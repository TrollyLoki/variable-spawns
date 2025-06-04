package net.trollyloki.plugins.variablespawns.bukkit;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
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
     * Gets the region where players coming from a specific server should spawn.
     *
     * @param server server ID
     * @return spawn region
     */
    public @Nullable SpawnRegion getSpawnRegion(@NotNull String server) {
        ConfigurationSection spawns = getSpawnsSection();
        if (spawns == null) return null;

        ConfigurationSection section = spawns.getConfigurationSection(server);
        if (section == null) return null;

        String worldName = section.getString("world");
        World world = worldName == null ? getServer().getWorlds().getFirst() : getServer().getWorld(worldName);
        if (world == null) throw new IllegalStateException("World " + worldName + " does not exist");

        double x1 = section.getDouble("x1");
        double y1 = section.getDouble("y1");
        double z1 = section.getDouble("z1");

        double x2 = section.contains("x2") ? section.getDouble("x2") : x1;
        double y2 = section.contains("y2") ? section.getDouble("y2") : y1;
        double z2 = section.contains("z2") ? section.getDouble("z2") : z1;

        Float yaw = section.contains("yaw") ? (float) section.getDouble("yaw") : null;
        Float pitch = section.contains("pitch") ? (float) section.getDouble("pitch") : null;

        return new SpawnRegion(world, new BoundingBox(x1, y1, z1, x2, y2, z2), yaw, pitch);
    }

}
