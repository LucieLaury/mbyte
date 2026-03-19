package org.example.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Date;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class ExportCommand implements Command, MessageObserver {

    private final HttpClient http = createHttpClient();

    // Lus depuis les variables d'environnement
    private final String keycloakTokenUrl = System.getenv("KEYCLOAK_TOKEN_URL");
    private final String keycloakClientId  = System.getenv("KEYCLOAK_CLIENT_ID");
    private final String storeUsername     = System.getenv("STORE_USERNAME");
    private final String storePassword     = System.getenv("STORE_PASSWORD");
    private final String storeBaseUrl      = System.getenv("STORE_BASE_URL");

    @Override
    public String name() {
        return "export";
    }

    @Override
    public Mono<Boolean> onMessage(MessageCreateEvent event) {
        String content = event.getMessage().getContent();
        if (!content.startsWith("!export")) return Mono.just(false);
        return execute(event);
    }

    @Override
    public Mono<Boolean> execute(MessageCreateEvent event) {
        String content = event.getMessage().getContent();
        String[] parts = content.split("\\s+");
        if (parts.length < 2 || parts.length > 3) {
            return reply(event, "Usage : !export <nombre> [ASC|DESC]").thenReturn(true);
        }
        int limit;
        try {
            limit = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return reply(event, "Le nombre doit être un entier.").thenReturn(true);
        }
        if (limit <= 0) {
            return reply(event, "Le nombre doit être supérieur à 0.").thenReturn(true);
        }
        boolean asc;
        if (parts.length == 3) {
            String order = parts[2].toUpperCase();
            if ("DESC".equals(order)) {
                asc = false;
            } else {
                asc = true;
                if (!"ASC".equals(order)) {
                    return reply(event, "Ordre invalide. Utilise ASC ou DESC.").thenReturn(true);
                }
            }
        } else {
            asc = true;
        }

        Message commandMessage = event.getMessage();
        String owner = commandMessage.getAuthor()
                .map(u -> u.getUsername().replace(".", ""))
                .orElse("???");
        Path file = Paths.get("export_" + owner + "_" + new Date().getTime() + ".md");

        return commandMessage.delete()
                .thenMany(
                        commandMessage.getChannel()
                                .flatMapMany(channel ->
                                        channel.getMessagesBefore(commandMessage.getId()).take(limit)
                                )
                )
                .map(message -> {
                    String author = message.getAuthor()
                            .map(u -> u.getUsername())
                            .orElse("Inconnu");
                    return "**" + author + "** :  \n" + message.getContent() + "\n\n";
                })
                .collectList()
                .flatMap(lines -> Mono.fromRunnable(() -> {
                    try {
                        if (asc) Collections.reverse(lines);
                        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).subscribeOn(Schedulers.boundedElastic()))
                // 1. Obtenir le token Keycloak
                .then(fetchToken())
                // 2. Uploader le fichier dans le store
                .flatMap(token -> uploadToStore(file, token))
                // 3. Confirmer dans Discord
                .then(reply(event, "✅ Export uploadé dans le store : `" + file.getFileName() + "`"))
                .thenReturn(true)
                // En cas d'erreur d'upload, on prévient sans planter
                .onErrorResume(e -> {
                    System.err.println("[ExportCommand] Erreur upload : " + e.getMessage());
                    e.printStackTrace(); // <-- ajoute cette ligne
                    return reply(event, "⚠️ Export créé localement mais l'upload a échoué : " + e.getMessage())
                            .thenReturn(true);
                });
    }

    private Mono<String> fetchToken() {
        String body = "grant_type=password"
            + "&client_id=" + keycloakClientId
            + "&username=" + storeUsername
            + "&password=" + storePassword
            + "&scope=openid+profile+email+microprofile-jwt";

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(keycloakTokenUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        

        return Mono.fromCallable(() -> {
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200)
                throw new RuntimeException("Keycloak auth failed: " + resp.statusCode() + " " + resp.body());
            String json = resp.body();
            int idx = json.indexOf("\"access_token\":\"");
            if (idx == -1) throw new RuntimeException("No access_token in response");
            int start = idx + 16;
            int end = json.indexOf("\"", start);
            System.out.println("[ExportCommand] Token: " + json.substring(start, end));
            return json.substring(start, end);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Void> uploadToStore(Path file, String token) {
        return Mono.fromCallable(() -> {
            byte[] content = Files.readAllBytes(file);
            String filename = file.getFileName().toString();
            String boundary = "----botboundary" + System.currentTimeMillis();
            String CRLF = "\r\n";

            byte[] namePart = ("--" + boundary + CRLF
                    + "Content-Disposition: form-data; name=\"name\"" + CRLF
                    + CRLF
                    + filename + CRLF).getBytes();

            byte[] dataPart = ("--" + boundary + CRLF
                    + "Content-Disposition: form-data; name=\"data\"; filename=\"" + filename + "\"" + CRLF
                    + "Content-Type: text/markdown" + CRLF
                    + CRLF).getBytes();

            byte[] closing = (CRLF + "--" + boundary + "--" + CRLF).getBytes();

            // Assemble les parties
            byte[] body = new byte[namePart.length + dataPart.length + content.length + closing.length];
            System.arraycopy(namePart, 0, body, 0, namePart.length);
            System.arraycopy(dataPart, 0, body, namePart.length, dataPart.length);
            System.arraycopy(content, 0, body, namePart.length + dataPart.length, content.length);
            System.arraycopy(closing, 0, body, namePart.length + dataPart.length + content.length, closing.length);

            String url = storeBaseUrl + "api/nodes/root";
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                    .POST(HttpRequest.BodyPublishers.ofByteArray(body))
                    .build();

            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println("[ExportCommand] POST -> " + url + " | " + resp.statusCode() + " " + resp.body());
            if (resp.statusCode() < 200 || resp.statusCode() >= 300)
                throw new RuntimeException("Upload failed: " + resp.statusCode() + " " + resp.body());
            return null;
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

    private Mono<Void> reply(MessageCreateEvent event, String text) {
        return event.getMessage().getChannel()
                .flatMap(channel -> channel.createMessage(text))
                .then();
    }

    private static HttpClient createHttpClient() {
    try {
        TrustManager[] trustAll = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAll, new java.security.SecureRandom());
        return HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
    } catch (Exception e) {
        throw new RuntimeException("Failed to create HTTP client", e);
    }
}
}