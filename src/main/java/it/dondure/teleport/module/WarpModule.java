package it.dondure.teleport.module;

import it.dondure.teleport.warps.WarpLoader;
import lombok.Getter;

public class WarpModule implements ModuleService {
    @Getter
    private WarpLoader warpLoader;
    
    @Override
    public void init() {
        loadWarps();
    }
    private void loadWarps(){
        warpLoader = new WarpLoader();
        warpLoader.loadWarps();
    }

}
