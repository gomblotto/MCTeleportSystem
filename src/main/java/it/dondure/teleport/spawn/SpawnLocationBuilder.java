package it.dondure.teleport.spawn;

import org.bukkit.Location;
import org.bukkit.World;

public class SpawnLocationBuilder {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private World world;

    public SpawnLocationBuilder setWorld(final World world) {
        this.world = world;
        return this;
    }

    public SpawnLocationBuilder setX(final double x) {
        this.x = x;
        return this;
    }

    public SpawnLocationBuilder setY(final double y) {
        this.y = y;
        return this;
    }

    public SpawnLocationBuilder setZ(final double z) {
        this.z = z;
        return this;
    }

    public SpawnLocationBuilder setYaw(final float yaw) {
        this.yaw = yaw;
        return this;
    }

    public SpawnLocationBuilder setPitch(final float pitch) {
        this.pitch = pitch;
        return this;
    }

    public Location toLoc() {
        return new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch);
    }

    @Override
    public String toString() {
        return "world= " + this.world.getName() + ",x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",yaw=" + this.yaw + ",pitch=" + this.pitch;
    }
}
