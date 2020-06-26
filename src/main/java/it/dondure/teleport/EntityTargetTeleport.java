package it.dondure.teleport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class EntityTargetTeleport {
    @Getter private final Player from;
    @Getter private final Player target;
   @Setter
   @Getter private Location destination;



    public void teleportTarget(){
        if (!target.getAllowFlight()) {
            while (destination.getBlock().getType() == Material.AIR) {
                destination = new Location(destination.getWorld(), destination.getX(), destination.getY() - 1, destination.getZ());
            }
        }
        destination = new Location(destination.getWorld(), destination.getX(), destination.getY() + 1, destination.getZ());
        target.teleport(destination);
    }
    public void teleportFrom(){
        if (!from.getAllowFlight()) {
            while (destination.getBlock().getType() == Material.AIR) {
                destination = new Location(destination.getWorld(), destination.getX(), destination.getY() - 1, destination.getZ());
            }
            destination = new Location(destination.getWorld(), destination.getX(), destination.getY() + 1, destination.getZ());

        }
        from.teleport(destination);
    }

}
