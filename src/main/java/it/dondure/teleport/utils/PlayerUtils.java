package it.dondure.teleport.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {
    public static boolean isOnline(final Player player) {
        return player != null && player.isOnline();
    }
}
