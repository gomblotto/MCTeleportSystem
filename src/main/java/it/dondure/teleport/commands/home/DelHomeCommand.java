package it.dondure.teleport.commands.home;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand extends AbstractCommand {
    public SetHomeCommand() {
        super("home", "teleportsystem.sethome", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0){
            Player p = (Player) sender;
                p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("set_home")));
               TeleportSystem.getInstance().getHomeManager().getHomeFromPlayer(p).teleport();
            }
        }


    @Override
    public void register() {

    }
}
