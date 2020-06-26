package it.dondure.teleport.teleports;

import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RequestManager {
    private HashMap<EntityTargetTeleport, TypeRequest> requests;

    public RequestManager() {
        this.requests = new HashMap<EntityTargetTeleport, TypeRequest>();
    }

    public void addRequest(@NonNull EntityTargetTeleport e, TypeRequest typeRequest) {
        this.requests.put(e, typeRequest);
    }

    public void removeRequest(@NonNull EntityTargetTeleport e) {
        this.requests.remove(e);
    }

    public boolean isPresentPlayer(Player e) {
        for (EntityTargetTeleport teleport : this.requests.keySet()) {
            if (teleport.getTarget().equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void removeTotally(@NonNull Player player) {
        this.requests.keySet().removeIf(i -> i.getTarget().equals(player));
        this.requests.keySet().removeIf(i -> i.getFrom().equals(player));
    }

    public void deleteOld(@NonNull Player target) {
        this.requests.keySet().removeIf(i -> i.getTarget().equals(target));
    }

    public EntityTargetTeleport getEntityTargetFromPlayerFrom(@NonNull Player from) {
        for (EntityTargetTeleport teleport : this.requests.keySet()) {
            if (teleport.getFrom().equals(from)) {
                return teleport;
            }
        }
        return null;
    }

    public EntityTargetTeleport getEntityTargetFromPlayerTarget(@NonNull Player target) {
        for (EntityTargetTeleport teleport : this.requests.keySet()) {
            if (teleport.getTarget().equals(target)) {
                return teleport;
            }
        }
        return null;
    }

    public TypeRequest getTypeRequest(@NonNull EntityTargetTeleport e) {
        return this.requests.get(e);
    }
}
