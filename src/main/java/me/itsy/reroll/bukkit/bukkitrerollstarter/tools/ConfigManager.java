package me.itsy.reroll.bukkit.bukkitrerollstarter.tools;

import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private static File file;
    private static FileConfiguration customFile;

    public static boolean enabled;

    //finds or generates config file
    public static void setUp(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(BukkitRerollStarter.getInstance().getName()).getDataFolder(),"playerList.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            }
            catch (IOException e){
                BukkitRerollStarter.getLog().warning("Config File not created properly");
            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            BukkitRerollStarter.getLog().warning("Config File not saved properly");
        }
    }

    public static void reload(){

        customFile = YamlConfiguration.loadConfiguration(file);


    }

    public static boolean isEnabled(){
        return customFile.getBoolean("Enabled");
    }

    public static List<String> getUUIDS(){
        return (List<String>) customFile.getList("Player UUID's");
    }

}
