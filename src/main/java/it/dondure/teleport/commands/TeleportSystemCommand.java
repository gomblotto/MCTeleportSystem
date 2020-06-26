package it.dondure.teleport.commands;

import it.dondure.TeleportSystem;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeleportSystemCommand extends AbstractCommand {
    public TeleportSystemCommand() {
        super("tsy", "teleportsystem.use", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(" §7§m--------[- §aTeleport§7System §7§m-]--------");
            p.sendMessage(" §7§l>§e /tsy reload§7 for reload config");
            return;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            p.sendMessage("§7All config are reloaded!");
            TeleportSystem.getInstance().getConfigManager().reloadConfig();
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor(this);
    }
}
