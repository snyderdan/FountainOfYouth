package com.dsnyder.fountainofyouth;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class FountainOfYouth extends JavaPlugin {
	public static final String VERSION_KEY		= "FOYVersion";
	public static final String CURRENT_VERSION	= "0.0.1-ALPHA";
	
	static JavaPlugin main;
	
	@Override
	public void onEnable() {
		main = this;
		getServer().getPluginManager().registerEvents(new YouthListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
    public boolean onCommand(CommandSender sender, 
    		Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("youth")) {
			if (args.length != 0) return false;
			
			if (!(sender instanceof Player)) return false;
			
			Player player = (Player) sender;
			
			if (player.getItemInHand().getType().isEdible()) {
				ItemStack items = player.getItemInHand();
				ItemMeta im = items.getItemMeta();
			    List<String> lore = new ArrayList<>();
			    if (im.hasLore()) {
			    	lore = im.getLore();
			    }
			    
			    lore.add("Youth I");
			    im.setLore(lore);
			    player.sendMessage("Successfully enchanted");
			    items.setItemMeta(im);
			} else {
				player.sendMessage(ChatColor.RED + "Item is not edible!");
			}
			
			return true;
			
		} else if (command.getName().equalsIgnoreCase("fountainofyouth")) {
			if (args.length > 0 && args[0].equalsIgnoreCase("version")) {
				sender.sendMessage("Fountain of Youth Version " + CURRENT_VERSION);
				return true;
			}
		}
		return false;
    }
}
