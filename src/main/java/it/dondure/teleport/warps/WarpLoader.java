package it.dondure.teleport.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.configs.WarpConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.DoubleAccumulator;

public class WarpLoader implements IWarp{
    private final FileConfiguration fileConfiguration;
    private final WarpConfig warpConfig;
    @Getter private final List<Warp> warps = new ArrayList<>();
    public WarpLoader() {
        this.warpConfig = TeleportSystem.getInstance().getConfigManager().getWarpConfig();
        fileConfiguration = TeleportSystem.getInstance().getConfigManager().getWarpConfig().getYamlConfiguration();
    }
    @Override
    public void loadWarps() {
        if(fileConfiguration.getConfigurationSection("warps") == null) {
            fileConfiguration.createSection("warps");
            return;
        }
        for(String namewarp : fileConfiguration.getConfigurationSection("warps").getKeys(false)){
            Map<String, Object> map = Objects.requireNonNull(fileConfiguration.getConfigurationSection("warps")).getConfigurationSection(namewarp).getValues(true);
            if(map.get("world") != null) {
                if(Bukkit.getWorlds().contains(Bukkit.getWorld(String.valueOf(map.get("world"))))) {
                    warps.add(new Warp(new Location(Bukkit.getWorld(String.valueOf(map.get("world"))), (double) map.get("x"), (double) map.get("y"), (double) map.get("z"), Float.parseFloat(String.valueOf(map.get("yaw"))), Float.parseFloat(String.valueOf(map.get("pitch")))), namewarp));
                }else{
                    TeleportSystem.getInstance().getLogger().info("Error with warp " + namewarp + ": ERROR NAME WORLD");
                }
            }
        }
        }

    @Override
    public void addWarp(Warp warp) {
        warps.add(warp);
        ConfigurationSection section = fileConfiguration.getConfigurationSection("warps");
        if (section != null && section.getConfigurationSection(warp.getName()) == null) {
            section.createSection(warp.getName());
        }
        ConfigurationSection section1 = null;
        if (section != null) {
            section1 = section.getConfigurationSection(warp.getName());
            if (section1 != null) {
                section1.set("x", warp.getWarp().getX());
                section1.set("y", warp.getWarp().getY());
                section1.set("z", warp.getWarp().getZ());
                section1.set("yaw", warp.getWarp().getYaw());
                section1.set("pitch", warp.getWarp().getPitch());
                section1.set("world", warp.getWarp().getWorld().getName());
            }
        }
        warpConfig.saveConfig();
    }

    @Override
    public void deleteWarp(Warp warp) {
        warps.remove(warp);
        ConfigurationSection section = fileConfiguration.getConfigurationSection("warps");
        if (section != null && section.getConfigurationSection(warp.getName()) != null) {
            Map<String, Object> configValues = Objects.requireNonNull(section.getConfigurationSection(warp.getName())).getValues(false);
            for (Map.Entry<String, Object> entry : configValues.entrySet()) {
                Objects.requireNonNull(section.getConfigurationSection(warp.getName())).set(entry.getKey(), null);
            }
            section.set(warp.getName(), null);
            warpConfig.saveConfig();
        }
    }

    @Override
    public boolean existsWarp(Warp warp) {
        return  warps.contains(warp);
    }

}
