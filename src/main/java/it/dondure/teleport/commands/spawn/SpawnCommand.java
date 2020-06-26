package it.dondure.teleport.commands.spawn;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.spawn.SpawnLocationBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand extends AbstractCommand {
    public SpawnCommand() {
        super("spawn", "teleportsystem.spawn", false);
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        new SpawnLocationBuilder()
                .setX(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getDouble("spawn.x"))
                .setY(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getDouble("spawn.y"))
                .setZ(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getDouble("spawn.z"))
                .setWorld(Bukkit.getWorld(Objects.requireNonNull(TeleportSystem.getInstance().getConfigManager().getSpawnConfig().getYamlConfiguration().getString("spawn.world"))))
                .teleport(p);
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
    }


}
