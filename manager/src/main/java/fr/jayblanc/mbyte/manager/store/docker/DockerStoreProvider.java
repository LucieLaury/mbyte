package fr.jayblanc.mbyte.manager.store.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import fr.jayblanc.mbyte.manager.store.StoreProvider;
import fr.jayblanc.mbyte.manager.store.StoreProviderException;
import io.quarkus.runtime.ConfigConfig;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
public class DockerStoreProvider implements StoreProvider {

    private static final Logger LOGGER = Logger.getLogger(DockerStoreProvider.class.getName());
    private static final String NAME = "docker";
    @Inject
    ConfigConfig configConfig;

    private DockerClient client;

    @Inject DockerStoreProviderConfig config;

    @PostConstruct
    private void init() {
        DockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(config.server())
                .build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(clientConfig.getDockerHost())
                .sslConfig(clientConfig.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        client = DockerClientImpl.getInstance(clientConfig, httpClient);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public List<String> listApps() {
        LOGGER.log(Level.INFO, "Listing docker apps");
        return client.listContainersCmd().exec().stream().map(container -> Arrays.stream(container.getNames()).collect(Collectors.joining()) + " / " + container.getImage()).collect(Collectors.toList());
    }

    @Override
    public String createApp(String id, String owner, String name) {
        LOGGER.log(Level.INFO, "Creating new docker app");
        return null;
    }

    @Override public void destroyApp(String id) throws StoreProviderException {
        LOGGER.log(Level.INFO, "Destroying docker app");
        throw new StoreProviderException("NOT IMPLEMENTED");
    }
}
