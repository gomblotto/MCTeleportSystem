package it.dondure.teleport.module;

import it.dondure.TeleportSystem;
import it.dondure.teleport.listeners.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerModule implements ModuleService {
    @Override
    public void init() {
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new QuitEvent(), (Plugin) TeleportSystem.getInstance());
    }
}
