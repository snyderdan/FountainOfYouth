package com.dsnyder.fountainofyouth.commands;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCommand extends FOYCommand {

	public CreateCommand() {
		super("create", "Creates a fountain of youth", "create <x> <y> <z>", "foyouth.fountains.create");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean _execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		
		int x, y, z;
		
		if (args.length == 0) {
			Player p = ((Player) sender);
			
			Location loc;
			
			if (p.getTargetBlock((Set<Material>) null, 5) != null) {
				loc = p.getTargetBlock((Set<Material>) null, 5).getLocation();
			} else {
				loc = p.getLocation();
			}
			
			x = loc.getBlockX();
			y = loc.getBlockY()-1;
			z = loc.getBlockZ();
		} else if (args.length != 3) {
			return false;
		} else {
			x = Integer.parseInt(args[0]);
			y = Integer.parseInt(args[1]);
			z = Integer.parseInt(args[2]);
		}
		
	    sender.getServer().dispatchCommand(sender, String.format("fill %d %d %d %d %d %d quartz_block 0", 
	    		x+4, y, z+4, x-4, y, z-4));
	    sender.getServer().dispatchCommand(sender, String.format("fill %d %d %d %d %d %d air 0", 
	    		x+3, y+4, z+3, x-3, y-1, z-3));
	    sender.getServer().dispatchCommand(sender, String.format("fill %d %d %d %d %d %d grass 0", 
	    		x+4, y-1, z+4, x-4, y-1, z-4));
	    sender.getServer().dispatchCommand(sender, String.format("fill %d %d %d %d %d %d quartz_block 0", 
	    		x, y-1, z, x, y+3, z));
	    sender.getServer().dispatchCommand(sender, String.format("fill %d %d %d %d %d %d quartz_block 0", 
	    		x+1, y+2, z+1, x-1, y+2, z-1));
	    sender.getServer().dispatchCommand(sender, String.format("setblock %d %d %d water 0", 
	    		x, y+4, z));
	    sender.getServer().dispatchCommand(sender, String.format("setblock %d %d %d dirt 0", 
	    		x, y+5, z));
	    sender.getServer().dispatchCommand(sender, String.format("setblock %d %d %d air 0", 
	    		x, y+5, z));
		return true;
	}

}
