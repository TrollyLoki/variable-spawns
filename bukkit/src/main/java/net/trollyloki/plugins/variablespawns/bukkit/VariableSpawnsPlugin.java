package net.trollyloki.plugins.variablespawns.bukkit;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.Nullable;

public class VariableSpawnsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new SpawnListener(this), this);

        //noinspection DataFlowIssue
        getCommand("variablespawns").setExecutor(new VariableSpawnsCommand(this));
    }

    /**
     * Gets the ID of this server.
     *
     * @return server ID
     */
    public @Nullable String getCurrentServer() {
        return getConfig().getString("current-server");
    }

    private @Nullable ConfigurationSection getSpawnsSection() {
        return getConfig().getConfigurationSection("spawns");
    }

    /**
     * Gets the region where players should spawn.
     *
     * @param server ID of the server that the player came from, or {@code null} to use the default region
     * @return spawn region
     */
    public @Nullable SpawnRegion getSpawnRegion(@Nullable String server) {
        ConfigurationSection spawns = getSpawnsSection();
        if (spawns == null) return null;

        if (server == null || !spawns.contains(server)) {
            server = "default";
        }
        ConfigurationSection section = spawns.getConfigurationSection(server);
        if (section == null) return null;

        String worldName = section.getString("world");
        World world = worldName == null ? getServer().getWorlds().getFirst() : getServer().getWorld(worldName);
        if (world == null) throw new IllegalStateException("World " + worldName + " does not exist");

        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");

        double x2 = section.contains("x2") ? section.getDouble("x2") : x;
        double y2 = section.contains("y2") ? section.getDouble("y2") : y;
        double z2 = section.contains("z2") ? section.getDouble("z2") : z;

        float yaw = (float) section.getDouble("yaw");
        float pitch = (float) section.getDouble("pitch");

        float yaw2 = section.contains("yaw2") ? (float) section.getDouble("yaw2") : yaw;
        float pitch2 = section.contains("pitch2") ? (float) section.getDouble("pitch2") : pitch;

        return new SpawnRegion(world, new BoundingBox(x, y, z, x2, y2, z2),
                Math.min(yaw, yaw2), Math.max(yaw, yaw2),
                Math.min(pitch, pitch2), Math.max(pitch, pitch2));
    }

}
