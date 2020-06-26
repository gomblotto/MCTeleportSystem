package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WarpConfig implements ConfigService {
    private File file;
    private FileConfiguration yamlConfiguration;

    @Override
    public void init() {
        this.file = new File(TeleportSystem.getInstance().getDataFolder(), "warps.yml");
        this.create(true);
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
    public void saveConfig() {
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
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
            } else
                TeleportSystem.getInstance().saveResource("warps.yml", TeleportSystem.getInstance().getResource("warps.yml") == null);
        }
        this.load();
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getYamlConfiguration() {
        return this.yamlConfiguration;
    }
}
