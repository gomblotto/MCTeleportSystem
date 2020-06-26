package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final List<ConfigService> configServices = new ArrayList<>();
    @Getter private final WarpConfig warpConfig;
    @Getter private final SpawnConfig spawnConfig;
    @Getter private final MessagesConfig messagesConfig;
    public ConfigManager() {
        this.warpConfig = new WarpConfig();
        this.spawnConfig = new SpawnConfig();
        this.messagesConfig = new MessagesConfig();
    }

    public void loadConfig(){
        configServices.add(warpConfig);
        configServices.add(spawnConfig);
        configServices.add(messagesConfig);
        configServices.forEach(ConfigService::init);
    }
    public void reloadConfig(){
        configServices.forEach(ConfigService::reload);
    }
}
