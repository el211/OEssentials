package fr.elias.oessentials.scoreboard.lifecycle;

import dev.oreo.modulith.core.ModuleApi;
import fr.elias.oessentials.scoreboard.internal.ScoreboardService;

@ModuleApi("services")
public interface ScoreboardServices {
    ScoreboardService getScoreboardService();
}
