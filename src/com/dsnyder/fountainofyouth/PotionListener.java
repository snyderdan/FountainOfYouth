package com.dsnyder.fountainofyouth;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;

import com.dsnyder.fountainofyouth.util.BabyMaker;

/**
 * 
 */
public class PotionListener implements Listener {
	
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onItemSplash(PotionSplashEvent event) {
		
		ItemStack item = event.getPotion().getItem();
		
		if (!item.getItemMeta().hasLore()) return;
		
		if (item.getItemMeta().getLore().contains(FountainOfYouth.YOUTH_LORE)) {
			for (Entity e : event.getAffectedEntities())
				BabyMaker.makeBaby(e);
			
		} else if (item.getItemMeta().getLore().contains(FountainOfYouth.AGING_LORE)) {
			for (Entity e : event.getAffectedEntities())
				BabyMaker.revokeYouth(e);
		}
	}
}
