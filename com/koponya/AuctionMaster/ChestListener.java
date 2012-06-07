package com.koponya.AuctionMaster;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
	
	//bukkit events
	@EventHandler
	public void chestClick(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		Player p = e.getPlayer();
		//get events
		if(events.containsKey(p.getName()) && b.getType().equals(Material.CHEST)) {
			ConfigHelper.setLocation(b.getLocation(), "chest", plugin.data);
			events.remove(p.getName());
			p.sendMessage(Lang.get("msg.set.ok"));
		}
		//get open
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.CHEST) && b.getLocation().equals(ConfigHelper.getLocation("chest", plugin.data))) {
			e.setCancelled(true);
			p.openInventory(PlayerInventorys.get(p.getName()).getInventory());
		}
	}
}
