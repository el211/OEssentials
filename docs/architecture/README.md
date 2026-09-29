# OEssentials module architecture

The `refactor/minecraft-modulith` branch moves plugin composition, service ownership,
and shutdown into 56 MinecraftModulith modules. The Bukkit entry point retains the
119 public methods from version 6.8 as a compatibility facade.

## Dependency and build

The project builds with **Gradle** (Kotlin DSL) via the Gradle wrapper — Maven is no
longer used. MinecraftModulith is pinned to [`v0.2.1`](https://github.com/el211/MinecraftModulith/tree/v0.2.1)
from JitPack:

```kotlin
implementation("com.github.el211.MinecraftModulith:modulith-paper:v0.2.1")
annotationProcessor("com.github.el211.MinecraftModulith:modulith-processor:v0.2.1")
testImplementation("com.github.el211.MinecraftModulith:modulith-test:v0.2.1")
```

`modulith-processor` validates the new module boundaries during compilation;
`modulith-test` supplies the lifecycle test harness. The MinecraftModulith runtime,
ClassGraph, CommandsAPI, Libby and bStats are shaded and relocated into the plugin JAR
by the Shadow plugin. No separate MinecraftModulith server plugin is required.

The plugin targets **Java 21** bytecode. Because a provided dependency
(worldedit-bukkit 7.4.4, via the FastAsyncWorldEdit BOM) ships Java 25 bytecode, the
Gradle toolchain compiles on **JDK 25** with `--release 21`; the tests run on JDK 25
with `-Dnet.bytebuddy.experimental=true` for Mockito. Gradle auto-provisions the
toolchain if needed.

Build with:

```bash
./gradlew clean build      # compile, test, and produce the shaded jar
./gradlew shadowJar        # just the plugin jar
```

The distributable artifact is `build/libs/OEssentials-7.0.jar` (the
`-plain.jar` beside it is the unshaded classes and is not used for deployment).

> Note: `build.gradle.kts` lists `mavenLocal()` first so the build still succeeds when
> a remote plugin repository is briefly unreachable (e.g. `maven.devs.beer` returning
> HTTP 522) and the artifacts are already in the local Maven cache.

## Layout

| Location | Responsibility |
| --- | --- |
| `OEssentials` | Bukkit entry point, library loading, compatible public getters and reload methods |
| `bootstrap/OreoModules` | Explicit catalog of all lifecycle modules; no runtime package scanning |
| `bootstrap/ManagedModule` | Named service publication, startup rollback, resource ownership and event subscriptions |
| `bootstrap/features/<feature>` | Feature initialization, private state, named service API and cleanup |
| `bootstrap/OreoModuleRegistry` | Compatibility lookup for callers of the old plugin API |
| `util/ModuleTaskScope` | Ownership of scheduled tasks created during initialization and their nested callbacks |
| Existing `modules`, `services`, `commands`, `api` packages | Gameplay implementation and established external API types |

New composition modules consume other modules through interfaces annotated
`@ModuleApi("services")` and declare those dependencies in `@PluginModule`.
MinecraftModulith enforces those service lookups and validates the graph. Existing
gameplay classes continue using their established APIs; the annotation processor's
module boundaries cover `bootstrap/features`, not all legacy gameplay packages.

The explicit ordering dependencies retain the established initialization sequence,
including constructor side effects. Reverse shutdown keeps storage and configuration
available until their consumers finish. See [the complete dependency graph](modules.mmd).

## Lifecycle

1. `onLoad` loads the existing external libraries.
2. `onEnable` starts `PaperModulith` with the explicit catalog.
3. Each module publishes its named service interface before constructing its managers.
   This preserves getters used by manager constructors. Disabled feature flags still
   leave their manager getters null, as before.
4. Cleanup is registered before resource creation. Startup failures unwind both the
   partially initialized module and previously started modules.
5. Module shutdown cancels owned tasks, unregisters listeners/events, and releases
   resources. Cleanup failures are reported without skipping remaining cleanup.
6. Global plugin shutdown removes residual Bukkit registrations and clears the
   compatibility registry.

`OreScheduler` keeps its Paper/Folia global, location, entity, and async scheduling
behavior. Module ownership propagates through callbacks and nested scheduling.
Completed one-shot tasks are released from tracking. Tasks started outside module
initialization/callbacks retain the existing plugin-managed lifetime. This refactor
does not introduce independent hot unloading of gameplay modules.

## Asynchronous messaging

Messaging starts locally and connects to RabbitMQ asynchronously. Connecting senders
are owned before the connection completes and are closed if the plugin stops.
Activation callbacks check module activity before touching services.

After the packet manager is initialized, the module publishes `MessagingReady` on
the server scheduler. Currency, portals, RTP, and group RTP install their own handlers
through `@ModuleListener`; subscriptions disappear when their modules stop. Messaging
no longer reads later modules through the compatibility registry.

## Compatibility and diagnostics

Existing configuration paths, feature flags, persisted data formats, command names,
and public API classes remain in place. The custom-tab visibility fix from 6.8 is
included. A running lifecycle module does not imply that its gameplay feature flag
is enabled.

`getModuleDiagnostics()` exposes MinecraftModulith's state/metrics snapshot while
the plugin is running. Compile-time module metadata is packaged under
`META-INF/minecraft-modulith/modules.idx`.

Tests cover graph/catalog consistency, all 119 released entry-point signatures,
dependency shutdown order, startup/linkage failure rollback, cleanup errors,
event unsubscription, task ownership/races, and command cleanup identity. Existing
feature tests remain part of the build. These checks do not replace live Paper/Folia
testing with the server's optional integrations.

Before deployment, verify join/quit, late joins with the custom tab layout, world
changes, configuration reloads, storage writes at shutdown, and RabbitMQ connecting
or failing after startup on a test server with the production configuration.
