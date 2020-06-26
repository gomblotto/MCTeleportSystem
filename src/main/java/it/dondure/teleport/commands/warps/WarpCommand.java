package it.dondure.teleport.commands.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.Teleport;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import it.dondure.teleport.utils.WarpUtils;
import it.dondure.teleport.warps.Warp;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class WarpCommand extends AbstractCommand {
    private Teleport teleport;
    private FileConfiguration messageConfig;
    public WarpCommand() {
        super("warp", "teleportsystem.warp", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0) {
            WarpUtils.sendWarpMessage(
                    MessageUtils.color(messageConfig.getString("no_perm_warp_view")),
                    MessageUtils.color(messageConfig.getString("no_warps")),
                    MessageUtils.color(messageConfig.getString("warp_list")),
                    teleport.getWarpLoader().getWarps(),
                    p);
          return;
        }
        if (p.hasPermission("teleportsystem.warp." + args[0].toLowerCase()) || p.hasPermission("teleportsystem.warp.*")) {
            Warp warp = WarpUtils.getWarpFromName( teleport.getWarpLoader().getWarps(), args[0]);
            if(warp != null){
                warp.teleportPlayer(p);
                p.sendMessage(MessageUtils.color(Objects.requireNonNull(messageConfig.getString("warping")).replace("%name%", warp.getName())));
            }else{
                p.sendMessage(MessageUtils.color(messageConfig.getString("no_warp_exists")));
            }
        }else{
            p.sendMessage(MessageUtils.color(messageConfig.getString("no_perm_warp_teleport")));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
        teleport = TeleportSystem.getInstance().getTeleport();
        this.messageConfig = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }

    }
