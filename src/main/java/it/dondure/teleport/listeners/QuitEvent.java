package it.dondure.teleport.listeners;

import it.dondure.TeleportSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        TeleportSystem.getInstance().getRequestManager().removeTotally(e.getPlayer());
    }
}
