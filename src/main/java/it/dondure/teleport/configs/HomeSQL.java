package it.dondure.teleport.configs;

import it.dondure.TeleportSystem;

import java.io.File;
import java.io.IOException;

public class HomeSQL implements ConfigService {
    private File file;

    @Override
    public void init() {
        this.file = new File(TeleportSystem.getInstance().getDataFolder(), "home.db");
        this.create();
    }

    @Override
    public void saveConfig() {
    }

    @Override
    public void load() {
    }

    @Override
    public void reload() {
    }

    public void create() {
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public File getFile() {
        return this.file;
    }
}
