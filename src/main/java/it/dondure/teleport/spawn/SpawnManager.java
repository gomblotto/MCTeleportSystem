package it.dondure.teleport.spawn;

import it.dondure.TeleportSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;

public class SpawnManager {
    private Location spawn;

    public void load() {
        this.setSpawn(new SpawnLocationBuilder().setX(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getDouble("spawn.x")).setY(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getDouble("spawn.y")).setZ(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getDouble("spawn.z")).setWorld(Bukkit.getWorld((String) Objects.requireNonNull(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getString("spawn.world")))).toLoc());
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }
}
