package it.dondure.teleport.module;

import it.dondure.teleport.spawn.SpawnManager;

public class SpawnModule implements ModuleService {
    private SpawnManager spawnManager;

    @Override
    public void init() {
        (this.spawnManager = new SpawnManager()).load();
    }

    public SpawnManager getSpawnManager() {
        return this.spawnManager;
    }
}
