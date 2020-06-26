package it.dondure.teleport.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageUtils {
    public static void sendMultipleLine(Player p, String target, List<String> stringList) {
        for (String string : stringList) {
            p.sendMessage(color(string).replace("%player%", target));
        }
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
