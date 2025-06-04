package net.trollyloki.plugins.variablespawns.bukkit;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * A region in which players can be spawned.
 */
public class SpawnRegion {

    private final @NotNull World world;
    private final @NotNull BoundingBox boundingBox;
    private final @Nullable Float yaw, pitch;

    /**
     * Creates a new spawn region.
     *
     * @param world       world
     * @param boundingBox bounding box
     * @param yaw         yaw, or {@code null} to randomize yaw
     * @param pitch       pitch, or {@code null} to randomize pitch
     */
    public SpawnRegion(@NotNull World world, @NotNull BoundingBox boundingBox, @Nullable Float yaw, @Nullable Float pitch) {
        this.world = world;
        this.boundingBox = boundingBox;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Gets the world in which players can spawn.
     *
     * @return world
     */
    public @NotNull World getWorld() {
        return world;
    }

    /**
     * Gets a copy of the bounding box defining the range of locations where players can spawn.
     *
     * @return bounding box.
     */
    public @NotNull BoundingBox getBoundingBox() {
        return boundingBox.clone();
    }

    /**
     * Gets the yaw players should spawn with.
     *
     * @return yaw, or {@code null} if yaw will be randomized
     */
    public @Nullable Float getYaw() {
        return yaw;
    }

    /**
     * Gets the pitch players should spawn with.
     *
     * @return pitch, or {@code null} if pitch will be randomized
     */
    public @Nullable Float getPitch() {
        return pitch;
    }

    /**
     * Gets a random location within this spawn region.
     *
     * @param random random source
     * @return location
     */
    public @NotNull Location getRandomLocation(@NotNull Random random) {
        double x = boundingBox.getWidthX() == 0 ? boundingBox.getMinX()
                : random.nextDouble(boundingBox.getMinX(), boundingBox.getMaxX());
        double y = boundingBox.getHeight() == 0 ? boundingBox.getMinY()
                : random.nextDouble(boundingBox.getMinY(), boundingBox.getMaxY());
        double z = boundingBox.getWidthZ() == 0 ? boundingBox.getMinZ()
                : random.nextDouble(boundingBox.getMinZ(), boundingBox.getMaxZ());

        float yaw = this.yaw != null ? this.yaw
                : random.nextFloat(-180, 180);
        float pitch = this.pitch != null ? this.pitch
                : random.nextFloat(-90, 90);

        return new Location(world, x, y, z, yaw, pitch);
    }

}
