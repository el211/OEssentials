package fr.elias.oessentials.redis.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.redis.internal.persistence.database.RedisManager;

@ModuleApi("services")
public interface RedisServices {
    RedisManager getRedis();
}
