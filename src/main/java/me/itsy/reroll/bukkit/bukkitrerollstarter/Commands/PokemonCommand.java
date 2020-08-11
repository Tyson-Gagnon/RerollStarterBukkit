package me.itsy.reroll.bukkit.bukkitrerollstarter.Commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.SubCommand;
import me.itsy.reroll.bukkit.bukkitrerollstarter.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import scala.Int;

public class PokemonCommand extends SubCommand {

    private BukkitRerollStarter plugin = BukkitRerollStarter.getInstance();

    @Override
    public void onCommand(CommandSender player, String[] args) {

        if(args.length != 3){
            player.sendMessage(ChatColor.RED + "INCORRECT SYNTAX. SEE /reroll help");
            return;
        }

        if(!Util.isOnline(args[1])){
            player.sendMessage(ChatColor.RED + "Cannot find specified player");
            return;
        }

        if(Integer.parseInt(args[2]) < 1 || Integer.parseInt(args[2]) > 6){
            player.sendMessage(ChatColor.RED + "Must define a slot between 1 and 6");
            return;
        }

        int slot = Integer.parseInt(args[2]);
        Player target = Bukkit.getPlayer(args[1]);

        PartyStorage  party = Pixelmon.storageManager.getParty(target.getUniqueId());

        if(party.get(slot-1) != null){
            Pokemon pokemon = party.get(slot-1);

            PokemonSpec spec = PokemonSpec.from(pokemon.getSpecies().name);

            Pokemon newPoke = Pixelmon.pokemonFactory.create(pokemon.getSpecies());

            //things that get saved on the pokemon

            spec.level = pokemon.getLevel();
            spec.shiny = pokemon.isShiny();
            spec.growth = pokemon.getGrowth().getForm();
            spec.gender = pokemon.getGender().getForm();

            //

            spec.apply(newPoke);

            party.set(slot-1,newPoke);
            target.sendMessage(ChatColor.GREEN + "Your " + ChatColor.GOLD + pokemon.getSpecies().name + " has been reset!");
            player.sendMessage(ChatColor.GREEN + "Reset " + ChatColor.GOLD + target.getName() + "'s " + pokemon.getSpecies().name);

        }else{
            player.sendMessage(ChatColor.RED+"Empty Slot!");
        }

    }

    @Override
    public String name() {
        return plugin.commandManager.pokemon;
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
