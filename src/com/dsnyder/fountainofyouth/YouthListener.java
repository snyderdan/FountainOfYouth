package com.dsnyder.fountainofyouth;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;

/**
 * 
 */
public class YouthListener implements Listener {
	
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onItemSplash(PotionSplashEvent event) {
		
		ItemStack item = event.getPotion().getItem();
		
		if (!item.getItemMeta().hasLore()) return;
		if (item.getItemMeta().getLore().contains("Youth I")) {
			for (Entity e : event.getAffectedEntities()) {
				if (!(e instanceof Ageable)) continue;
				
				Ageable ent = (Ageable) e;
				
				ent.setBaby();
				ent.setAgeLock(true);
			}
		} else if (item.getItemMeta().getLore().contains("Aging I")) {
			for (Entity e : event.getAffectedEntities()) {
				if (!(e instanceof Ageable)) continue;
				
				Ageable ent = (Ageable) e;
				
				ent.setAgeLock(false);
			}
		}
	}
}
