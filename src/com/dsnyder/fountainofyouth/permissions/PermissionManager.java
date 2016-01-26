package com.dsnyder.fountainofyouth.permissions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class PermissionManager {
	
	private List<String> permissions;
	private static PermissionManager main;
	
	public PermissionManager() {
		permissions = new ArrayList<>();
		main = this;
	}
	
	public void registerPermission(String permission) {
		permissions.add(permission);
	}
	
	public boolean hasPermission(Player pl, String perm) {
		
		if (pl.isOp()) return true;
		
		for (PermissionAttachmentInfo p : pl.getEffectivePermissions())  {
			String pname = p.getPermission();
			if (pname.endsWith(".*") && perm.startsWith(pname.substring(0, pname.length()-2))) {
				return true;
			} else if (pname.equalsIgnoreCase(perm)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static PermissionManager getManager() {
		return main;
	}
}
