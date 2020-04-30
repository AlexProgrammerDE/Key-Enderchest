package me.alexprogrammerde.KeyEnderChest.storage;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class KeyUUIDStorage {

    public static HashMap<UUID, Inventory> list = new HashMap<UUID, Inventory>();

    public static HashMap<UUID, Inventory> getList() {
        return list;
    }

    public static void setList(HashMap<UUID, Inventory> list) { KeyUUIDStorage.list = list; }

    public static void addUUID(UUID UUID, Inventory inv){
        if(!list.containsKey(UUID)){
            list.put(UUID, inv);
        }

        // main.getPlugin().getConfig()("UUIDS", KeyUUIDStorage.getList());
    }

}
