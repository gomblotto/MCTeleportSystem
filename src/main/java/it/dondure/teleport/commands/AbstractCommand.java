package it.dondure.teleport.commands;

import it.dondure.TeleportSystem;
import it.dondure.teleport.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor {
    private final String commandName;
    private final String permission;
    private final boolean canConsoleUse;

    public boolean onCommand(final CommandSender sender, final Command cmd, final String str, final String[] args) {
        if (!cmd.getLabel().equalsIgnoreCase(this.commandName)) {
            return true;
        }
        if (!sender.hasPermission(this.permission)) {
            sender.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("no_perm")));
            return true;
        }
        if (!this.canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command sorry!");
            return true;
        }
        this.execute(sender, args);
        return true;
    }

    public abstract void execute(final CommandSender p0, final String[] p1);

    public abstract void register();

    public AbstractCommand(final String commandName, final String permission, final boolean canConsoleUse) {
        this.commandName = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
    }

    public String getCommandName() {
        return this.commandName;
    }
}
