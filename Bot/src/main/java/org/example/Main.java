package org.example;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.example.commands.ExportCommand;
import org.example.commands.MessageObserver;
import org.example.commands.PingCommand;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static List<MessageObserver> observers = List.of(
            new PingCommand(),
            new ExportCommand()
    );

    public static void main(String[] args) {
        // if (args.length == 0) {
        //     System.err.println("Token manquant");
        //     return;
        // }

        String token = System.getenv("DISCORD_TOKEN");
        System.out.println("Token : " + token);

        GatewayDiscordClient client =
                DiscordClientBuilder.create(token)
                        .build()
                        .login()
                        .block();

        if (client == null) {
            System.err.println("Impossible de se connecter à Discord");
            return;
        }

        client.on(MessageCreateEvent.class)
                .flatMap(event ->
                        Flux.fromIterable(observers)
                                .concatMap(observer ->
                                        observer.onMessage(event)
                                                .onErrorResume(ex -> {
                                                    System.err.println(
                                                            "Erreur dans " + observer.getClass().getSimpleName()
                                                    );
                                                    ex.printStackTrace();
                                                    return Mono.just(false); // on ignore et on continue
                                                })
                                )
                                .filter(Boolean::booleanValue)
                                .next()
                )
                .subscribe();

        client.onDisconnect().block();
    }
}