package it.dondure.teleport.commands.home;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HomeCommand extends AbstractCommand {
    public HomeCommand() {
        super("home", "teleportsystem.home", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            Player p = (Player) sender;
            if (TeleportSystem.getInstance().getHomeManager().getHomeFromPlayer(p) == null) {
                p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("no_home_found")));
            } else {
                TeleportSystem.getInstance().getHomeManager().getHomeFromPlayer(p).teleport();
            }
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor(this);
    }
}
