package it.dondure.teleport.warps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class Warp {

    @Getter private final Location warp;
    @Getter private final String name;

    @Override
    public String toString() {
        return name;
    }
    public void teleportPlayer(Player player){
        player.teleport(warp);
    }
}
