package me.itsy.reroll.bukkit.bukkitrerollstarter;

import com.pixelmonmod.pixelmon.Pixelmon;
import me.itsy.reroll.bukkit.bukkitrerollstarter.Events.ConfirmEvent;
import me.itsy.reroll.bukkit.bukkitrerollstarter.Events.PixelmonRecieved;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.CommandManager;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class BukkitRerollStarter extends JavaPlugin {

    public static BukkitRerollStarter instance;

    private static Logger logger = Bukkit.getLogger();
    public CommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommands();
        registerEvents();

        //config stuff
        ConfigManager.setUp();
        ConfigManager.getConfig().addDefault("Enabled", true);
        ConfigManager.getConfig().options().copyDefaults(true);
        ConfigManager.save();

        //command manager stuff;
        commandManager = new CommandManager();
        commandManager.setUp();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void registerCommands(){

    }

    private void registerEvents(){
        Bukkit.getServer().getPluginManager().registerEvents(new PixelmonRecieved(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new ConfirmEvent(),this);
    }

    public static BukkitRerollStarter getInstance(){return instance;}
    public static Logger getLog(){return logger;}

}
