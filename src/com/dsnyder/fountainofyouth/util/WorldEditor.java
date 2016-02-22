package com.dsnyder.fountainofyouth.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class WorldEditor {

	public WorldEditor() {
		// TODO Auto-generated constructor stub
	}

	public static void placeBlock(Location l, Material bl) {
		l.getBlock().setType(bl);
	}
	
	public static void placeBlock(World w, int x, int y, int z, Material bl) {
		new Location(w, x, y, z).getBlock().setType(bl);
	}
	
	public static void fill(Location l1, Location l2, Material bl) {
		
		Location hi = l1.clone();
		Location lo = l2.clone();
		
		int x,y,z;
		
		if (hi.getBlockX() < lo.getBlockX()) {
			x = hi.getBlockX();
			hi.setX(lo.getBlockX());
			lo.setX(x);
		}
		
		if (hi.getBlockY() < lo.getBlockY()) {
			y = hi.getBlockY();
			hi.setY(lo.getBlockY());
			lo.setY(y);
		}
		
		if (hi.getBlockZ() < lo.getBlockZ()) {
			z = hi.getBlockZ();
			hi.setZ(lo.getBlockZ());
			lo.setZ(z);
		}
		
		for (x=lo.getBlockX(); x<=hi.getBlockX(); x++)
			for (y=lo.getBlockY(); y<=hi.getBlockY(); y++)
				for (z=lo.getBlockZ(); z<=hi.getBlockZ(); z++) 
					placeBlock(new Location(lo.getWorld(), x, y, z), bl);
	}
	
public static void fill(World w, int x1, int y1, int z1, int x2, int y2, int z2, Material bl) {
		
		Location hi = new Location(w, Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
		Location lo = new Location(w, Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
		fill(hi, lo, bl);
	}
}
