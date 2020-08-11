package me.itsy.reroll.bukkit.bukkitrerollstarter.Commands;

import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {


    BukkitRerollStarter plugin  = BukkitRerollStarter.getInstance();

    @Override
    public void onCommand(CommandSender player, String[] args) {

        player.sendMessage(ChatColor.BLUE + "/reroll Prompt <player> -" + ChatColor.GOLD + "Prompts player to reroll their starter with conformation menu");
        player.sendMessage(ChatColor.BLUE + "/reroll starter <player> -" + ChatColor.GOLD + "Rerolls players starter pixelmon (one time use)");
        player.sendMessage(ChatColor.BLUE + "/reroll Toggle -" + ChatColor.GOLD + "Toggles the ability to reroll starter pokemon");
        player.sendMessage(ChatColor.BLUE + "/reroll pokemon <player> <slot> -" + ChatColor.GOLD + "Rerolls targets pokemon");

    }

    @Override
    public String name() {
        return plugin.commandManager.help;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
