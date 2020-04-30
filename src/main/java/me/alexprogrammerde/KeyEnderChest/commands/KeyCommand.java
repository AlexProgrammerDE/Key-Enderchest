package me.alexprogrammerde.KeyEnderChest.commands;

import me.alexprogrammerde.KeyEnderChest.storage.KeyUUIDStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class KeyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player =  (Player) sender;
            PlayerInventory playerInv = player.getInventory();
            ItemStack hook = new ItemStack(Material.TRIPWIRE_HOOK, 1);
            ItemMeta hookMeta = hook.getItemMeta();
            Inventory keyInv = Bukkit.createInventory(player, 27, player.getName() + "s Ender Chest");

            boolean shouldKeyContainEnderChest = false;

            ItemStack[] noItems = new ItemStack[0];

            player.sendMessage("§a§lGenerating new Key.");

            for(ItemStack stack : player.getEnderChest().getStorageContents()){
                if (stack != null) shouldKeyContainEnderChest = true;
            }

            if(shouldKeyContainEnderChest) {
                player.sendMessage("§a§lYou have a Ender Chest with items on this server! Transferring the items to the key.");
                keyInv.setStorageContents(player.getEnderChest().getStorageContents());
                player.getEnderChest().setContents(noItems);
            }

            // List for the Item Lore
            ArrayList<String> Lore = new ArrayList<String>();

            // Get a random UUID
            UUID LoreUUID = UUID.randomUUID();

            // Try to get another UUID if already in list
            while(KeyUUIDStorage.getList().containsKey(Lore)) {
                LoreUUID = UUID.randomUUID();
            }

            // Add the UUID to the list
            KeyUUIDStorage.addUUID(LoreUUID, keyInv);

            // Add the new Lore to the Item
            Lore.add(LoreUUID.toString());
            hookMeta.setLore(Lore);
            hookMeta.setDisplayName(player.getName() + "s Key");
            hook.setItemMeta(hookMeta);

            playerInv.addItem(hook);
        }

        return false;
    }
}
