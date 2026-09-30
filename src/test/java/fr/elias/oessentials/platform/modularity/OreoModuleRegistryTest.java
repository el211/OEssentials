package fr.elias.oessentials.platform.modularity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OreoModuleRegistryTest {
    interface Service { String value(); }

    @Test
    void compatibilityGetterIsNullBeforeStartupAndAfterShutdown() {
        var registry = new OreoModuleRegistry();
        assertNull(registry.read(Service.class, Service::value));
        Service service = () -> "ready";
        registry.bind(Service.class, service);
        assertSame(service, registry.require(Service.class));
        assertEquals("ready", registry.read(Service.class, Service::value));
        registry.unbind(Service.class, service);
        assertNull(registry.read(Service.class, Service::value));
        assertThrows(IllegalStateException.class, () -> registry.require(Service.class));
    }

    @Test
    void duplicateOwnerCannotReplaceOrUnbindTheOriginal() {
        var registry = new OreoModuleRegistry();
        Service first = () -> "first";
        Service second = () -> "second";
        registry.bind(Service.class, first);
        assertThrows(IllegalStateException.class, () -> registry.bind(Service.class, second));
        registry.unbind(Service.class, second);
        assertSame(first, registry.require(Service.class));
        registry.clear();
        assertNull(registry.read(Service.class, Service::value));
    }
}
