package it.dondure.teleport.commands.teleportrequests.sendrequests;

import it.dondure.TeleportSystem;
import it.dondure.teleport.EntityTargetTeleport;
import it.dondure.teleport.TypeRequest;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import it.dondure.teleport.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class RequestTeleportHereCommand extends AbstractCommand {
    public RequestTeleportHereCommand() {
        super("tpahere", "teleportsystem.tpahere", false);
    }
    private FileConfiguration messagesConfig;
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(MessageUtils.color(messagesConfig.getString("type_name")));
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (PlayerUtils.isOnline(target)) {
                    TeleportSystem.getInstance().getTeleport().getRequestManager().deleteOld(target);
                    TeleportSystem.getInstance().getTeleport().getRequestManager().addRequest(new EntityTargetTeleport(player, target, null), TypeRequest.TPHERE);
                    MessageUtils.sendMultipleLine(target, player.getName(), messagesConfig.getStringList("tpahere"));
                    player.sendMessage(MessageUtils.color(messagesConfig.getString("requesttp")));

                } else {
                    player.sendMessage(Objects.requireNonNull(MessageUtils.color(messagesConfig.getString("online"))));
                }
            }
        }.runTaskAsynchronously(TeleportSystem.getInstance());
    }
    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
        this.messagesConfig = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }



}
