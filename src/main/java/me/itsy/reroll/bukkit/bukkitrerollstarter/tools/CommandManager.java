package me.itsy.reroll.bukkit.bukkitrerollstarter.tools;

import me.itsy.reroll.bukkit.bukkitrerollstarter.BukkitRerollStarter;
import me.itsy.reroll.bukkit.bukkitrerollstarter.Commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private ArrayList<SubCommand> commands = new ArrayList<>();
    private BukkitRerollStarter plugin = BukkitRerollStarter.getInstance();

    public CommandManager() {

    }

    public String main = "reroll";
    public String prompt = "prompt";
    public String starter = "starter";
    public String toggle = "toggle";
    public String pokemon = "pokemon";
    public String help = "help";

    public void setUp() {
        plugin.getCommand(main).setExecutor(this);
        plugin.getCommand(main).setTabCompleter(this);

        this.commands.add(new PromptCommand());
        this.commands.add(new StarterCommand());
        this.commands.add(new ToggleCommand());
        this.commands.add(new PokemonCommand());
        this.commands.add(new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender src, Command command, String label, String[] args) {


        if (command.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                return true;
            }

            SubCommand target = this.get(args[0]);

            if (target == null) {

            }

            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try {
                target.onCommand(src, args);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }

        return false;
    }

    private SubCommand get(String name) {
        Iterator<SubCommand> subCommands = this.commands.iterator();

        while (subCommands.hasNext()) {
            SubCommand sc = (SubCommand) subCommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;
            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender src, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("reroll")){
            if(args.length == 1){
                List<String> list = new ArrayList<>();
                list.add("prompt");
                list.add("pokemon");
                list.add("starter");
                list.add("toggle");
                list.add("help");
                return list;
            }
        }
        return null;
    }
}
