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

    public SpawnLocationBuilder setWorld(World world) {
        this.world = world;
        return this;
    }

    public SpawnLocationBuilder setX(double x) {
        this.x = x;
        return this;
    }

    public SpawnLocationBuilder setY(double y) {
        this.y = y;
        return this;
    }

    public SpawnLocationBuilder setZ(double z) {
        this.z = z;
        return this;
    }

    public SpawnLocationBuilder setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }

    public SpawnLocationBuilder setPitch(float pitch) {
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
