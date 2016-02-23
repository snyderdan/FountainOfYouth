package com.dsnyder.fountainofyouth.util;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class BabyMaker {
	public static boolean makeBaby(Entity e) {
		
		if (!(e instanceof Ageable)) return false;
		
		Ageable ent = (Ageable) e;
		
		ent.setBaby();
		ent.setAgeLock(true);
		return true;
	}
	
	public static boolean revokeYouth(Entity e) {
		
		if (!(e instanceof Ageable)) return false;
		
		Ageable ent = (Ageable) e;

		ent.setAgeLock(false);
		return true;
	}
}
