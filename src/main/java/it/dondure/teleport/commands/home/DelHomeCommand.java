package it.dondure.teleport.commands.home;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DelHomeCommand extends AbstractCommand {
    public DelHomeCommand() {
        super("delhome", "teleportsystem.delhome", false);
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 0) {
            final Player p = (Player) sender;
            p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("del_home")));
            TeleportSystem.getInstance().getHomeManager().removeHome(TeleportSystem.getInstance().getHomeManager().getHomeFromPlayer(p));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor((CommandExecutor) this);
    }
}
