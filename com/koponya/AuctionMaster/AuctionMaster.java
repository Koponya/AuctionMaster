package com.koponya.AuctionMaster;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AuctionMaster extends JavaPlugin {

	private Logger log;
	public FileConfiguration conf;
	public FileConfiguration data;
	public String pluginName;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return AuctionCommands.onCommand(sender, cmd, label, args);
	}
	
	public void infoLog(String msg) {
		log.info(msg);
	}

	public void warningLog(String msg) {
		log.warning(msg);
	}
	
	public void loadConfig() {
		this.conf = this.getConfig();
		this.data = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "data.yml"));
	}
	
	@Override
	public void saveConfig() {
		super.saveConfig();
		try {
			this.data.save(new File(this.getDataFolder(), "data.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onLoad() {
		this.log = this.getLogger();
		this.pluginName = this.getDescription().getName();
		this.loadConfig();
		this.saveConfig();
	}
	
	public void onEnable() {
		Lang.init(this.conf.getString("language"));
		infoLog("Plugin enabled!");
	}
	
	public void onDisable() {
		this.saveConfig();
		infoLog("Plugin disabled!");
	}
}
