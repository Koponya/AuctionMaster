package com.koponya.AuctionMaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Lang {

	private Lang() {}
	private static final Map<String,String> language = new HashMap<String, String>(); 

	public static boolean init(String lang) {
		if(!Lang.language.isEmpty())
			return false;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("plugins/AuctionMaster/hu.lang")), "UTF8"));
			String line;
			while((line=in.readLine())!=null) {
				try {
					String[] word = line.split("=", 2);
					Lang.language.put(word[0], word[1]);
				} catch (Exception ex) { }
			}
			in.close();
			if(!lang.equalsIgnoreCase("hu")) {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("plugins/AuctionMaster/"+lang+".lang")), "UTF8"));
				while((line=in.readLine())!=null) {
					try {
						String[] word = line.split("=", 2);
						Lang.language.put(word[0], word[1]);
					} catch (Exception ex) { }
				}
				in.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static String get(String key) {
		String ret = language.get(key);
		return (ret!=null?ret:"§cNot found: "+key);
	}
	
}
