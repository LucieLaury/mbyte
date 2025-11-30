package fr.jayblanc.mbyte.manager.store;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Startup
public class StoreManager {

    @Inject StoreProviderConfig config;
    @Inject Instance<StoreProvider> providers;

    public StoreProvider getProvider() throws StoreProviderNotFoundException {
        return providers.stream().filter(p -> p.name().equals(config.provider())).findFirst().orElseThrow(() -> new StoreProviderNotFoundException("unable to find a provider for name: " + config.provider()));
    }

}
