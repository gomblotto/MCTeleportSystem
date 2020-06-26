package it.dondure.teleport.warps;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Warp {
    private Location warp;
    private String name;

    @Override
    public String toString() {
        return this.name;
    }

    public void teleportPlayer(Player player) {
        player.teleport(this.warp);
    }

    public Warp(Location warp, String name) {
        this.warp = warp;
        this.name = name;
    }

    public Location getWarp() {
        return this.warp;
    }

    public String getName() {
        return this.name;
    }
}
