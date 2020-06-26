package it.dondure.teleport.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {
    public static boolean isOnline(Player player){
        if(player != null){
            return player.isOnline();
        }
        return false;
    }

}
