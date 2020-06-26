package it.dondure.teleport.commands;

import it.dondure.TeleportSystem;
import it.dondure.teleport.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public abstract class AbstractCommand implements CommandExecutor {
    @Getter private final String commandName;
    private final String permission;
    private final boolean canConsoleUse;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if(!cmd.getLabel().equalsIgnoreCase(commandName))
            return true;
        if(!sender.hasPermission(permission)){
            sender.sendMessage(MessageUtils.color(TeleportSystem.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("no_perm")));
            return true;
        }
        if(!canConsoleUse && !(sender instanceof Player)){
            sender.sendMessage("Only players may use this command sorry!");
            return true;
        }
        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public abstract void register();


}
