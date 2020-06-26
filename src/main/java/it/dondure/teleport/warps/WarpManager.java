package it.dondure.teleport.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.configs.WarpConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WarpManager {
    private final FileConfiguration fileConfiguration;
    private final WarpConfig warpConfig;
    private final List<Warp> warps;

    public WarpManager() {
        this.warps = new ArrayList<Warp>();
        this.warpConfig = TeleportSystem.getInstance().getConfigManager().getWarpConfig();
        this.fileConfiguration = TeleportSystem.getInstance().getConfigManager().getWarpConfig().getYamlConfiguration();
    }

    public void loadWarps() {
        if (this.fileConfiguration.getConfigurationSection("warps") == null) {
            this.fileConfiguration.createSection("warps");
            return;
        }
        for (final String namewarp : this.fileConfiguration.getConfigurationSection("warps").getKeys(false)) {
            final Map<String, Object> map = (Map<String, Object>) Objects.requireNonNull(this.fileConfiguration.getConfigurationSection("warps")).getConfigurationSection(namewarp).getValues(true);
            if (map.get("world") != null) {
                if (Bukkit.getWorlds().contains(Bukkit.getWorld(String.valueOf(map.get("world"))))) {
                    this.warps.add(new Warp(new Location(Bukkit.getWorld(String.valueOf(map.get("world"))), (double) map.get("x"), (double) map.get("y"), (double) map.get("z"), Float.parseFloat(String.valueOf(map.get("yaw"))), Float.parseFloat(String.valueOf(map.get("pitch")))), namewarp));
                } else {
                    TeleportSystem.getInstance().getLogger().info("Error with warp " + namewarp + ": ERROR NAME WORLD");
                }
            }
        }
    }

    public void addWarp(final Warp warp) {
        this.warps.add(warp);
        final ConfigurationSection section = this.fileConfiguration.getConfigurationSection("warps");
        if (section != null && section.getConfigurationSection(warp.getName()) == null) {
            section.createSection(warp.getName());
        }
        ConfigurationSection section2 = null;
        if (section != null) {
            section2 = section.getConfigurationSection(warp.getName());
            if (section2 != null) {
                section2.set("x", (Object) warp.getWarp().getX());
                section2.set("y", (Object) warp.getWarp().getY());
                section2.set("z", (Object) warp.getWarp().getZ());
                section2.set("yaw", (Object) warp.getWarp().getYaw());
                section2.set("pitch", (Object) warp.getWarp().getPitch());
                section2.set("world", (Object) warp.getWarp().getWorld().getName());
            }
        }
        this.warpConfig.saveConfig();
    }

    public void deleteWarp(final Warp warp) {
        this.warps.remove(warp);
        final ConfigurationSection section = this.fileConfiguration.getConfigurationSection("warps");
        if (section != null && section.getConfigurationSection(warp.getName()) != null) {
            final Map<String, Object> configValues = (Map<String, Object>) Objects.requireNonNull(section.getConfigurationSection(warp.getName())).getValues(false);
            for (final Map.Entry<String, Object> entry : configValues.entrySet()) {
                Objects.requireNonNull(section.getConfigurationSection(warp.getName())).set((String) entry.getKey(), (Object) null);
            }
            section.set(warp.getName(), (Object) null);
            this.warpConfig.saveConfig();
        }
    }

    public boolean existsWarp(final Warp warp) {
        return this.warps.contains(warp);
    }

    public List<Warp> getWarps() {
        return this.warps;
    }
}
