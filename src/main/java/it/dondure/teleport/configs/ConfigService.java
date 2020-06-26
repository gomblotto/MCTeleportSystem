package it.dondure.teleport.configs;

public interface ConfigService {
    void init();

    void saveConfig();

    void load();

    void reload();
}
