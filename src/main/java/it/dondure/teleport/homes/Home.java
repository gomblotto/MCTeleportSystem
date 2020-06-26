package it.dondure.teleport.homes;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;

public class Home {
    private String owner;
    private Location home;

    @Override
    public String toString() {
        return this.owner + "," + this.home.getWorld().getName() + "," + this.home.getX() + "," + this.home.getY() + "," + this.home.getZ() + "," + this.home.getYaw() + "," + this.home.getPitch();
    }

    public void teleport() {
        Objects.requireNonNull(Bukkit.getPlayer(this.owner)).teleport(this.home);
    }

    public Home(String owner, Location home) {
        this.owner = owner;
        this.home = home;
    }

    public String getOwner() {
        return this.owner;
    }

    public Location getHome() {
        return this.home;
    }
}
