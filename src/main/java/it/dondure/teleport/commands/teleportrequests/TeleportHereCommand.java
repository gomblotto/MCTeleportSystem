package it.dondure.teleport.commands.teleportrequests;

import it.dondure.TeleportSystem;
import it.dondure.teleport.EntityTargetTeleport;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.configs.MessagesConfig;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeleportHereCommand extends AbstractCommand {

    public TeleportHereCommand() {
        super("tphere", "teleportsystem.teleporthere", false);
    }
    private FileConfiguration messages;
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0){
            player.sendMessage(Objects.requireNonNull(MessageUtils.color(messages.getString("type_name"))));
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if(isOnline(target)){
            EntityTargetTeleport entityRequestTeleport = new EntityTargetTeleport(player,target, player.getLocation());
            entityRequestTeleport.teleportTarget();
        }else{
            player.sendMessage(Objects.requireNonNull(MessageUtils.color(messages.getString("online"))));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
        this.messages = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }

    private boolean isOnline(Player player){
        if(player != null){
            return player.isOnline();
        }
        return false;
    }
}
