package net.thedragonteam.globalchat;

import java.util.Iterator;
import java.util.List;

import ibt.ortc.api.Ortc;
import ibt.ortc.extensibility.OnConnected;
import ibt.ortc.extensibility.OnMessage;
import ibt.ortc.extensibility.OnSubscribed;
import ibt.ortc.extensibility.OrtcClient;
import ibt.ortc.extensibility.OrtcFactory;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Handler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		
		if(Config.active){
			
			Messaging.initMessagingClient();
			
		}
        
    }
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onChatClient(ServerChatEvent event){
		
		if(Config.active){
			
			if(event.getUsername().equals(Minecraft.getMinecraft().thePlayer.getName())){
			
				Messaging.send(event.getUsername() + ":" + event.getMessage());
				event.setCanceled(true);
			
			}
			
		}
			
	}
	
	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public void onChatServer(ServerChatEvent event){
		
		if(Config.active){
			
			Messaging.send(event.getUsername() + ":" + event.getMessage());
			event.setCanceled(true);
			
		}
			
	}
	
}
