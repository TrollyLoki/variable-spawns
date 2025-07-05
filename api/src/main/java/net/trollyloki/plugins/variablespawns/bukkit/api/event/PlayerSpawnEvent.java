package net.trollyloki.plugins.variablespawns.bukkit.api.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a player joins the server and can be assigned a spawn location.
 */
public class PlayerSpawnEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final @Nullable String server;
    private @Nullable Location spawnLocation;

    @ApiStatus.Internal
    public PlayerSpawnEvent(@NotNull Player who, @Nullable String server, @Nullable Location spawnLocation) {
        super(who);
        this.server = server;
        this.spawnLocation = spawnLocation;
    }

    /**
     * Gets the ID of the server that the player came from.
     *
     * @return origin server, or {@code null} if the player just joined
     */
    public @Nullable String getOriginServer() {
        return server;
    }

    /**
     * Gets the location that the player will spawn at.
     * The returned location is mutable.
     *
     * @return spawn location, or {@code null} if the player's spawn location will not be changed
     */
    public @Nullable Location getSpawnLocation() {
        return spawnLocation;
    }

    /**
     * Sets the location that the player will spawn at.
     *
     * @param spawnLocation spawn location, or {@code null} to not change the player's spawn location
     */
    public void setSpawnLocation(@Nullable Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
