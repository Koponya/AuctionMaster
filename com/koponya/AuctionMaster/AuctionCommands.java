package com.koponya.AuctionMaster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AuctionCommands {

	private final AuctionMaster plugin;
	public AuctionCommands(AuctionMaster instance) {
		this.plugin = instance;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if((label.equalsIgnoreCase("auctionmaster") || label.equalsIgnoreCase("am"))) {
			if(args.length==1 && args[0].equalsIgnoreCase("set")) {
				
			}
		}
		
		sender.sendMessage(Lang.get("command.usage"));
		return false;
	}
}
