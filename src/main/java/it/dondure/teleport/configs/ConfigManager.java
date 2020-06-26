package it.dondure.teleport.configs;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final List<ConfigService> configServices;
    private final WarpConfig warpConfig;
    private final SpawnConfig spawnConfig;
    private final MessagesConfig messagesConfig;
    private final HomeSQL homeSQL;

    public ConfigManager() {
        this.configServices = new ArrayList<ConfigService>();
        this.warpConfig = new WarpConfig();
        this.spawnConfig = new SpawnConfig();
        this.messagesConfig = new MessagesConfig();
        this.homeSQL = new HomeSQL();
    }

    public void loadConfig() {
        this.configServices.add(this.warpConfig);
        this.configServices.add(this.spawnConfig);
        this.configServices.add(this.messagesConfig);
        this.configServices.add(this.homeSQL);
        this.configServices.forEach(ConfigService::init);
    }

    public void reloadConfig() {
        this.configServices.forEach(ConfigService::reload);
    }

    public WarpConfig getWarpConfig() {
        return this.warpConfig;
    }

    public SpawnConfig getSpawnConfig() {
        return this.spawnConfig;
    }

    public MessagesConfig getMessagesConfig() {
        return this.messagesConfig;
    }

    public HomeSQL getHomeSQL() {
        return this.homeSQL;
    }
}
