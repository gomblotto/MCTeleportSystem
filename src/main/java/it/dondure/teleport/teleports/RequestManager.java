package it.dondure.teleport;

import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RequestManager {
    private  final HashMap<EntityTargetTeleport, TypeRequest> requests = new HashMap<>();

    public  void addRequest(@NonNull EntityTargetTeleport e, TypeRequest typeRequest){
        requests.put(e, typeRequest);
    }
    public  void removeRequest(@NonNull EntityTargetTeleport e){
        requests.remove(e);
    }
    public  boolean isPresentPlayer(Player e){
       for(EntityTargetTeleport teleport : requests.keySet()){
           if(teleport.getTarget().equals(e)) return true;
       }
       return false;
    }
    public  void removeTotally(@NonNull Player player){
        requests.keySet().removeIf(i -> i.getTarget().equals(player));
        requests.keySet().removeIf(i -> i.getFrom().equals(player));
    }

    public void deleteOld(@NonNull Player target){
        requests.keySet().removeIf(i -> i.getTarget().equals(target));
    }
    public  EntityTargetTeleport getEntityTargetFromPlayerFrom(@NonNull Player from){
        for(EntityTargetTeleport teleport : requests.keySet()){
            if(teleport.getFrom().equals(from)) return teleport;
        }
        return null;
    }

    public  EntityTargetTeleport getEntityTargetFromPlayerTarget(@NonNull Player target){
        for(EntityTargetTeleport teleport : requests.keySet()){
            if(teleport.getTarget().equals(target)) return teleport;
        }
        return null;
    }
    public  TypeRequest getTypeRequest(@NonNull EntityTargetTeleport e){
        return requests.get(e);
    }


}
