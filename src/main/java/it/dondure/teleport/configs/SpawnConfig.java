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

    public void create(final boolean saveResource) {
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

    public void setSpawn(final String world, final double x, final double y, final double z, final float yaw, final float pitch) {
        this.yamlConfiguration.set("spawn.world", (Object) world);
        this.yamlConfiguration.set("spawn.x", (Object) x);
        this.yamlConfiguration.set("spawn.y", (Object) y);
        this.yamlConfiguration.set("spawn.z", (Object) z);
        this.yamlConfiguration.set("spawn.yaw", (Object) yaw);
        this.yamlConfiguration.set("spawn.pitch", (Object) pitch);
        this.saveConfig();
    }

    public FileConfiguration getYamlConfiguration() {
        return this.yamlConfiguration;
    }
}
