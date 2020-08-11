package me.itsy.reroll.bukkit.bukkitrerollstarter.Commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.SubCommand;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.Util;
import net.minecraft.init.Items;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PromptCommand extends SubCommand {
    private BukkitRerollStarter  plugin = BukkitRerollStarter.getInstance();

    @Override
    public void onCommand(CommandSender player, String[] args) {

        if(args.length != 2){
            player.sendMessage(ChatColor.RED + "INCORRECT SYNTAX. SEE /reroll help");
            return;
        }

        if(!Util.isOnline(args[1])){
            player.sendMessage(ChatColor.RED + "Cannot find specified player");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        Inventory confirmationGUY = Bukkit.createInventory(target,27,ChatColor.GREEN + "Confirm Starter Reroll");

        List<String> whatsResetting = new ArrayList<>();
        whatsResetting.add(ChatColor.GRAY + "Rerolling your starter will reset the following!");
        whatsResetting.add(ChatColor.RED + "- IV's");
        whatsResetting.add(ChatColor.RED + "- EV's");
        whatsResetting.add(ChatColor.RED + "- Nature");
        whatsResetting.add(ChatColor.RED + "- Size");
        whatsResetting.add(ChatColor.RED + "- Level(Default Server Level)");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta FillerMeta = filler.getItemMeta();

        FillerMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "WARNING");
        FillerMeta.setLore(whatsResetting);

        filler.setItemMeta(FillerMeta);

        ItemStack Confirm = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 5);
        ItemMeta ConfirmMeta = Confirm.getItemMeta();

        ConfirmMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "CONFIRM");
        Confirm.setItemMeta(ConfirmMeta);

        ItemStack Cancel = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 14);
        ItemMeta CancelMeta = Confirm.getItemMeta();

        CancelMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "CANCEL");
        Cancel.setItemMeta(CancelMeta);

        for(int i = 0; i < 27;i++){
            if(i == 10){
                confirmationGUY.setItem(i,Confirm);
            }else if(i == 16){
                confirmationGUY.setItem(i,Cancel);
            }else{
                confirmationGUY.setItem(i,filler);
            }
        }

        target.openInventory(confirmationGUY);

    }

    @Override
    public String name() {
        return plugin.commandManager.prompt;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
