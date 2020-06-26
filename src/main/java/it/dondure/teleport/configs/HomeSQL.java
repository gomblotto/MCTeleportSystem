package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfig implements ConfigService{
    private File file;
    @Getter private FileConfiguration yamlConfiguration;
    @Override
    public void init() {
        file = new File(TeleportSystem.getInstance().getDataFolder(), "messages.yml");
        create(true);
    }
    @Override
    public void saveConfig(){
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void reload() {
        System.out.println("Reloaded!");
        try {
            yamlConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void create(boolean saveResource) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (!saveResource) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                if (TeleportSystem.getInstance().getResource("messages.yml") == null) {
                    TeleportSystem.getInstance().saveResource("messages.yml", true);
                } else {
                    TeleportSystem.getInstance().saveResource("messages.yml", false);
                }
            }
        }
        load();
    }


}
