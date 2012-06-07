package com.koponya.AuctionMaster;

import java.util.HashMap;
import java.util.Map;

public class Lang {

	private Lang() {}
	private static final Map<String,String> language = new HashMap<String, String>(); 
	
	public static boolean init(String lang) {
		if(!Lang.language.isEmpty())
			return false;
		
		return false;
	}
	
}
