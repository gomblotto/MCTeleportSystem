package it.dondure.teleport.commands.teleportrequests.response;

import it.dondure.TeleportSystem;
import it.dondure.teleport.EntityTargetTeleport;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DenyRequestTeleportCommand extends AbstractCommand {
    public DenyRequestTeleportCommand() {
        super("tpdeny", "teleportsystem.tpaccept", false);
    }
    private FileConfiguration fileConfiguration;

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if(TeleportSystem.getInstance().getTeleport().getRequestManager().isPresentPlayer(p)){
            EntityTargetTeleport targetTeleport = TeleportSystem.getInstance().getTeleport().getRequestManager().getEntityTargetFromPlayerTarget(p);
            if(targetTeleport != null) {
               Player from =  targetTeleport.getFrom();
               Player target = targetTeleport.getTarget();
                from.sendMessage(MessageUtils.color(Objects.requireNonNull(fileConfiguration.getString("request_declined_from")).replace("%player%", target.getName())));
                target.sendMessage(MessageUtils.color(Objects.requireNonNull(fileConfiguration.getString("request_declined_target"))));
            }
            TeleportSystem.getInstance().getTeleport().getRequestManager().removeRequest(targetTeleport);

        }else{
            p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("no_tpa_request")));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
        this.fileConfiguration = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();


    }
}
