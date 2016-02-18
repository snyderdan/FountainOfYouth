package com.dsnyder.fountainofyouth.fountains;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class FountainManager implements Listener {
	
	private static FountainManager main;
	private FountainDB db;

	public FountainManager() {
		main = this;
		db = new FountainDB();
	}
	
	public static FountainManager getManager() {
		return main;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onLoadChunk(ChunkLoadEvent event) {
		
		for (Fountain f : db.getFountainsInChunk(event.getChunk())) {
			if (f.isGenerated()) continue;
			f.generate();
		}
	}
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onUnloadChunk(ChunkUnloadEvent event) {
		
	}
	
	public void addFountain(CommandSender sender, Location loc) {
		
		Fountain fountain = new Fountain(loc);
		if (db == null) db = new FountainDB();
		db.save(fountain);
		
		fountain.generate();
		/*
		// if chunk is not loaded currently, simply return and have the fountain be generated later
		if (!sender.getWorld().isChunkLoaded(loc.getChunk())) return;
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
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
	    		x, y+5, z));*/
	}
	
	public Fountain[] getFountains() {
		return db.getFountains();
	}
}
