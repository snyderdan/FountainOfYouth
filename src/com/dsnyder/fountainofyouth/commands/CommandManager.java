package com.dsnyder.fountainofyouth.commands;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.dsnyder.fountainofyouth.FountainOfYouth;

public class CommandManager implements CommandExecutor {
	
	public static final String PLUGIN_TAG  = "FountainOfYouth";
	public static final String PLUGIN_NAME = "Fountain Of Youth";
	public static final String PLUGIN_ALIAS = "foyouth";

	private HashMap<String, FOYCommand> commands;
	private static CommandManager main;
	
	public CommandManager() {
		commands = new HashMap<>();
		main = this;
		registerCommand(new HelpCommand());
		registerCommand(new YouthCommand());
		registerCommand(new AgingCommand());
		registerCommand(new CreateCommand());
		registerCommand(new ListCommand());
	}
	
	static CommandManager getManager() {
		return main;
	}
	
	Collection<FOYCommand> getRegisteredCommands() {
		return commands.values();
	}
	
	boolean registerCommand(FOYCommand cmd) {
		
		if (commands.containsKey(cmd.getName())) return false;
		
		commands.put(cmd.getName().toLowerCase(), cmd);
		
		return true;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, 
    		Command command, String label, String[] args) {
		
		if (args.length > 0) {
			
			args[0] = args[0].toLowerCase();
			
			if (!commands.containsKey(args[0])) {
				sender.sendMessage(ChatColor.RED + "\"" + args[0] + "\"" + " is not a valid " + PLUGIN_NAME + " command");
				return true;
			}
			
			String[] subargs = new String[args.length - 1];
			System.arraycopy(args, 1, subargs, 0, subargs.length);
			
			if (!commands.get(args[0]).execute(sender, subargs)) {
				sender.sendMessage(ChatColor.RED + commands.get(args[0]).getUsage());
			}
		} else {
			sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + PLUGIN_TAG + ChatColor.GOLD +
					"] " + ChatColor.GREEN + PLUGIN_NAME + " Version " + FountainOfYouth.CURRENT_VERSION + 
					" by Sgt_Snyder\n");
			sender.sendMessage(ChatColor.GOLD + "/" + PLUGIN_ALIAS + " help: " + ChatColor.WHITE + "Display help page ");
		}
		
		return true;
	}
}
