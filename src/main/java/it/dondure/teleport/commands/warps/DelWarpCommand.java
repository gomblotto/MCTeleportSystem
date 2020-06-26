package it.dondure.teleport.commands.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import it.dondure.teleport.utils.WarpUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DelWarpCommand extends AbstractCommand {
    private FileConfiguration messageConfig;

    public DelWarpCommand() {
        super("delwarp", "teleportsystem.delwarp", false);
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        final Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(MessageUtils.color(this.messageConfig.getString("warp_name")));
            return;
        }
        if (WarpUtils.getWarpFromName(TeleportSystem.getInstance().getWarpManager().getWarps(), args[0]) != null) {
            TeleportSystem.getInstance().getWarpManager().deleteWarp(WarpUtils.getWarpFromName(TeleportSystem.getInstance().getWarpManager().getWarps(), args[0]));
            p.sendMessage(MessageUtils.color(Objects.requireNonNull(this.messageConfig.getString("del_warp")).replace("%name%", args[0])));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor((CommandExecutor) this);
        this.messageConfig = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }
}
