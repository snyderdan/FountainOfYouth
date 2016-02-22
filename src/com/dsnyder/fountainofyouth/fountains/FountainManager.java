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
	}
	
	public Fountain[] getFountains() {
		return db.getFountains();
	}
}
