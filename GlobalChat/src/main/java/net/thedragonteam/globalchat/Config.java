package net.thedragonteam.globalchat;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
	
	public static String channel = null;
	public static Boolean active = null;
	
	public static void initConfig(FMLPreInitializationEvent event){
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	
		config.load();
	
		channel = config.getString("channel", config.CATEGORY_GENERAL, "globalchat", "Every client in the same channel will recieve the messages sent from clients in the channel");
		active = config.getBoolean("active", config.CATEGORY_GENERAL, true, "true: GlobalChat is enabled, false: GlobalChat is disabled");
	
		config.save();
	
	}

}
