package com.dsnyder.fountainofyouth;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;

public class FountainOfYouth extends JavaPlugin {
	
	public static final String VERSION_KEY		= "FOYVersion";
	public static final String CURRENT_VERSION	= "0.1.2-ALPHA";
	
	public static final String YOUTH_LORE = "Youth I";
	public static final String AGING_LORE = "Aging I";
	
	static JavaPlugin main;
	
	@Override
	public void onEnable() {
		main = this;
		getServer().getPluginManager().registerEvents(new YouthListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/**
	 * 
	 * @param items - item to enchant. Must be a splash potion
	 * @param l - lore to add 
	 * @return true if lore was added, false if item was not a splash potion
	 */
	private boolean addSplashLore(ItemStack items, String l) {
		// ensure it is a potion
		if (!items.getType().equals(Material.POTION)) return false;
		// ensure it is a splash potion
		if (!Potion.fromItemStack(items).isSplash()) return false;
		
		ItemMeta im = items.getItemMeta();
	    List<String> lore = new ArrayList<>();
	    if (im.hasLore()) {
	    	lore = im.getLore();
	    }
	    
	    lore.add(l);
	    im.setLore(lore);
	    items.setItemMeta(im);
	    return true;
	}
	
	private void removeFOYLore(ItemStack items) {
		
		// ensure it is a potion
		if (!items.getType().equals(Material.POTION)) return;
		// ensure it is a splash potion
		if (!Potion.fromItemStack(items).isSplash()) return;
		
		ItemMeta im = items.getItemMeta();
	    List<String> lore = new ArrayList<>();
	    if (im.hasLore()) {
	    	lore = im.getLore();
	    }
	    
	    lore.remove(YOUTH_LORE);
	    lore.remove(AGING_LORE);
	    im.setLore(lore);
	    items.setItemMeta(im);
	}
	
	@Override
    public boolean onCommand(CommandSender sender, 
    		Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("youth")) {
			
			if (args.length != 0) return false;
			if (!(sender instanceof Player)) return false;
			
			Player player = (Player) sender;
			
			removeFOYLore(player.getItemInHand());
			
			if (addSplashLore(player.getItemInHand(), YOUTH_LORE)) {
				player.sendMessage(ChatColor.GREEN + "Successfully enchanted");
			} else {
				player.sendMessage(ChatColor.RED + "Item must be a splash potion!");
			}
			
			return true;
			
		}  else if (command.getName().equalsIgnoreCase("aging")) {
			
			if (args.length != 0) return false;
			if (!(sender instanceof Player)) return false;
			
			Player player = (Player) sender;
			
			removeFOYLore(player.getItemInHand());
			
			if (addSplashLore(player.getItemInHand(), AGING_LORE)) {
				player.sendMessage(ChatColor.GREEN + "Successfully enchanted");
			} else {
				player.sendMessage(ChatColor.RED + "Item must be a splash potion!");
			}
			
			return true;
			
		} else if (command.getName().equalsIgnoreCase("fountainofyouth")) {
			if (args.length > 0 && args[0].equalsIgnoreCase("version")) {
				sender.sendMessage(ChatColor.YELLOW + "Fountain of Youth Version " + CURRENT_VERSION);
				return true;
			} else {
				sender.sendMessage(ChatColor.GOLD + "/foyouth [version]:" + ChatColor.WHITE + " Main command. See /help fountainofyouth for more commands");
				return true;
			}
		}
		return false;
    }
}
