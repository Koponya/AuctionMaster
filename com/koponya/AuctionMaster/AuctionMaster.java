package com.koponya.AuctionMaster;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		//super.saveConfig();
		try {
			this.data.save(new File(this.getDataFolder(), "data.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void copyDefault(String name) {
        try {
        	File file = new File(this.getDataFolder(),name);
        	if(file.exists()) return;
        	InputStream in = this.getResource(name);
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void onLoad() {
		this.log = this.getLogger();
		this.pluginName = this.getDescription().getName();
		this.loadConfig();
		this.saveConfig();
		copyDefault("hu.lang");
	}
	
	public void onEnable() {
		Lang.init(this.conf.getString("language"));
		infoLog(Lang.get("console.welcome"));
	}
	
	public void onDisable() {
		this.saveConfig();
		infoLog("Plugin disabled!");
	}
}
