package net.trollyloki.plugins.variablespawns.bukkit.api.event;

import net.trollyloki.plugins.variablespawns.bukkit.api.SpawnProvider;
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
    private @Nullable SpawnProvider spawnProvider;

    @ApiStatus.Internal
    public PlayerSpawnEvent(@NotNull Player who, @Nullable String server, @Nullable SpawnProvider spawnProvider) {
        super(who);
        this.server = server;
        this.spawnProvider = spawnProvider;
    }

    /**
     * Gets the ID of the server that the player came from, or {@code null} if the player just joined
     *
     * @return origin server
     */
    public @Nullable String getOriginServer() {
        return server;
    }

    /**
     * Gets the provider that will be used to obtain a spawn location.
     *
     * @return spawn provider, or {@code null} if the player's spawn location will not be changed
     */
    public @Nullable SpawnProvider getSpawnProvider() {
        return spawnProvider;
    }

    /**
     * Sets the provider that will be used to obtain a spawn location.
     *
     * @param spawnProvider spawn provider, or {@code null} to not change the player's spawn location
     */
    public void setSpawnProvider(@Nullable SpawnProvider spawnProvider) {
        this.spawnProvider = spawnProvider;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
