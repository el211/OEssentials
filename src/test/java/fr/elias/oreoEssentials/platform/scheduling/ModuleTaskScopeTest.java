package fr.elias.oreoEssentials.platform.scheduling;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModuleTaskScopeTest {
    private final Plugin plugin = mock(Plugin.class);

    @Test
    void stoppingModuleCancelsRepeatingAndPendingTasks() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        ScheduledTask pending = mock(ScheduledTask.class);
        ScheduledTask repeating = mock(ScheduledTask.class);
        scope.run(() -> {
            ModuleTaskScope.schedule(plugin, () -> {}, false, run -> pending);
            ModuleTaskScope.schedule(plugin, () -> {}, true, run -> repeating);
        });
        assertEquals(2, scope.taskCount());
        scope.close();
        scope.close();
        verify(pending).cancel();
        verify(repeating).cancel();
        assertEquals(0, scope.taskCount());
    }

    @Test
    void finishedOneShotIsReleasedAndNestedTaskKeepsItsModuleOwner() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        AtomicReference<Runnable> callback = new AtomicReference<>();
        ScheduledTask parent = mock(ScheduledTask.class);
        ScheduledTask child = mock(ScheduledTask.class);
        scope.run(() -> ModuleTaskScope.schedule(plugin,
                () -> ModuleTaskScope.schedule(plugin, () -> {}, true, task -> child), false,
                task -> { callback.set(task); return parent; }));
        callback.get().run();
        assertEquals(1, scope.taskCount());
        scope.close();
        verify(parent, never()).cancel();
        verify(child).cancel();
    }

    @Test
    void queuedCallbackDoesNotRunAfterShutdown() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        AtomicReference<Runnable> callback = new AtomicReference<>();
        Runnable work = mock(Runnable.class);
        scope.run(() -> ModuleTaskScope.schedule(plugin, work, false,
                task -> { callback.set(task); return mock(ScheduledTask.class); }));
        scope.close();
        callback.get().run();
        verifyNoInteractions(work);
    }

    @Test
    void completionBeforeSchedulerReturnsDoesNotLeakTask() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        scope.run(() -> ModuleTaskScope.schedule(plugin, () -> {}, false,
                task -> { task.run(); return mock(ScheduledTask.class); }));
        assertEquals(0, scope.taskCount());
    }

    @Test
    void shutdownDuringTaskRegistrationCancelsTheLateHandle() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        ScheduledTask handle = mock(ScheduledTask.class);
        scope.run(() -> ModuleTaskScope.schedule(plugin, () -> {}, true,
                task -> { scope.close(); return handle; }));
        verify(handle).cancel();
        assertEquals(0, scope.taskCount());
    }

    @Test
    void explicitCancellationReleasesHandle() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        scope.run(() -> ModuleTaskScope.schedule(plugin, () -> {}, true,
                task -> mock(ScheduledTask.class)).cancel());
        assertEquals(0, scope.taskCount());
    }

    @Test
    void otherPluginsAndTasksOutsideAModuleAreNotClaimed() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        ScheduledTask other = mock(ScheduledTask.class);
        ScheduledTask outside = mock(ScheduledTask.class);
        scope.run(() -> ModuleTaskScope.schedule(mock(Plugin.class), () -> {}, true, task -> other));
        ModuleTaskScope.schedule(plugin, () -> {}, true, task -> outside);
        scope.close();
        verify(other, never()).cancel();
        verify(outside, never()).cancel();
    }

    @Test
    void retiredEntityWithNoTaskDoesNotLeaveAHandle() {
        ModuleTaskScope scope = new ModuleTaskScope(plugin);
        scope.run(() -> assertFalse(ModuleTaskScope.schedule(plugin, () -> {}, false, task -> null).isValid()));
        assertEquals(0, scope.taskCount());
    }
}
