package com.dsnyder.fountainofyouth;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * 
 */
public class YouthListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled=true)
	public void PlayerInteractEvent(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		ItemStack item = player.getItemInHand();
		
		if (!item.getType().isEdible()) return;
		if (!item.getItemMeta().hasLore()) return;
		if (!item.getItemMeta().getLore().contains("Youth I")) return;
		if (!(e.getRightClicked() instanceof Ageable)) return;
		
		Ageable ent = (Ageable) e.getRightClicked();
		
		ent.setBaby();
		ent.setAgeLock(true);
	}
}
