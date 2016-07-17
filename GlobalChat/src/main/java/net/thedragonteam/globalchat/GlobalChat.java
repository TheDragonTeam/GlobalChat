package net.thedragonteam.globalchat;

import ibt.ortc.extensibility.OnConnected;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = GlobalChat.MODID, version = GlobalChat.VERSION, name = GlobalChat.MODNAME)
public class GlobalChat
{
    public static final String MODID = "globalchat";
    public static final String VERSION = "1.0";
    public static final String MODNAME = "GlobalChat";
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)  {
		
    	Config.initConfig(event);
    	System.out.println("Config Initialized");
    	
        FMLCommonHandler.instance().bus().register(new Handler());
        System.out.println("Event Handler Initialized");
		
	}
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    	
    	
    }
    
	@SideOnly(Side.SERVER)
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
    	
		if(Config.active){
			
			Messaging.initMessagingServer();
			
		}
    	
    }
    
}
