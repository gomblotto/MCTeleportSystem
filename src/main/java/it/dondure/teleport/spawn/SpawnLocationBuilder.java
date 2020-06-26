package it.dondure.teleport.spawn;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnLocationBuilder {
    private double x,y,z;
    private float yaw,pitch;
    private World world;
    public SpawnLocationBuilder setWorld(World world){
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
    public SpawnLocationBuilder teleport(Player player){
        player.teleport(new Location(world,x,y,z,yaw,pitch));
        return this;
    }

    @Override
    public String toString() {
        return "world= " + world.getName() +  ",x=" + x+ ",y="+ y + ",z=" + z +  ",yaw=" + yaw + ",pitch=" + pitch;
    }
}
