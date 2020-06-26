package it.dondure.teleport.module;

import it.dondure.teleport.warps.WarpManager;

public class WarpModule implements ModuleService {
    private WarpManager warpManager;

    @Override
    public void init() {
        this.loadWarps();
    }

    private void loadWarps() {
        (this.warpManager = new WarpManager()).loadWarps();
    }

    public WarpManager getWarpManager() {
        return this.warpManager;
    }
}
