package me.itsy.reroll.bukkit.bukkitrerollstarter.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.logging.Logger;

public class Util {

    public static boolean isOnline(String name){

        if(Bukkit.getServer().getPlayer(name) !=  null){
            System.out.println(Bukkit.getServer().getPlayer(name).getName());
            if(Bukkit.getServer().getPlayer(name).isOnline()){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

}
