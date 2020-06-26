package it.dondure.teleport.teleports;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EntityTargetTeleport {
    private final Player from;
    private final Player target;
    private Location destination;

    public void teleportTarget() {
        if (!this.target.getAllowFlight()) {
            while (this.destination.getBlock().getType() == Material.AIR) {
                this.destination = new Location(this.destination.getWorld(), this.destination.getX(), this.destination.getY() - 1.0, this.destination.getZ());
            }
        }
        this.destination = new Location(this.destination.getWorld(), this.destination.getX(), this.destination.getY() + 1.0, this.destination.getZ());
        this.target.teleport(this.destination);
    }

    public void teleportFrom() {
        if (!this.from.getAllowFlight()) {
            while (this.destination.getBlock().getType() == Material.AIR) {
                this.destination = new Location(this.destination.getWorld(), this.destination.getX(), this.destination.getY() - 1.0, this.destination.getZ());
            }
            this.destination = new Location(this.destination.getWorld(), this.destination.getX(), this.destination.getY() + 1.0, this.destination.getZ());
        }
        this.from.teleport(this.destination);
    }

    public EntityTargetTeleport(final Player from, final Player target, final Location destination) {
        this.from = from;
        this.target = target;
        this.destination = destination;
    }

    public Player getFrom() {
        return this.from;
    }

    public Player getTarget() {
        return this.target;
    }

    public void setDestination(final Location destination) {
        this.destination = destination;
    }

    public Location getDestination() {
        return this.destination;
    }
}
