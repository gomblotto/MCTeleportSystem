package it.dondure.teleport.commands.spawn;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetSpawnCommand extends AbstractCommand {
    public SetSpawnCommand() {
        super("setspawn", "teleportsystem.setspawn", false);
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        TeleportSystem.getInstance().getConfigManager().getSpawnConfig().setSpawn(p.getWorld().getName(), p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
        p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("set_spawn")));
        }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
    }


}
