package com.dsnyder.fountainofyouth.fountains;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
			Bukkit.broadcastMessage("load " + event.getChunk().toString());
			f.load();
			db.update(f);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onUnloadChunk(ChunkUnloadEvent event) {
		for (Fountain f : db.getFountainsInChunk(event.getChunk())) {
			Bukkit.broadcastMessage("unload " + event.getChunk().toString());
			f.unload();
			db.update(f);
		}
	}
	
	public void createFountain(Location loc) {
		Fountain fountain = new Fountain(loc);
		if (db == null) db = new FountainDB();
		fountain.load();
		db.save(fountain);
	}
	
	public Fountain[] getFountains() {
		return db.getFountains();
	} 
}
