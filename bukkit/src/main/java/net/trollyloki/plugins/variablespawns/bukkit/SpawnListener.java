package net.trollyloki.plugins.variablespawns.bukkit;

import net.trollyloki.plugins.variablespawns.Constants;
import net.trollyloki.plugins.variablespawns.CookieUtils;
import net.trollyloki.plugins.variablespawns.bukkit.api.SpawnRegion;
import net.trollyloki.plugins.variablespawns.bukkit.api.event.PlayerSpawnEvent;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class SpawnListener implements Listener {

    private static final @NotNull NamespacedKey SERVER_COOKIE_KEY = Objects.requireNonNull(NamespacedKey.fromString(Constants.SERVER_COOKIE));

    private final @NotNull VariableSpawnsPlugin plugin;

    public SpawnListener(@NotNull VariableSpawnsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        retrieveServerCookie(event.getPlayer()).whenComplete((server, throwable) -> {
            if (throwable != null) {
                plugin.getLogger().log(Level.WARNING, "Failed to retrieve server cookie from " + event.getPlayer().getName(), throwable);
            }

            teleportToSpawn(event.getPlayer(), server);
            storeServerCookie(event.getPlayer());

        }).exceptionally(throwable -> {
            plugin.getLogger().log(Level.WARNING, "Exception while handling cookie response", throwable);
            return null;
        });
    }

    private void teleportToSpawn(@NotNull Player player, @Nullable String server) {
        SpawnRegion region = plugin.getSpawnRegion(server);
        Location randomLocation = region == null ? null : region.getRandomLocation(ThreadLocalRandom.current());

        PlayerSpawnEvent event = new PlayerSpawnEvent(player, server, randomLocation);
        plugin.getServer().getPluginManager().callEvent(event);

        Location spawnLocation = event.getSpawnLocation();
        if (spawnLocation == null) return;

        player.teleportAsync(spawnLocation);
    }

    private @NotNull CompletableFuture<String> retrieveServerCookie(@NotNull Player player) {
        return player.retrieveCookie(SERVER_COOKIE_KEY).thenApply(CookieUtils::decodeString);
    }

    private void storeServerCookie(@NotNull Player player) {
        player.storeCookie(SERVER_COOKIE_KEY, CookieUtils.encodeString(plugin.getCurrentServer()));
    }

}
