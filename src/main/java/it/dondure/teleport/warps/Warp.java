package it.dondure.teleport.warps;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Warp {
    private final Location warp;
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    public void teleportPlayer(final Player player) {
        player.teleport(this.warp);
    }

    public Warp(final Location warp, final String name) {
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
