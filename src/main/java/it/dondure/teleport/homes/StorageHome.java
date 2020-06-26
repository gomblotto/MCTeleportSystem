package it.dondure.teleport.homes;

import it.dondure.TeleportSystem;
import org.bukkit.scheduler.BukkitRunnable;

public class StorageHome extends BukkitRunnable {
    public void run() {
        if (!TeleportSystem.getInstance().getHomeManager().getHomeList().isEmpty()) {
            TeleportSystem.getInstance().getPlayerDataStorage().saveHome(TeleportSystem.getInstance().getHomeManager().getHomeList());
        }
    }
}
