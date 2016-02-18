package com.dsnyder.fountainofyouth.commands;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dsnyder.fountainofyouth.fountains.FountainManager;

public class CreateCommand extends FOYCommand {

	public CreateCommand() {
		super("create", "Creates a fountain of youth", "create <x> <y> <z>", "foyouth.fountains.create");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean _execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		
		Location loc = new Location(null, 0, 0, 0);
		
		if (args.length == 3) {
			loc.setWorld(sender.getServer().getWorlds().get(0));
			loc.setX(Integer.parseInt(args[0]));
			loc.setY(Integer.parseInt(args[1]));
			loc.setZ(Integer.parseInt(args[2]));
		} else if (args.length == 0) {
			Player p = ((Player) sender);
			loc = p.getLocation();
			if (p.getTargetBlock((Set<Material>) null, 5) != null)
				loc = p.getTargetBlock((Set<Material>) null, 5).getLocation();
			else {
				loc.setY(loc.getBlockY()-1);
			}
		} else {
			return false;
		}

		FountainManager.getManager().addFountain(sender, loc);
		return true;
	}

}
