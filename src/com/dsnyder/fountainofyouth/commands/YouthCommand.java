package com.dsnyder.fountainofyouth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dsnyder.fountainofyouth.FountainOfYouth;

public class YouthCommand extends FOYCommand {

	public YouthCommand() {
		super("youth", "Enchants splash potion in your hand with Youth I", "youth", "foyouth.enchant.youth");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean _execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 0) return false;
		if (!(sender instanceof Player)) return false;
		
		Player player = (Player) sender;
		
		removeFOYLore(player.getItemInHand());
		
		if (addSplashLore(player.getItemInHand(), FountainOfYouth.YOUTH_LORE)) {
			player.sendMessage(ChatColor.GREEN + "Successfully enchanted");
		} else {
			player.sendMessage(ChatColor.RED + "Item must be a splash potion!");
		}
		
		return true;
	}

}
