package net.trollyloki.plugins.variablespawns.bukkit.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * A region in which players can be spawned randomly.
 */
public class SpawnRegion {

    private final @NotNull World world;
    private final @NotNull BoundingBox boundingBox;
    private final float minYaw, maxYaw;
    private final float minPitch, maxPitch;

    /**
     * Creates a new spawn region.
     *
     * @param world       world that the player must spawn in
     * @param boundingBox bounding box that the player's spawn location must be within
     * @param yaw1      one end of the yaw range that the player can spawn with
     * @param yaw2      the other end of the yaw range that the player can spawn with
     * @param pitch1    one end of the pitch range that the player can spawn with
     * @param pitch2    the other end of the pitch range that the player can spawn with
     */
    public SpawnRegion(@NotNull World world, @NotNull BoundingBox boundingBox, float yaw1, float yaw2, float pitch1, float pitch2) {
        this.world = world;
        this.boundingBox = boundingBox.clone();
        this.minYaw = Math.min(yaw1, yaw2);
        this.maxYaw = Math.max(yaw1, yaw2);
        this.minPitch = Math.min(pitch1, pitch2);
        this.maxPitch = Math.max(pitch1, pitch2);
    }

    /**
     * Creates a new spawn region with a fixed yaw and pitch.
     *
     * @param world       world that the player must spawn in
     * @param boundingBox bounding box that the player's spawn location must be within
     * @param yaw         yaw that the player must spawn with
     * @param pitch       pitch that the player must spawn with
     */
    public SpawnRegion(@NotNull World world, @NotNull BoundingBox boundingBox, float yaw, float pitch) {
        this(world, boundingBox, yaw, yaw, pitch, pitch);
    }

    /**
     * Gets the world that the player must spawn in.
     *
     * @return world
     */
    public @NotNull World getWorld() {
        return world;
    }

    /**
     * Gets a copy of the bounding box that the player's spawn location must be within.
     *
     * @return bounding box
     */
    public @NotNull BoundingBox getBoundingBox() {
        return boundingBox.clone();
    }

    /**
     * Gets the minimum yaw that the player can spawn with.
     *
     * @return minimum yaw
     */
    public float getMinYaw() {
        return minYaw;
    }

    /**
     * Gets the maximum yaw that the player can spawn with.
     *
     * @return maximum yaw
     */
    public float getMaxYaw() {
        return maxYaw;
    }

    /**
     * Gets the minimum pitch that the player can spawn with.
     *
     * @return minimum pitch
     */
    public float getMinPitch() {
        return minPitch;
    }

    /**
     * Gets the maximum pitch that the player can spawn with.
     *
     * @return maximum pitch
     */
    public float getMaxPitch() {
        return maxPitch;
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

        float yaw = minYaw == maxYaw ? minYaw
                : random.nextFloat(minYaw, maxYaw);
        float pitch = minPitch == maxPitch ? minPitch
                : random.nextFloat(minPitch, maxPitch);

        return new Location(world, x, y, z, yaw, pitch);
    }

}
