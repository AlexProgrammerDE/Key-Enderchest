package me.alexprogrammerde.KeyEnderChest.listeners;

import me.alexprogrammerde.KeyEnderChest.storage.KeyUUIDStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class ChestInvListener implements Listener {

    @EventHandler
    public void onEnderChestOpen(PlayerInteractEvent event){
        Action action = event.getAction();
        Player player = event.getPlayer();

        if(!(action == RIGHT_CLICK_BLOCK)) { return; }
        if(!(event.getClickedBlock().getType() == Material.ENDER_CHEST)) { return; }

        if(event.getClickedBlock().getType() == Material.ENDER_CHEST && action == RIGHT_CLICK_BLOCK) {

            // It is clear that the player wants to opens a enderchest
            Block enderChestBlock = event.getClickedBlock();
            if(player.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {

                List<String> loreList = player.getInventory().getItemInMainHand().getItemMeta().getLore();
                String loreString = loreList.get(0);
                UUID id = UUID.fromString(loreString);

                if(KeyUUIDStorage.getList().containsKey(id)) {
                    player.openInventory(KeyUUIDStorage.getList().get(id));
                } else
                    player.sendMessage("§4§lSorry your key is not valid!");

            } else {
                player.sendMessage("§4§lHey! You need a key to open a Ender Chest. You can get one with: /key");
            }

            event.setCancelled(true);
        }

        return;
    }
}
