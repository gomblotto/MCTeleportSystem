package it.dondure;

import it.dondure.teleport.ModuleLoader;
import it.dondure.teleport.configs.ConfigManager;
import it.dondure.teleport.database.PlayerDataStorage;
import it.dondure.teleport.homes.Home;
import it.dondure.teleport.homes.HomeManager;
import it.dondure.teleport.teleports.RequestManager;
import it.dondure.teleport.warps.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TeleportSystem extends JavaPlugin {
    private ConfigManager configManager;
    private ModuleLoader moduleLoader;
    private PlayerDataStorage playerDataStorage;
    private static TeleportSystem instance;

    public void onEnable() {
        TeleportSystem.instance = this;
        (this.configManager = new ConfigManager()).loadConfig();
        this.load();
        try {
            this.loadDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.getLogger().info("TeleportSystem loaded!");
    }

    private void load() {
        (this.moduleLoader = new ModuleLoader()).load();
    }

    private void loadDatabase() throws ClassNotFoundException {
        (this.playerDataStorage = new PlayerDataStorage(this.configManager.getHomeSQL().getFile().getAbsolutePath())).openConnection();
        this.playerDataStorage.createNewTable();
        for (final Home homes : this.playerDataStorage.allHomes()) {
            this.getHomeManager().addHome(homes);
        }
    }

    public void onDisable() {
        this.playerDataStorage.saveHome(this.getHomeManager().getHomeList());
        this.playerDataStorage.closeConnection();
    }

    public WarpManager getWarpManager() {
        return this.moduleLoader.getWarpModule().getWarpManager();
    }

    public RequestManager getRequestManager() {
        return this.moduleLoader.getTeleportModule().getRequestManager();
    }

    public HomeManager getHomeManager() {
        return this.moduleLoader.getHomeModule().getHomeManager();
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public ModuleLoader getModuleLoader() {
        return this.moduleLoader;
    }

    public PlayerDataStorage getPlayerDataStorage() {
        return this.playerDataStorage;
    }

    public static TeleportSystem getInstance() {
        return TeleportSystem.instance;
    }
}
