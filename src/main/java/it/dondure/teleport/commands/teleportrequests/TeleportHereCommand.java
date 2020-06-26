package it.dondure.teleport.commands.teleportrequests;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.teleports.EntityTargetTeleport;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeleportHereCommand extends AbstractCommand {
    private FileConfiguration messages;

    public TeleportHereCommand() {
        super("tphere", "teleportsystem.teleporthere", false);
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        final Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage((String) Objects.requireNonNull(MessageUtils.color(this.messages.getString("type_name"))));
            return;
        }
        final Player target = Bukkit.getPlayer(args[0]);
        if (this.isOnline(target)) {
            final EntityTargetTeleport entityRequestTeleport = new EntityTargetTeleport(player, target, player.getLocation());
            entityRequestTeleport.teleportTarget();
        } else {
            player.sendMessage((String) Objects.requireNonNull(MessageUtils.color(this.messages.getString("online"))));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor((CommandExecutor) this);
        this.messages = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }

    private boolean isOnline(final Player player) {
        return player != null && player.isOnline();
    }
}
