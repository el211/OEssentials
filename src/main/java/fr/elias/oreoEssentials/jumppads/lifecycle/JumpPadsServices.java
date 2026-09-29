package fr.elias.oreoEssentials.jumppads.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface JumpPadsServices {
    fr.elias.oreoEssentials.jumppads.internal.jumpads.JumpPadsManager getJumpPads();
}
