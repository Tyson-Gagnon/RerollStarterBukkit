package me.itsy.reroll.bukkit.bukkitrerollstarter.Commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.ConfigManager;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.SubCommand;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.Util;
import net.minecraftforge.event.world.NoteBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StarterCommand extends SubCommand {
    private BukkitRerollStarter plugin = BukkitRerollStarter.getInstance();

    @Override
    public void onCommand(CommandSender player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "INVALID SYNTAX. USE /reroll help");
            return;
        }

        if(!Util.isOnline(args[1])){
            player.sendMessage(ChatColor.RED + "Cannot find specified player");
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (ConfigManager.isEnabled()) {

            if (ConfigManager.getConfig().getString(target.getName()) != null) {

                UUID pokeUUID = UUID.fromString(ConfigManager.getConfig().getString(target.getName()));
                PartyStorage party = Pixelmon.storageManager.getParty(target.getUniqueId());

                EnumSpecies species = null;
                int level = 5;
                boolean shiny = false;
                int slot = 0;

                for (int i = 0; i < 6; i++) {
                    if (party.get(i) != null) {
                        if (party.get(i).getUUID().equals(pokeUUID)) {
                            species = party.get(i).getSpecies();
                            shiny = party.get(i).isShiny();
                            slot = i;
                        }
                    }
                }

                if (species != null) {
                    PokemonSpec spec = PokemonSpec.from(species.name);
                    spec.level = level;
                    spec.shiny = shiny;
                    Pokemon pokemon = Pixelmon.pokemonFactory.create(species);
                    spec.apply(pokemon);
                    party.set(slot, pokemon);

                    target.sendMessage(ChatColor.GREEN + "Started has been re-rolled");
                    player.sendMessage(ChatColor.GREEN + "Rerolled players starter");

                    ConfigManager.getConfig().set(target.getName(), null);
                    ConfigManager.save();

                } else {
                    target.sendMessage(ChatColor.RED + "You must have your starter in your party to re-roll!");
                }

            } else {
                target.sendMessage(ChatColor.RED + "You have already rerolled your starter, or somehow have not picked it yet!");
                return;
            }

        } else {
            target.sendMessage(ChatColor.RED + "ReRolling is current disabled!");
            return;
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.starter;
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
