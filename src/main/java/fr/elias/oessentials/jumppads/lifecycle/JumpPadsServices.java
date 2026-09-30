package fr.elias.oessentials.jumppads.lifecycle;

import dev.oreo.modulith.core.ModuleApi;

@ModuleApi("services")
public interface JumpPadsServices {
    fr.elias.oessentials.jumppads.internal.jumpads.JumpPadsManager getJumpPads();
}
