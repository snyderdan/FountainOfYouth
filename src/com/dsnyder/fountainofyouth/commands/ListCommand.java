package com.dsnyder.fountainofyouth.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import com.dsnyder.fountainofyouth.fountains.Fountain;
import com.dsnyder.fountainofyouth.fountains.FountainManager;

public class ListCommand extends FOYCommand {

	public ListCommand() {
		super("list", "lists info on all fountains", "list", "foyouth.commands.list");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean _execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 0) return false;
		
		for (Fountain f : FountainManager.getManager().getFountains()) {
			Location l = f.getLocation();
			sender.sendMessage(String.format("World: %s\n\tX: %d Y: %d Z: %d\n\n", l.getWorld().getName(), l.getBlockX(),
					l.getBlockY(), l.getBlockZ()));
		}
		return true;
	}
	
}
