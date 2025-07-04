package net.trollyloki.plugins.variablespawns.bukkit.api;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Provides spawn locations.
 */
public interface SpawnProvider {

    /**
     * Gets a spawn location.
     *
     * @return location
     */
    @NotNull Location getSpawnLocation();

}
