package me.Dupps.GuildX.Main;


import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Commands.user.GuildXHome;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.MessageManager;
import me.Dupps.GuildX.Tasks.Raid;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener{
	private ConfigManager cfgm = new ConfigManager();
	private GuildMethods gm = new GuildMethods();
	private ChunkMethods cm = new ChunkMethods();
	private MessageManager msg = new MessageManager();
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if (!b.getMetadata("SPAWNED").isEmpty()) {
			e.setCancelled(true);
		}
		Chunk c = e.getBlock().getChunk();
		if(cm.chunkIsClaimed(c.getX(), c.getZ()) && !e.getPlayer().isOp() && !gm.getGuildwName(cm.getChunkOwner(c.getX(), c.getZ())).isRaidable()) {
			if(gm.isInGuild(e.getPlayer().getUniqueId().toString())) {
				if(!cm.getChunkOwner(c.getX(), c.getZ()).equalsIgnoreCase(gm.getGuildwPlayer(e.getPlayer().getUniqueId().toString()).toString()))
					e.setCancelled(true);
			}
			else
				e.setCancelled(true);
				
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getClickedBlock()!=null) {
			if(!e.getClickedBlock().getType().equals(Material.AIR)) {
				Chunk c = e.getClickedBlock().getChunk();
				if(cm.chunkIsClaimed(c.getX(), c.getZ()) && !e.getPlayer().isOp() && !gm.getGuildwName(cm.getChunkOwner(c.getX(), c.getZ())).isRaidable())
					if(gm.isInGuild(e.getPlayer().getUniqueId().toString())) {
						if(!cm.getChunkOwner(c.getX(), c.getZ()).equalsIgnoreCase(gm.getGuildwPlayer(e.getPlayer().getUniqueId().toString()).toString()))
							e.setCancelled(true);
					}
					else
						e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		int fromz = e.getFrom().getChunk().getZ();
		int fromx = e.getFrom().getChunk().getX();
		int endz = e.getTo().getChunk().getZ();
		int endx = e.getTo().getChunk().getX();
		Player p = e.getPlayer();
		
		//Entering from wilderness to a claim
		if(!cm.chunkIsClaimed(fromx, fromz) && cm.chunkIsClaimed(endx, endz)) {
			p.sendTitle("", ChatColor.YELLOW + cm.getChunkOwner(endx, endz), 2, 15, 2);
			msg.print("msg.guild.enterclaim", p, cm.getChunkOwner(endx, endz), null, cm.getChunkOwner(endx, endz));
		}
		
		//Entering from a claim to another claim
		else if(cm.chunkIsClaimed(fromx, fromz) && cm.chunkIsClaimed(endx, endz)) {
			if(!cm.getChunkOwner(fromx, fromz).equals(cm.getChunkOwner(endx, endz))) {
				p.sendTitle("", ChatColor.YELLOW + cm.getChunkOwner(endx, endz), 2, 15, 2);
				msg.print("msg.guild.enterclaim", p, cm.getChunkOwner(endx, endz), null, cm.getChunkOwner(endx, endz));
			}
				
		}
		//Entering wilderness from a claim
		else if(cm.chunkIsClaimed(fromx, fromz) && !cm.chunkIsClaimed(endx, endz)) {
			p.sendTitle("", ChatColor.GRAY + "Wilderness", 2, 15, 2);
		}
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Location movedFrom = e.getFrom();
        Location movedTo = e.getTo();
        Player p = e.getPlayer();
        //Checks if the player moved, ignoring yaw and pitch
        if (movedFrom.getBlockX() != movedTo.getBlockX() || 
        		movedFrom.getBlockY() != movedTo.getBlockY() || 
        		movedFrom.getBlockZ() != movedTo.getBlockZ()) {
        	
        	if(GuildXHome.homemap.containsKey(p)) {
        		GuildXHome.homemap.get(p).cancel();
    			GuildXHome.homemap.remove(p);
    			e.getPlayer().sendMessage(ChatColor.RED + "Teleportation canceled you moved!");
    		}
        }
	}
	
	@EventHandler
	public void blockPlaced(BlockPlaceEvent event) {
		Chunk c = event.getBlock().getChunk();
		if(cm.chunkIsClaimed(c.getX(), c.getZ()) && !event.getPlayer().isOp() && !gm.getGuildwName(cm.getChunkOwner(c.getX(), c.getZ())).isRaidable())
			if(gm.isInGuild(event.getPlayer().getUniqueId().toString())) {
				if(!cm.getChunkOwner(c.getX(), c.getZ()).equalsIgnoreCase(gm.getGuildwPlayer(event.getPlayer().getUniqueId().toString()).toString()))
					event.setCancelled(true);
			}
			else
				event.setCancelled(true);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		boolean hasJoined = false;
		try {
			for(String key : cfgm.getPlayers().getKeys(false)) {
				if(key.equalsIgnoreCase(e.getPlayer().getName()))
					hasJoined = true;
			}
		}
		catch(NullPointerException exception) {
			cfgm.getPlayers().set(e.getPlayer().getName() + ".uuid", e.getPlayer().getUniqueId().toString());
		}
		if(!hasJoined) {
			cfgm.getPlayers().set(e.getPlayer().getName() + ".uuid", e.getPlayer().getUniqueId().toString());
		}
	}
	
	@EventHandler
	//Used to prevent players from taking items from their guild info screens.
	public void onPlayerClickOnItem(InventoryClickEvent e){
        if(e.getRawSlot() == e.getSlot() && e.getView().getTitle().equals("Guild Display")){
        	e.setCancelled(true);
    	}    	
    }
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		//This is used when a player dies, their guild loses one life.
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(gm.isInGuild(p.getUniqueId().toString())) {
				Guild g = gm.getGuildwPlayer(p.getUniqueId().toString());
				if((g.getLives()-1) == 0) {
					g.setLives(0);
					g.setRaidable(true);
					@SuppressWarnings("unused")
					BukkitTask task = new Raid(Plugin.plugin.getConfig().getInt("default.guild.raidtime")+1,g).runTaskTimer(Plugin.plugin, 0, 20);
				}
				else if ((g.getLives()-1) > 0)
					g.setLives(g.getLives()-1);
			}
			
		}
	}
	
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
              
        Player p = e.getPlayer();
        if(Plugin.plugin.getConfig().getBoolean("default.guild.chatformat")) {
        	String guildprefix = Plugin.plugin.getConfig().getString("default.guild.guildprefix");
        	
	        if (gm.isInGuild(p.getUniqueId().toString())){ 
	             String message = e.getMessage();
	             guildprefix = guildprefix.replaceAll("%guild%", gm.getGuildwPlayer(p.getUniqueId().toString()).toString());
	             message = message.replaceAll("%guild%", guildprefix);
	             e.setMessage(message);
	        }
	        else {
	        	String message = e.getMessage();
	            message = message.replaceAll("%guild%", "");
	            e.setMessage(message);
	        }
        }
            
    }
	

}
