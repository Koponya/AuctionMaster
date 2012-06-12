package com.koponya.AuctionMaster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.koponya.AuctionMaster.ChestListener.EventType;

public class AuctionCommands {

	private final AuctionMaster plugin;
	public AuctionCommands(AuctionMaster instance) {
		this.plugin = instance;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if((label.equalsIgnoreCase("auctionmaster") || label.equalsIgnoreCase("am"))) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(args.length==1 && args[0].equalsIgnoreCase("set")) {
					if(!plugin.hasPerm(p, "auctionmaster.set")) {
						p.sendMessage(Lang.get("msg.nopermission"));
						return true;
					}
					plugin.chestListener.add(p.getName(), EventType.Set);
					p.sendMessage(Lang.get("msg.set"));
					return true;
				}
	
				if(args.length==1 && args[0].equalsIgnoreCase("remove")) {
					p.sendMessage("remove");
					return true;
				}

				if(args.length==1 && args[0].equalsIgnoreCase("reload")) {
					if(!plugin.hasPerm(p, "auctionmaster.reload")) {
						p.sendMessage(Lang.get("msg.nopermission"));
						return true;
					}
					plugin.loadConfig();
					p.sendMessage(Lang.get("msg.reload"));
					plugin.infoLog(Lang.get("msg.reload.console").replace("%name%", p.getName()));
					return true;
				}
			} else {
				//console only
				if(args.length==1 && args[0].equalsIgnoreCase("reload")) {
					plugin.loadConfig();
					sender.sendMessage(Lang.get("msg.reload"));
					return true;
				}

			}
			sender.sendMessage(Lang.get("msg.usage"));
			return true;
		}
		
		if(label.equalsIgnoreCase("bid")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(!plugin.hasPerm(p, "auctionmaster.bid")) {
					p.sendMessage(Lang.get("msg.nopermission"));
					return true;
				}
				if(args.length!=1) {
					p.sendMessage(Lang.get("msg.bid.usage"));
					return true;
				}
				if(args[0].equalsIgnoreCase("info")) {
					
				} else {
					int arg = 0;
					try {
						arg = Integer.parseInt(args[0]);
					} catch (Exception ex) {
						p.sendMessage(Lang.get("msg.bid.usage"));
						return true;
					}
					if(AuctionThread.current==null) {
						new AuctionThread(p.getName(), PlayerInventorys.get(p.getName()).getInventory().getContents(), plugin.conf, plugin.data, arg);
						if(AuctionThread.current==null) {
							p.sendMessage(Lang.get("msg.auction.cantstart"));
						} else {
							AuctionThread.current.start();
						}
						return true;
					} else {
						//van-e elég pénz?
						AuctionThread.current.bid(p.getName(), arg);
					}
				}
			}
		}
		
		return false;
	}
}
