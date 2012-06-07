package com.koponya.AuctionMaster;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHelper {
	private ConfigHelper() {}
	
	/*
	 * Write a Location to a fileconfiguration object 
	 */
	public static void setLocation(Location loc, String node, FileConfiguration conf) {
		conf.set(node+".world", loc.getWorld().getName());
		conf.set(node+".x", loc.getX());
		conf.set(node+".y", loc.getY());
		conf.set(node+".z", loc.getZ());
		conf.set(node+".yaw", loc.getYaw());
		conf.set(node+".pitch", loc.getPitch());
	}
	
	public static Location getLocation(String node, FileConfiguration conf)
	{
		return new Location(
				Bukkit.getWorld(conf.getString(node+".world")),
				conf.getDouble(node+".x"),
				conf.getDouble(node+".y"),
				conf.getDouble(node+".z"),
				(float)conf.getDouble(node+".yaw"),
				(float)conf.getDouble(node+".pitch")
			);
	}
}
