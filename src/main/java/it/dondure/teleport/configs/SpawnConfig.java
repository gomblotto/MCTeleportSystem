package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnConfig implements ConfigService {
    private File file;
    @Getter private FileConfiguration yamlConfiguration;
    @Override
    public void init() {
        file = new File(TeleportSystem.getInstance().getDataFolder(), "spawn.yml");
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
    public void reload() {
        yamlConfiguration.setDefaults(YamlConfiguration.loadConfiguration(file));
    }
    @Override
    public void load() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
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
                if (TeleportSystem.getInstance().getResource("spawn.yml") == null) {
                    TeleportSystem.getInstance().saveResource("spawn.yml", true);
                } else {
                    TeleportSystem.getInstance().saveResource("spawn.yml", false);
                }
            }

        }
        load();
    }
    public void setSpawn(String world, double x, double y, double z, float yaw,float pitch){
        yamlConfiguration.set("spawn.world", world);
        yamlConfiguration.set("spawn.x", x);
        yamlConfiguration.set("spawn.y", y);
        yamlConfiguration.set("spawn.z", z);
        yamlConfiguration.set("spawn.yaw", yaw);
        yamlConfiguration.set("spawn.pitch", pitch);

        saveConfig();
    }
}
