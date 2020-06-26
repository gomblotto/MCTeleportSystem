package it.dondure.teleport.commands.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.ModuleLoader;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import it.dondure.teleport.utils.WarpUtils;
import it.dondure.teleport.warps.Warp;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class WarpCommand extends AbstractCommand {
    private ModuleLoader teleport;
    private FileConfiguration messageConfig;

    public WarpCommand() {
        super("warp", "teleportsystem.warp", false);
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        final Player p = (Player) sender;
        if (args.length == 0) {
            WarpUtils.sendWarpMessage(MessageUtils.color(this.messageConfig.getString("no_perm_warp_view")), MessageUtils.color(this.messageConfig.getString("no_warps")), MessageUtils.color(this.messageConfig.getString("warp_list")), TeleportSystem.getInstance().getWarpManager().getWarps(), p);
            return;
        }
        if (p.hasPermission("teleportsystem.warp." + args[0].toLowerCase()) || p.hasPermission("teleportsystem.warp.*")) {
            final Warp warp = WarpUtils.getWarpFromName(TeleportSystem.getInstance().getWarpManager().getWarps(), args[0]);
            if (warp != null) {
                warp.teleportPlayer(p);
                p.sendMessage(MessageUtils.color(Objects.requireNonNull(this.messageConfig.getString("warping")).replace("%name%", warp.getName())));
            } else {
                p.sendMessage(MessageUtils.color(this.messageConfig.getString("no_warp_exists")));
            }
        } else {
            p.sendMessage(MessageUtils.color(this.messageConfig.getString("no_perm_warp_teleport")));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor((CommandExecutor) this);
        this.messageConfig = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }
}
