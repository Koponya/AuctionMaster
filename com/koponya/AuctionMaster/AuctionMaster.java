package com.koponya.AuctionMaster;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class AuctionMaster extends JavaPlugin {

	public Logger log;
	public final String pluginName = this.getDescription().getName();
	public void onEnabled() {
		this.log = Logger.getLogger("Minecraft");
		log.info("["+pluginName+"] Plugin enabled!");
	}
	
	public void onDisabled() {
		log.info("["+pluginName+"] Plugin disabled!");
	}
}
