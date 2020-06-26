package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnConfig implements ConfigService {
    private File file;
    private FileConfiguration yamlConfiguration;

    @Override
    public void init() {
        this.file = new File(TeleportSystem.getInstance().getDataFolder(), "spawn.yml");
        this.create(true);
    }

    @Override
    public void saveConfig() {
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        try {
            this.yamlConfiguration.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void create(boolean saveResource) {
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            if (!saveResource) {
                try {
                    this.file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                TeleportSystem.getInstance().saveResource("spawn.yml", TeleportSystem.getInstance().getResource("spawn.yml") == null);
            }
        }
        this.load();
    }

    public void setSpawn(String world, double x, double y, double z, float yaw, float pitch) {
        this.yamlConfiguration.set("spawn.world", world);
        this.yamlConfiguration.set("spawn.x", x);
        this.yamlConfiguration.set("spawn.y", y);
        this.yamlConfiguration.set("spawn.z", z);
        this.yamlConfiguration.set("spawn.yaw", yaw);
        this.yamlConfiguration.set("spawn.pitch", pitch);
        this.saveConfig();
    }

    public FileConfiguration getYamlConfiguration() {
        return this.yamlConfiguration;
    }
}
