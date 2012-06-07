package com.koponya.AuctionMaster;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PlayerInventorys implements InventoryHolder{

	private Inventory inv;
	private static final Map<String, PlayerInventorys> inventorys = new HashMap<String, PlayerInventorys>();
	
	public static PlayerInventorys get(String name) {
		PlayerInventorys ret = inventorys.get(name);
		if(ret==null) {
			ret = new PlayerInventorys();
			inventorys.put(name, ret);
		}
		return ret;
	}
	
	public PlayerInventorys() {
		this.inv = Bukkit.createInventory(this, InventoryType.CHEST);
	}
	
	@Override
	public Inventory getInventory() {
		return inv;
	}

}
