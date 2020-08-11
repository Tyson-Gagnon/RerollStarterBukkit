package me.itsy.reroll.bukkit.bukkitrerollstarter.Events;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.enums.ReceiveType;
import com.pixelmonmod.pixelmon.api.events.PixelmonReceivedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.ConfigManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import red.mohist.api.event.BukkitHookForgeEvent;

import java.util.UUID;

public class PixelmonRecieved implements Listener {

    @EventHandler
    public void PixelmonReceivedEvent(BukkitHookForgeEvent event){
        if(event.getEvent() instanceof PixelmonReceivedEvent){
            PixelmonReceivedEvent e = (PixelmonReceivedEvent)event.getEvent();


            //logic
            ReceiveType receiveType = ReceiveType.Starter;

            if(e.receiveType.equals(receiveType)){
                Pokemon pokemon = e.pokemon;
                UUID pokemonUUID = pokemon.getUUID();

                ConfigManager.getConfig().set(e.player.getName(),pokemonUUID.toString());
                ConfigManager.save();

            }

        }
    }


}
