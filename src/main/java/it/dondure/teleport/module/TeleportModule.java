package it.dondure.teleport.module;

import it.dondure.teleport.teleports.RequestManager;

public class TeleportModule implements ModuleService {
    private RequestManager requestManager;

    @Override
    public void init() {
        this.requestManager = new RequestManager();
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }
}
