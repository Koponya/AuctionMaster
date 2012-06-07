package com.koponya.AuctionMaster;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class AuctionMaster extends JavaPlugin {

	private Logger log;
	private Permission perm;
	public FileConfiguration conf;
	public FileConfiguration data;
	public AuctionCommands command;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return command.onCommand(sender, cmd, label, args);
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perm = rsp.getProvider();
        return perm != null;
    }
	
	public boolean hasPerm(Player p, String perm) {
		return this.perm.has(p, perm) || p.isOp();
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
		copyDefault("config.yml");
		copyDefault("hu.lang");
		this.loadConfig();
	}
	
	public void onEnable() {
		Lang.init(this.conf.getString("language"));
		this.command = new AuctionCommands(this);
		setupPermissions();
		infoLog("Plugin enabled");
	}
	
	public void onDisable() {
		this.saveConfig();
		infoLog("Plugin disabled!");
	}
}
