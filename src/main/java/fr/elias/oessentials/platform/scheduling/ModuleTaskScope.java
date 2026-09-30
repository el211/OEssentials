package fr.elias.oessentials.platform.scheduling;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/** Carries module ownership through existing Paper/Folia scheduler calls, including nested tasks. */
public final class ModuleTaskScope implements AutoCloseable {
    private static final ThreadLocal<ModuleTaskScope> CURRENT = new ThreadLocal<>();
    private final Plugin plugin;
    private final Set<OreTask> tasks = ConcurrentHashMap.newKeySet();
    private volatile boolean closed;

    public ModuleTaskScope(Plugin plugin) { this.plugin = plugin; }

    public void run(Runnable action) {
        if (closed) return;
        ModuleTaskScope previous = CURRENT.get();
        CURRENT.set(this);
        try { action.run(); }
        finally {
            if (previous == null) CURRENT.remove();
            else CURRENT.set(previous);
        }
    }

    static OreTask schedule(Plugin plugin, Runnable action, boolean repeating,
                            Function<Runnable, ScheduledTask> scheduler) {
        ModuleTaskScope owner = CURRENT.get();
        if (owner == null || owner.plugin != plugin) return new OreTask(scheduler.apply(action));
        if (owner.closed) return OreTask.EMPTY;
        Invocation invocation = owner.new Invocation(action, repeating);
        OreTask task = new OreTask(scheduler.apply(invocation));
        invocation.attach(task);
        return task;
    }

    @Override
    public void close() {
        closed = true;
        for (OreTask task : tasks) task.cancel();
        tasks.clear();
    }

    int taskCount() { return tasks.size(); }

    private final class Invocation implements Runnable {
        private final Runnable action;
        private final boolean repeating;
        private volatile OreTask task;
        private volatile boolean finished;

        private Invocation(Runnable action, boolean repeating) {
            this.action = action;
            this.repeating = repeating;
        }

        private void attach(OreTask task) {
            this.task = task;
            if (finished || !task.isValid()) return;
            tasks.add(task);
            task.onCancel(() -> tasks.remove(task));
            if (finished) tasks.remove(task);
            if (closed) { tasks.remove(task); task.cancel(); }
        }

        @Override
        public void run() {
            try { ModuleTaskScope.this.run(action); }
            finally {
                if (!repeating) {
                    finished = true;
                    OreTask handle = task;
                    if (handle != null) tasks.remove(handle);
                }
            }
        }
    }
}
