package com.dsnyder.fountainofyouth.fountains;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.dsnyder.fountainofyouth.util.WorldEditor;

public class Fountain {
	
	private static final int WORLD_LENGTH = 128;
	private static final int COORD_LENGTH = 10;
	
	private static Integer len = null;
	
	private Location location;
	private boolean isGenerated;
	
	public Fountain(Location l) {
		this.location = l;
		isGenerated = false;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public boolean isGenerated() {
		return isGenerated;
	}
	
	public boolean generate() {
		
		World w = location.getWorld();
		
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		
		if (isGenerated()) return isGenerated();
		
		if (location.getWorld().isChunkLoaded(location.getChunk())) {
			WorldEditor.fill(w, x+4, y, z+4, x-4, y, z-4, Material.QUARTZ_BLOCK);
			WorldEditor.fill(w, x+3, y+4, z+3, x-3, y-1, z-3, Material.AIR);
			WorldEditor.fill(w, x+4, y-1, z+4, x-4, y-1, z-4, Material.GRASS);
			WorldEditor.fill(w, x, y+3, z, x, y-1, z, Material.QUARTZ_BLOCK);
			WorldEditor.fill(w, x+1, y+2, z+1, x-1, y+2, z-1, Material.QUARTZ_BLOCK);
			WorldEditor.placeBlock(w, x, y+4, z, Material.WATER);
			WorldEditor.placeBlock(w, x, y+5, z, Material.DIRT);
			WorldEditor.placeBlock(w, x, y+5, z, Material.AIR);
			isGenerated = true;
		}
		
		return isGenerated();
	}
	
	public static Fountain unstringify(String str) {
		
		Fountain fountain = new Fountain(new Location(null, 0, 0, 0));
		
		String[] attrs = str.split(" ");
		if (attrs[0] == "g") {
			fountain.isGenerated = true;
		} else if (attrs[0] == "n") {
			fountain.isGenerated = false;
		} else {
			return null;
		}
		
		fountain.location.setWorld(Bukkit.getWorld(attrs[1]));
		fountain.location.setX(Integer.parseInt(attrs[2]));
		fountain.location.setY(Integer.parseInt(attrs[3]));
		fountain.location.setZ(Integer.parseInt(attrs[4]));
		
		return fountain;
	}
	
	public String stringify() {
		
		String me = String.format("%%s %%%ds %%%d0d %%%d0d %%%d0d", WORLD_LENGTH, COORD_LENGTH, COORD_LENGTH, COORD_LENGTH);
		
		if (location.getWorld() != null) {
			me = String.format(me, (isGenerated) ? "g" : "n", location.getWorld().getName(), 
					location.getBlockX(), location.getBlockY(), location.getBlockZ());
		} else {
			me = String.format(me, (isGenerated) ? "g" : "n", "", 
					location.getBlockX(), location.getBlockY(), location.getBlockZ());
		}
		return me;
	}
	
	public int stringifyLength() {
		if (len == null) {
			len = stringify().length();
		}
		
		return len.intValue();
	}
}
