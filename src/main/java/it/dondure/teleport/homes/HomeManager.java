package it.dondure.teleport.homes;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeManager {
    private List<Home> homeList;

    public HomeManager() {
        this.homeList = new ArrayList<Home>();
    }

    public void addHome(final Home home) {
        this.homeList.add(home);
    }

    public void removeHome(final Home home) {
        this.homeList.remove(home);
    }

    public Home getHomeFromPlayer(final Player player) {
        for (final Home home : this.homeList) {
            if (home.getOwner().equals(player.getName())) {
                return home;
            }
        }
        return null;
    }

    public List<Home> getHomeList() {
        return this.homeList;
    }

    public void setHomeList(final List<Home> homeList) {
        this.homeList = homeList;
    }
}
