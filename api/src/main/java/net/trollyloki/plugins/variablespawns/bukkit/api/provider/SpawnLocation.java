package net.trollyloki.plugins.variablespawns.bukkit.api.provider;

import net.trollyloki.plugins.variablespawns.bukkit.api.SpawnProvider;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * A fixed location at which players can be spawned.
 */
public class SpawnLocation implements SpawnProvider {

    private final @NotNull Location location;

    /**
     * Creates a new fixed spawn location.
     *
     * @param location location that the player must spawn at (including yaw/pitch)
     */
    public SpawnLocation(@NotNull Location location) {
        this.location = location.clone();
    }

    @Override
    public @NotNull Location getSpawnLocation() {
        return location.clone();
    }

}
