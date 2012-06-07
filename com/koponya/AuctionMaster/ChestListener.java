package com.koponya.AuctionMaster;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Listener;

public class ChestListener implements Listener {

	private AuctionMaster plugin;
	private Map<String,EventType> events = new HashMap<String, EventType>();
	
	public enum EventType {Set,Remove,Check}
	
	public ChestListener(AuctionMaster instance) {
		this.plugin = instance;
	}
	
	public void add(String name, EventType event) {
		this.events.put(name, event);
	}
	
}
