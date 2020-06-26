package it.dondure.teleport.commands.teleportrequests.response;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.teleports.EntityTargetTeleport;
import it.dondure.teleport.teleports.TypeRequest;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AcceptRequestTeleportCommand extends AbstractCommand {
    private FileConfiguration fileConfiguration;

    public AcceptRequestTeleportCommand() {
        super("tpaccept", "teleportsystem.tpaccept", false);
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        final Player p = (Player) sender;
        if (TeleportSystem.getInstance().getRequestManager().isPresentPlayer(p)) {
            final EntityTargetTeleport targetTeleport = TeleportSystem.getInstance().getRequestManager().getEntityTargetFromPlayerTarget(p);
            if (targetTeleport != null) {
                final Player from = targetTeleport.getFrom();
                final Player target = targetTeleport.getTarget();
                if (TeleportSystem.getInstance().getRequestManager().getTypeRequest(targetTeleport).equals(TypeRequest.TP)) {
                    targetTeleport.setDestination(target.getLocation());
                    targetTeleport.teleportFrom();
                } else {
                    targetTeleport.setDestination(from.getLocation());
                    targetTeleport.teleportTarget();
                    from.sendMessage(MessageUtils.color(Objects.requireNonNull(this.fileConfiguration.getString("request_accepted_from")).replace("%player%", target.getName())));
                    target.sendMessage(MessageUtils.color(Objects.requireNonNull(this.fileConfiguration.getString("request_accepted_target"))));
                }
            }
            TeleportSystem.getInstance().getRequestManager().removeRequest(targetTeleport);
        } else {
            p.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("no_tpa_request")));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor((CommandExecutor) this);
        this.fileConfiguration = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }
}
