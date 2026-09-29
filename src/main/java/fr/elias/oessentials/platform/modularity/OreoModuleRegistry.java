package fr.elias.oessentials.platform.modularity;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/** Compatibility lookup for the existing Bukkit-facing API while modules own their services. */
public final class OreoModuleRegistry {
    private final Map<Class<?>, Object> services = new ConcurrentHashMap<>();

    public <T> void bind(Class<T> api, T service) {
        Objects.requireNonNull(service, "service");
        if (services.putIfAbsent(api, api.cast(service)) != null) {
            throw new IllegalStateException("Module API already bound: " + api.getName());
        }
    }

    public <T> T require(Class<T> api) {
        T service = api.cast(services.get(api));
        if (service == null) throw new IllegalStateException("Module API is not running: " + api.getName());
        return service;
    }

    /** Legacy getters returned null until their feature initialized, including disabled features. */
    public <T, R> R read(Class<T> api, Function<T, R> reader) {
        T service = api.cast(services.get(api));
        return service == null ? null : reader.apply(service);
    }

    public void unbind(Class<?> api, Object service) { services.remove(api, service); }
    public void clear() { services.clear(); }
}
