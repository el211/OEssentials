package fr.elias.oreoEssentials.redis.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.redis.internal.persistence.database.RedisManager;

@ModuleApi("services")
public interface RedisServices {
    RedisManager getRedis();
}
