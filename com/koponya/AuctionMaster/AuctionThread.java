package com.koponya.AuctionMaster;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class AuctionThread extends Thread {

	public static AuctionThread current;
	
	private FileConfiguration conf;
	private double price;
	private ItemStack[] items;
	private String owner;
	private long timeOut;
	private long nextTick;
	public boolean run;
	
	public AuctionThread(String owner, ItemStack[] items, FileConfiguration conf) {
		this.conf = conf;
		this.price = 0;
		this.items = items;
		this.owner = owner;
		AuctionThread.current = this;
	}
	
	public void run() {
		this.timeOut = System.currentTimeMillis()+conf.getInt("auctionTime")*1000;
		this.nextTick = System.currentTimeMillis()+conf.getInt("auctionTick")*1000;
		this.run = true;
		while(run) {
			long now = System.currentTimeMillis();
			Bukkit.getServer().broadcastMessage("tick");
			
			if(timeOut<now) {
				run = false;
				break;
			}
			nextTick += conf.getInt("auctionTick")*1000L;
			try {
				Thread.sleep(nextTick-now);
			} catch (Exception ex) { }
		}
		AuctionThread.current = null;
	}
}
