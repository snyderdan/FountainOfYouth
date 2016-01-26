package com.dsnyder.fountainofyouth;

import org.bukkit.plugin.java.JavaPlugin;

import com.dsnyder.fountainofyouth.commands.CommandManager;
import com.dsnyder.fountainofyouth.permissions.PermissionManager;

public class FountainOfYouth extends JavaPlugin {
	
	public static final String VERSION_KEY		= "FOYVersion";
	public static final String CURRENT_VERSION	= "1.2.0-ALPHA";
	
	public static final String YOUTH_LORE = "Youth I";
	public static final String AGING_LORE = "Aging I";
	
	static JavaPlugin main;
	
	@Override
	public void onEnable() {
		main = this;
		new PermissionManager();	// simply call constructor to initialize static manager
		getServer().getPluginManager().registerEvents(new YouthListener(), this);
		getCommand("foyouth").setExecutor(new CommandManager());
	}
	
	@Override
	public void onDisable() {
		
	}
}
