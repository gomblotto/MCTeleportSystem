package it.dondure.teleport.commands.home;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.homes.Home;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetHomeCommand extends AbstractCommand {
    public SetHomeCommand() {
        super("sethome", "teleportsystem.sethome", false);
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 0) {
            final Player p = (Player) sender;
            p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("set_home")));
            if (TeleportSystem.getInstance().getHomeManager().getHomeFromPlayer(p) != null) {
                TeleportSystem.getInstance().getHomeManager().removeHome(TeleportSystem.getInstance().getHomeManager().getHomeFromPlayer(p));
            }
            TeleportSystem.getInstance().getHomeManager().addHome(new Home(p.getName(), p.getLocation()));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor((CommandExecutor) this);
    }
}
