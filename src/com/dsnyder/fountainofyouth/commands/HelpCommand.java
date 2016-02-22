package com.dsnyder.fountainofyouth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand extends GenericCommand {

	public HelpCommand() {
		super("help", "Display help page", "help [subcommand]", "foyouth.commands.help");
	}

	@Override
	public boolean _execute(CommandSender sender, String[] args) {
		if (args.length == 0)
			for (GenericCommand cmd : CommandManager.getManager().getRegisteredCommands()) 
				sender.sendMessage(ChatColor.GOLD + "/" + CommandManager.PLUGIN_ALIAS + " " + cmd.getName() + 
						": " + ChatColor.WHITE + cmd.getDescription());
		else {
			
			boolean found = false;
			
			for (GenericCommand cmd : CommandManager.getManager().getRegisteredCommands()) 
				if (cmd.getName().equalsIgnoreCase(args[0])) {
					sender.sendMessage(ChatColor.GOLD + "/" + CommandManager.PLUGIN_ALIAS + " " + cmd.getName() + 
							": " + ChatColor.WHITE + cmd.getDescription());
					found = true;
					break;
				}
			
			if (!found) {
				sender.sendMessage(ChatColor.RED + args[0] + " is not a valid subcommand!");
			}
		}
		return true;
	}

}
