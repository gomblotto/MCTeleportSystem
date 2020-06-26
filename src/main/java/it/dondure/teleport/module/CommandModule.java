package it.dondure.teleport.module;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandModule implements ModuleService {
    private final List<AbstractCommand> commands = new ArrayList<>();

    @Override
    public void init() {
        registerCommands();
    }
    private void addCommand(AbstractCommand... commands){
        this.commands.addAll(Arrays.asList(commands));
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
}
