package com.dsnyder.fountainofyouth.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;

import com.dsnyder.fountainofyouth.FountainOfYouth;
import com.dsnyder.fountainofyouth.permissions.PermissionManager;

public abstract class GenericCommand {
	
	private String name;
	private String description;
	private String usage;
	private String permission;
	
	public GenericCommand(String name, String description, String usage, String permission) {
		this.name = name;
		this.description = description;
		this.usage = usage;
		this.permission = permission;
		if (permission != null) {
			PermissionManager.getManager().registerPermission(permission);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public String getPermission() {
		return permission;
	}
	
	/**
	 * 
	 * @param items - item to enchant. Must be a splash potion
	 * @param l - lore to add 
	 * @return true if lore was added, false if item was not a splash potion
	 */
	protected boolean addSplashLore(ItemStack items, String l) {
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
	
	protected void removeFOYLore(ItemStack items) {
		
		// ensure it is a potion
		if (!items.getType().equals(Material.POTION)) return;
		// ensure it is a splash potion
		if (!Potion.fromItemStack(items).isSplash()) return;
		
		ItemMeta im = items.getItemMeta();
	    List<String> lore = new ArrayList<>();
	    if (im.hasLore()) {
	    	lore = im.getLore();
	    }
	    
	    lore.remove(FountainOfYouth.YOUTH_LORE);
	    lore.remove(FountainOfYouth.AGING_LORE);
	    im.setLore(lore);
	    items.setItemMeta(im);
	}
	
	public boolean execute(CommandSender sender, String[] args) {
		if (permission == null || PermissionManager.getManager().hasPermission(sender, getPermission())) {
			return _execute(sender, args);
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
			return true;
		}
	}
	
	protected abstract boolean _execute(CommandSender sender, String[] args);
}
