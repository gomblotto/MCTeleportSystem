package it.dondure.teleport.listeners;

import it.dondure.TeleportSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    public QuitEvent(){
        TeleportSystem.getInstance().getServer().getPluginManager().registerEvents(this, TeleportSystem.getInstance());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        TeleportSystem.getInstance().getTeleport().getRequestManager().removeTotally(e.getPlayer());
    }
}
