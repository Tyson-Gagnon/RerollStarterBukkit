package me.itsy.reroll.bukkit.bukkitrerollstarter.Events;

import net.minecraftforge.event.world.NoteBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ConfirmEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Confirm Starter Reroll")){

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "CONFIRM")){
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "reroll starter " + player.getName());
                player.closeInventory();
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "CANCEL")){
                player.closeInventory();
            }

            e.setCancelled(true);

        }

    }

}
