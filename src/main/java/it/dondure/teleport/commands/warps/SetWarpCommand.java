package it.dondure.teleport.commands.warps;

import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.utils.MessageUtils;
import it.dondure.teleport.warps.Warp;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetWarpCommand extends AbstractCommand {
    private FileConfiguration messageConfig;

    public SetWarpCommand() {
        super("setwarp", "teleportsystem.setwarp", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(MessageUtils.color(this.messageConfig.getString("warp_name")));
            return;
        }
        Warp warp = new Warp(p.getLocation(), args[0]);
        if (!TeleportSystem.getInstance().getWarpManager().existsWarp(warp)) {
            TeleportSystem.getInstance().getWarpManager().addWarp(warp);
            p.sendMessage(MessageUtils.color(Objects.requireNonNull(this.messageConfig.getString("add_warp")).replace("%name%", args[0])));
        } else {
            p.sendMessage(MessageUtils.color(this.messageConfig.getString("warp_alredy_exists")));
        }
    }

    @Override
    public void register() {
        Objects.requireNonNull(TeleportSystem.getInstance().getCommand(this.getCommandName())).setExecutor(this);
        this.messageConfig = TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration();
    }
}
