package it.dondure.teleport.warps;


public interface IWarp {
    void loadWarps();
    void addWarp(Warp warp);
    void deleteWarp(Warp warp);
    boolean existsWarp(Warp warp);
}
