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
					p.sendMessage(Lang.get("msg.reload.ok"));
					return true;
				}
			} else {
				//console
			}
			sender.sendMessage(Lang.get("command.usage"));
			return true;
		}
		
		return false;
	}
}
