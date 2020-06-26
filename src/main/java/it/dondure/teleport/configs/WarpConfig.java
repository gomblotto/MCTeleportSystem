package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class WarpConfig implements ConfigService{
    private File file;
    private FileConfiguration yamlConfiguration;
    @Override
    public void init() {
        file = new File(TeleportSystem.getInstance().getDataFolder(), "warps.yml");
        create(true);
    }
    @Override
    public void reload() {
        yamlConfiguration.setDefaults(YamlConfiguration.loadConfiguration(file));
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
                if (TeleportSystem.getInstance().getResource("warps.yml") == null) {
                    TeleportSystem.getInstance().saveResource("warps.yml", true);
                } else {
                    TeleportSystem.getInstance().saveResource("warps.yml", false);
                }
            }
        }
        load();
    }

}
