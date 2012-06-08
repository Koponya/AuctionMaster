package com.koponya.AuctionMaster;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class AuctionThread extends Thread {

	public static AuctionThread current;
	
	private FileConfiguration conf;
	private double price;
	private ItemStack[] items;
	private String owner;
	private int leftTicks;
	
	public AuctionThread(String owner, ItemStack[] items, FileConfiguration conf) {
		this.conf = conf;
		this.price = 0;
		this.items = items;
		this.owner = owner;
		this.leftTicks = 10; //d
		AuctionThread.current = this;
	}
	
	public void run() {
		
	}
}
