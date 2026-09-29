package fr.elias.oreoEssentials.scoreboard.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oreoEssentials.scoreboard.internal.ScoreboardService;

@ModuleApi("services")
public interface ScoreboardServices {
    ScoreboardService getScoreboardService();
}
