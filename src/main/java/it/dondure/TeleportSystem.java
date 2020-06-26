package it.dondure;


import it.dondure.teleport.Teleport;
import it.dondure.teleport.configs.ConfigManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;


public class TeleportSystem extends JavaPlugin {
    @Getter private ConfigManager configManager;
    @Getter private Teleport teleport;
    @Getter private static TeleportSystem instance;
    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        configManager.loadConfig();
        teleport = new Teleport();
        teleport.init();
        getLogger().info("TeleportSystem loaded!");
    }

    @Override
    public void onDisable() {

    }



}
