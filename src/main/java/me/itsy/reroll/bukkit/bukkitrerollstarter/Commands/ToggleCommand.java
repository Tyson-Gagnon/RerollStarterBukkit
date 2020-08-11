package me.itsy.reroll.bukkit.bukkitrerollstarter.Commands;

import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.ConfigManager;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCommand extends SubCommand {

    private BukkitRerollStarter plugin = BukkitRerollStarter.getInstance();

    @Override
    public void onCommand(CommandSender player, String[] args) {
        if(ConfigManager.isEnabled()){
            ConfigManager.getConfig().set("Enabled",false);
            ConfigManager.save();
            player.sendMessage(ChatColor.RED + "Disabled Rerolling of starters");
        }else{
            ConfigManager.getConfig().set("Enabled",true);
            ConfigManager.save();
            player.sendMessage(ChatColor.GREEN + "Enabled Rerolling of starters");
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.toggle;
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
