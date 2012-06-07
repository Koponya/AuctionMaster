package com.koponya.AuctionMaster;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class AuctionMaster extends JavaPlugin {

	public Logger log;
	public Configuration conf;
	public final String pluginName = this.getDescription().getName();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return AuctionCommands.onCommand(sender, cmd, label, args);
	}
	
	public void onEnabled() {
		this.log = this.getLogger();
		this.conf = this.getConfig();
		this.conf.options().copyDefaults(true);
		Lang.init("hu");
		
		log.info("["+pluginName+"] Plugin enabled!");
	}
	
	public void onDisabled() {
		log.info("["+pluginName+"] Plugin disabled!");
	}
}
