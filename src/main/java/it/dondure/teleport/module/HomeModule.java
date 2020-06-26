package it.dondure.teleport.module;

import it.dondure.TeleportSystem;
import it.dondure.teleport.homes.HomeManager;
import it.dondure.teleport.homes.StorageHome;
import org.bukkit.plugin.Plugin;

public class HomeModule implements ModuleService {
    private HomeManager homeManager;

    @Override
    public void init() {
        this.homeManager = new HomeManager();
        new StorageHome().runTaskTimerAsynchronously(TeleportSystem.getInstance(), 1200L, 1200L);
    }

    public HomeManager getHomeManager() {
        return this.homeManager;
    }
}
