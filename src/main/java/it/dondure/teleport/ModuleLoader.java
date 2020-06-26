package it.dondure.teleport;

import it.dondure.teleport.module.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleLoader {
    private final List<ModuleService> moduleServiceList;
    private final TeleportModule teleportModule;
    private final WarpModule warpModule;
    private final SpawnModule spawnModule;
    private final HomeModule homeModule;

    public ModuleLoader() {
        this.moduleServiceList = new ArrayList<ModuleService>();
        this.teleportModule = new TeleportModule();
        this.warpModule = new WarpModule();
        this.spawnModule = new SpawnModule();
        this.homeModule = new HomeModule();
        this.addModule(new CommandModule(), new ListenerModule(), this.teleportModule, this.warpModule, this.spawnModule, this.homeModule);
    }

    public void load() {
        this.moduleServiceList.forEach(ModuleService::init);
    }

    private void addModule(final ModuleService... services) {
        this.moduleServiceList.addAll(Arrays.asList(services));
    }

    public TeleportModule getTeleportModule() {
        return this.teleportModule;
    }

    public WarpModule getWarpModule() {
        return this.warpModule;
    }

    public SpawnModule getSpawnModule() {
        return this.spawnModule;
    }

    public HomeModule getHomeModule() {
        return this.homeModule;
    }
}
