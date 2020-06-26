package it.dondure.teleport.commands.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import it.dondure.teleport.utils.WarpUtils;
import it.dondure.teleport.warps.WarpLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DelWarpCommand extends AbstractCommand {
    private WarpLoader warpLoader;
    private FileConfiguration messageConfig;
    public DelWarpCommand() {
        super("delwarp", "teleportsystem.delwarp", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0){
            p.sendMessage(MessageUtils.color(messageConfig.getString("warp_name")));
            return;
        }
    if(WarpUtils.getWarpFromName(warpLoader.getWarps(), args[0]) != null) {
            warpLoader.deleteWarp(WarpUtils.getWarpFromName(warpLoader.getWarps(), args[0]));
            p.sendMessage(MessageUtils.color(Objects.requireNonNull(messageConfig.getString("del_warp")).replace("%name%", args[0])));
    }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(getCommandName())).setExecutor(this);
        this.warpLoader = TeleportSystem.getInstance().getTeleport().getWarpLoader();
        this.messageConfig = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();

    }


}
