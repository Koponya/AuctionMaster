package com.koponya.AuctionMaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class AuctionThread extends Thread {

	public static AuctionThread current;
	
	private final List<Integer> tools = new ArrayList(Arrays.asList(256,257,258,261,267,268,269,270,271,272,273,274,275,276,277,278,279,283,284,285,286,290,291,292,293,294,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317));
	private FileConfiguration conf;
	private int bid;
	private int itemId;
	private byte itemD;
	private int itemAmount;
	private ItemStack itemObj;
	private String owner;
	private String maxBidder;
	private long timeOut;
	private long nextTick;
	public boolean run;
	
	public AuctionThread(String owner, ItemStack[] items, FileConfiguration conf, int bid) {
		this.conf = conf;
		this.bid = 0;
		this.owner = owner;
		this.bid = bid;
		if(getItem(items))
			AuctionThread.current = this;
	}
	
	/*
	 * @return null if 
	 */
	private boolean getItem(ItemStack[] is) {
		itemId = 0;
		itemD = 0;
		itemAmount = 0;
		for(ItemStack i : is) {
			if(i==null) continue;
			if(tools.contains(i.getTypeId())) {
				if(itemObj!=null) {
					itemId=1;
					break;
				}
				itemObj = i;
			} else if(itemId==0) {
				itemId = i.getTypeId();
				itemD = i.getData().getData();
				itemAmount = i.getAmount();
			} else if(itemId==i.getTypeId() && itemD==i.getData().getData()) {
				itemAmount += i.getAmount();
			} else {
				itemObj = new ItemStack(1);
			}
		}
		if(itemObj!=null && itemId>0) {
			itemId = 0;
			itemD = 0;
			itemAmount = 0;
			itemObj = null;
			return false;
		}
		return true;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	synchronized public boolean bid(String name, int bid) {
		if(bid<=this.bid) return false;
		this.bid = bid;
		this.maxBidder = name;
		return true;
	}
	
	public void run() {
		this.timeOut = System.currentTimeMillis()+conf.getInt("auctionTime")*1000;
		this.nextTick = System.currentTimeMillis()+conf.getInt("auctionTick")*1000;
		this.run = true;
		int i = 0;
		while(run) {
			long now = System.currentTimeMillis();
			if(i++==0) {
				this.sendFormatedBroadcast(Lang.get("msg.auction.first"));
			} else {
				this.sendFormatedBroadcast(Lang.get("msg.auction.tick"));
			}
			
			
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
	
	private void sendFormatedBroadcast(String msg) {
		Bukkit.getServer().broadcastMessage(msg
				.replace("%owner%", owner==null?"":owner)
				.replace("%maxbid%", maxBidder==null?"":maxBidder)
				.replace("%amount%", Integer.toString(itemAmount))
				.replace("%item%", itemId==0?"":Material.getMaterial(itemId).name().toLowerCase().replace("_", " "))
			);
	}
}
