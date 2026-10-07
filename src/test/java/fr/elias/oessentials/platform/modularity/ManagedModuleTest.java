package fr.elias.oessentials.platform.modularity;

import dev.oreo.modulith.core.ModuleApi;
import dev.oreo.modulith.core.ModuleListener;
import dev.oreo.modulith.core.ModuleRuntime;
import dev.oreo.modulith.core.PluginModule;
import dev.oreo.modulith.test.ModuleTestHarness;
import fr.elias.oessentials.OEssentials;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManagedModuleTest {
    private OEssentials plugin;
    private OreoModuleRegistry registry;
    private static final List<String> calls = new ArrayList<>();

    @BeforeEach
    void setup() {
        calls.clear();
        registry = new OreoModuleRegistry();
        plugin = mock(OEssentials.class);
        when(plugin.getModuleRegistry()).thenReturn(registry);
        when(plugin.isEnabled()).thenReturn(true);
    }

    @Test
    void consumerStopsBeforeItsStorageAndCompatibilityBindingsAreRemoved() {
        try (var harness = ModuleTestHarness.builder()
                .modules(List.of(ConsumerModule.class, StorageModule.class))
                .platformService(JavaPlugin.class, plugin).start()) {
            harness.assertStartupOrder("test-storage", "test-consumer");
            assertTrue(registry.require(StorageApi.class).isOpen());
            assertTrue(registry.require(ConsumerApi.class).active());
        }
        assertEquals(List.of("storage-start", "consumer-start", "consumer-stop", "storage-stop"), calls);
        assertNull(registry.read(StorageApi.class, StorageApi::isOpen));
        assertNull(registry.read(ConsumerApi.class, ConsumerApi::active));
    }

    @Test
    void partialStartupFailureRollsBackTheFailingModuleAndItsDependencies() {
        assertThrows(RuntimeException.class, () -> ModuleRuntime.builder()
                .modules(List.of(StorageModule.class, FailingModule.class))
                .platformService(JavaPlugin.class, plugin).start());
        assertEquals(List.of("storage-start", "failed-module-cleanup", "storage-stop"), calls);
        assertThrows(IllegalStateException.class, () -> registry.require(StorageApi.class));
        assertThrows(IllegalStateException.class, () -> registry.require(FailureApi.class));
    }

    @Test
    void cleanupFailureDoesNotPreventOtherResourcesOrStorageFromClosing() {
        var runtime = ModuleRuntime.builder().modules(List.of(StorageModule.class, BadCleanupModule.class))
                .platformService(JavaPlugin.class, plugin).start();
        assertThrows(RuntimeException.class, runtime::close);
        assertEquals(List.of("storage-start", "remaining-cleanup", "storage-stop"), calls);
        assertThrows(IllegalStateException.class, () -> registry.require(StorageApi.class));
        assertThrows(IllegalStateException.class, () -> registry.require(BadCleanupApi.class));
    }

    @Test
    void moduleEventsUnsubscribeAtShutdown() {
        var runtime = ModuleRuntime.builder().modules(List.of(StorageModule.class, ConsumerModule.class))
                .platformService(JavaPlugin.class, plugin).start();
        runtime.events().publish(new Ready());
        assertTrue(calls.contains("ready"));
        runtime.close();
        int size = calls.size();
        runtime.events().publish(new Ready());
        assertEquals(size, calls.size());
    }

    @Test
    void missingPlaceholderApiDoesNotPreventNonListenerModuleStartup() throws Exception {
        String fixture = OptionalIntegrationModule.class.getName();
        ClassLoader isolated = new ClassLoader(getClass().getClassLoader()) {
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                if (name.startsWith("me.clip.placeholderapi.")) throw new ClassNotFoundException(name);
                if (!name.equals(fixture)) return super.loadClass(name, resolve);
                Class<?> loaded = findLoadedClass(name);
                if (loaded == null) {
                    try (var input = getParent().getResourceAsStream(name.replace('.', '/') + ".class")) {
                        byte[] bytes = java.util.Objects.requireNonNull(input).readAllBytes();
                        loaded = defineClass(name, bytes, 0, bytes.length);
                    } catch (java.io.IOException e) {
                        throw new ClassNotFoundException(name, e);
                    }
                }
                if (resolve) resolveClass(loaded);
                return loaded;
            }
        };
        var module = isolated.loadClass(fixture).asSubclass(ManagedModule.class);
        // Reproduce the exact failure triggered by the old unconditional event scan.
        assertThrows(NoClassDefFoundError.class, module::getDeclaredMethods);
        try (var runtime = ModuleRuntime.builder().modules(List.of(module))
                .platformService(JavaPlugin.class, plugin).start()) {
            assertNotNull(registry.require(OptionalIntegrationApi.class));
        }
        assertThrows(IllegalStateException.class, () -> registry.require(OptionalIntegrationApi.class));
    }

    @Test
    void missingPluginDependencyAlsoRollsBackStartedModules() {
        assertThrows(RuntimeException.class, () -> ModuleRuntime.builder()
                .modules(List.of(StorageModule.class, LinkageFailureModule.class))
                .platformService(JavaPlugin.class, plugin).start());
        assertEquals(List.of("storage-start", "linkage-cleanup", "storage-stop"), calls);
    }

    @Test
    void disabledPluginAbortsStartupInsteadOfContinuingOtherModules() {
        when(plugin.isEnabled()).thenReturn(false);
        assertThrows(RuntimeException.class, () -> ModuleRuntime.builder()
                .modules(List.of(StorageModule.class, ConsumerModule.class))
                .platformService(JavaPlugin.class, plugin).start());
        assertEquals(List.of("storage-start", "storage-stop"), calls);
    }

    public record Ready() {}
    @ModuleApi("storage") public interface StorageApi { boolean isOpen(); }
    @ModuleApi("consumer") public interface ConsumerApi { boolean active(); }
    @ModuleApi("failure") public interface FailureApi {}
    @ModuleApi("bad-cleanup") public interface BadCleanupApi {}
    @ModuleApi("linkage") public interface LinkageApi {}
    @ModuleApi("optional") public interface OptionalIntegrationApi {}

    @PluginModule("test-optional-integration")
    public static final class OptionalIntegrationModule extends ManagedModule implements OptionalIntegrationApi {
        @Override protected void start() {}
        public me.clip.placeholderapi.expansion.PlaceholderExpansion getExpansion() { return null; }
    }

    @PluginModule("test-storage")
    public static final class StorageModule extends ManagedModule implements StorageApi {
        private boolean open;
        @Override protected void start() {
            cleanup("storage", () -> { calls.add("storage-stop"); open = false; });
            open = true;
            calls.add("storage-start");
        }
        @Override public boolean isOpen() { return open; }
    }

    @PluginModule(value = "test-consumer", dependencies = "test-storage::storage")
    public static final class ConsumerModule extends ManagedModule implements ConsumerApi {
        @Override protected void start() {
            assertTrue(services(StorageApi.class).isOpen());
            calls.add("consumer-start");
            listenForModuleEvents(this);
            cleanup("consumer", () -> {
                assertTrue(services(StorageApi.class).isOpen(), "Storage closed before consumer");
                assertFalse(isActive());
                calls.add("consumer-stop");
            });
        }
        @Override public boolean active() { return isActive(); }
        @ModuleListener public void onReady(Ready ready) { calls.add("ready"); }
    }

    @PluginModule(value = "test-failure", dependencies = "test-storage::storage")
    public static final class FailingModule extends ManagedModule implements FailureApi {
        @Override protected void start() {
            cleanup("partial", () -> calls.add("failed-module-cleanup"));
            throw new IllegalStateException("Simulated startup failure");
        }
    }

    @PluginModule(value = "test-bad-cleanup", dependencies = "test-storage::storage")
    public static final class BadCleanupModule extends ManagedModule implements BadCleanupApi {
        @Override protected void start() {
            cleanup("remaining", () -> calls.add("remaining-cleanup"));
            cleanup("broken", () -> { throw new IllegalStateException("Simulated cleanup failure"); });
        }
    }

    @PluginModule(value = "test-linkage", dependencies = "test-storage::storage")
    public static final class LinkageFailureModule extends ManagedModule implements LinkageApi {
        @Override protected void start() {
            cleanup("partial", () -> calls.add("linkage-cleanup"));
            throw new NoClassDefFoundError("OptionalPluginDependency");
        }
    }
}
