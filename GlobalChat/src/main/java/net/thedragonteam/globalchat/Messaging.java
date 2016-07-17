package net.thedragonteam.globalchat;

import ibt.ortc.api.Ortc;
import ibt.ortc.extensibility.OnConnected;
import ibt.ortc.extensibility.OnMessage;
import ibt.ortc.extensibility.OnSubscribed;
import ibt.ortc.extensibility.OrtcClient;
import ibt.ortc.extensibility.OrtcFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Messaging {
	
	public static OrtcClient c = null;
	
	public static void initMessagingClient() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		Ortc api = new Ortc();
		OrtcFactory factory = api.loadOrtcFactory("IbtRealtimeSJ");
		 
		final OrtcClient client = factory.createClient();
		client.setClusterUrl("http://ortc-developers.realtime.co/server/2.1/");
		 
		client.onConnected = new OnConnected() {
		    @Override
		    public void run(OrtcClient sender) {

		        client.subscribe(Config.channel, true,
		          new OnMessage() {
		            @Override
		            public void run(OrtcClient sender, String channel, String message) {
		                
		            	String player = message.split(":", 2)[0];
		            	String msg = message.split(":", 2)[1];
		            	
		            	addToChat(player, msg);
		            
		            }
		        });
		    }
		};
		 
		client.onSubscribed = new OnSubscribed() {
		    @Override
		    public void run(OrtcClient sender, String channel) {
		    	
		        c = sender;
		        
		    }
		};
		 
		client.connect("[AppKey]", "[AppSecret]");
	
	}
	
	public static void initMessagingServer() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		Ortc api = new Ortc();
		OrtcFactory factory = api.loadOrtcFactory("IbtRealtimeSJ");
		 
		final OrtcClient client = factory.createClient();
		client.setClusterUrl("http://ortc-developers.realtime.co/server/2.1/");
		 
		client.onConnected = new OnConnected() {
		    @Override
		    public void run(OrtcClient sender) {

		        client.subscribe(Config.channel, true,
		          new OnMessage() {
		            @Override
		            public void run(OrtcClient sender, String channel, String message) {
		               
		            	
		            
		            }
		        });
		    }
		};
		 
		client.onSubscribed = new OnSubscribed() {
		    @Override
		    public void run(OrtcClient sender, String channel) {
		    	
		        c = sender;
		        
		    }
		};
		 
		client.connect("UiR56Z", "GxM7SNJlxK5S");
	
	}
	
	public static void send(String message){
		
		c.send(Config.channel, message);
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void addToChat(String player, String msg){
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new TextComponentString("<" + player + ">" + " " + msg));
		
	}
		
}
