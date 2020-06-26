package it.dondure.teleport;


import it.dondure.TeleportSystem;
import it.dondure.teleport.commands.AbstractCommand;
import it.dondure.teleport.commands.TeleportSystemCommand;
import it.dondure.teleport.commands.spawn.SetSpawnCommand;
import it.dondure.teleport.commands.spawn.SpawnCommand;
import it.dondure.teleport.commands.teleportrequests.TeleportCommand;
import it.dondure.teleport.commands.teleportrequests.TeleportHereCommand;
import it.dondure.teleport.commands.teleportrequests.response.AcceptRequestTeleportCommand;
import it.dondure.teleport.commands.teleportrequests.response.DenyRequestTeleportCommand;
import it.dondure.teleport.commands.teleportrequests.sendrequests.RequestTeleportCommand;
import it.dondure.teleport.commands.teleportrequests.sendrequests.RequestTeleportHereCommand;
import it.dondure.teleport.commands.warps.DelWarpCommand;
import it.dondure.teleport.commands.warps.SetWarpCommand;
import it.dondure.teleport.commands.warps.WarpCommand;
import it.dondure.teleport.listeners.QuitEvent;
import it.dondure.teleport.warps.Warp;
import it.dondure.teleport.warps.WarpLoader;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teleport  {
    private final List<AbstractCommand> commands = new ArrayList<>();
    @Getter private WarpLoader warpLoader;
    @Getter private RequestManager requestManager;
    public void init() {
        loadWarps();
        registerCommands();
        requestManager = new RequestManager();
        new QuitEvent();

    }
private void registerCommands(){
        addCommand(
                new SetSpawnCommand(),
                new SpawnCommand(),
                new AcceptRequestTeleportCommand(),
                new DenyRequestTeleportCommand(),
                new RequestTeleportCommand(),
                new RequestTeleportHereCommand(),
                new TeleportCommand(),
                new TeleportHereCommand(),
                new DelWarpCommand(),
                new SetWarpCommand(),
                new WarpCommand(),
                new TeleportSystemCommand()
                );
        commands.forEach(AbstractCommand::register);
}
private void loadWarps(){
    warpLoader = new WarpLoader();
    warpLoader.loadWarps();
}
private void addCommand(AbstractCommand... commands){
    this.commands.addAll(Arrays.asList(commands));
}
}
