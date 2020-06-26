package it.dondure.teleport.teleports;

import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RequestManager {
    private final HashMap<EntityTargetTeleport, TypeRequest> requests;

    public RequestManager() {
        this.requests = new HashMap<EntityTargetTeleport, TypeRequest>();
    }

    public void addRequest(@NonNull final EntityTargetTeleport e, final TypeRequest typeRequest) {
        this.requests.put(e, typeRequest);
    }

    public void removeRequest(@NonNull final EntityTargetTeleport e) {
        this.requests.remove(e);
    }

    public boolean isPresentPlayer(final Player e) {
        for (final EntityTargetTeleport teleport : this.requests.keySet()) {
            if (teleport.getTarget().equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void removeTotally(@NonNull final Player player) {
        this.requests.keySet().removeIf(i -> i.getTarget().equals(player));
        this.requests.keySet().removeIf(i -> i.getFrom().equals(player));
    }

    public void deleteOld(@NonNull final Player target) {
        this.requests.keySet().removeIf(i -> i.getTarget().equals(target));
    }

    public EntityTargetTeleport getEntityTargetFromPlayerFrom(@NonNull final Player from) {
        for (final EntityTargetTeleport teleport : this.requests.keySet()) {
            if (teleport.getFrom().equals(from)) {
                return teleport;
            }
        }
        return null;
    }

    public EntityTargetTeleport getEntityTargetFromPlayerTarget(@NonNull final Player target) {
        for (final EntityTargetTeleport teleport : this.requests.keySet()) {
            if (teleport.getTarget().equals(target)) {
                return teleport;
            }
        }
        return null;
    }

    public TypeRequest getTypeRequest(@NonNull final EntityTargetTeleport e) {
        return this.requests.get(e);
    }
}
