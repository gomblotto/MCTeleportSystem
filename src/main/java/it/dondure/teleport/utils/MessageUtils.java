package it.dondure.teleport.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageUtils {
    public static void sendMultipleLine(final Player p, final String target, final List<String> stringList) {
        for (final String string : stringList) {
            p.sendMessage(color(string).replace("%player%", target));
        }
    }

    public static String color(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
