package it.dondure.teleport.utils;

import it.dondure.TeleportSystem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnUtils {
    public static void teleportPlayer(Player player) {
        Location spawn = TeleportSystem.getInstance().getModuleLoader().getSpawnModule().getSpawnManager().getSpawn();
        player.teleport(spawn);
    }
}
