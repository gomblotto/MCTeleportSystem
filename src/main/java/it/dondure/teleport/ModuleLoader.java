package it.dondure.teleport;

import it.dondure.teleport.module.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleLoader {
    private List<ModuleService> moduleServiceList;
    private TeleportModule teleportModule;
    private WarpModule warpModule;
    private SpawnModule spawnModule;
    private HomeModule homeModule;

    public ModuleLoader() {
        this.moduleServiceList = new ArrayList<>();
        this.teleportModule = new TeleportModule();
        this.warpModule = new WarpModule();
        this.spawnModule = new SpawnModule();
        this.homeModule = new HomeModule();
        this.addModule(new CommandModule(), new ListenerModule(), this.teleportModule, this.warpModule, this.spawnModule, this.homeModule);
    }

    public void load() {
        this.moduleServiceList.forEach(ModuleService::init);
    }

    private void addModule(ModuleService... services) {
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
