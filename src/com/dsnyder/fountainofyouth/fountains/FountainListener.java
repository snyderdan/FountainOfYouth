package com.dsnyder.fountainofyouth.fountains;

import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.dsnyder.fountainofyouth.util.BabyMaker;

public class FountainListener extends BukkitRunnable {
	
	private Location hi;
	private Location lo;
	private Location fnt;
	
	public FountainListener(Fountain fountain) {
		super();
		fnt = fountain.getLocation().clone();
		hi = fnt.clone();
		lo = fnt.clone();
		lo.setX(lo.getX()-3);
		lo.setY(lo.getY()-3);
		hi.setX(hi.getX()+3);
		hi.setY(hi.getY()+4);
		hi.setX(hi.getZ()+3);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (Entity e : fnt.getWorld().getNearbyEntities(fnt, 4, 4, 4)) {
			
			if (!(e instanceof Ageable)) continue;	// skip heavy processing
			
			Location l = e.getLocation();
			int x = l.getBlockX();
			int y = l.getBlockY();
			int z = l.getBlockZ();
			
			if (!(x >= lo.getBlockX() && x <= hi.getBlockX())) continue;
			if (!(y >= lo.getBlockY() && y <= hi.getBlockY())) continue;
			if (!(z >= lo.getBlockZ() && z <= hi.getBlockZ())) continue;
			
			BabyMaker.makeBaby(e);
		}
	}
	
}
