package it.dondure.teleport.utils;

import it.dondure.teleport.warps.Warp;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpUtils {
    public static Warp getWarpFromName(final List<Warp> warps, final String s) {
        for (final Warp warpa : warps) {
            if (warpa.toString().equals(s)) {
                return warpa;
            }
        }
        return null;
    }

    public static void sendWarpMessage(final String nowarpview, final String nowarp, final String join, final List<Warp> warps, final Player player) {
        final StringBuilder sb = new StringBuilder();
        sb.append(join);
        if (warps.size() == 0) {
            player.sendMessage(nowarp);
            return;
        }
        for (int i = 0; i < warps.size(); ++i) {
            if (player.hasPermission("teleportsystem.warp.list." + warps.get(i)) || player.hasPermission("teleportsystem.warp.list.*")) {
                if (warps.size() - i == 1) {
                    sb.append(warps.get(i).toString());
                } else {
                    sb.append(warps.get(i).toString()).append(",");
                }
            }
        }
        if (sb.toString().equals(join)) {
            player.sendMessage(nowarpview);
            return;
        }
        player.sendMessage(sb.toString());
    }
}
