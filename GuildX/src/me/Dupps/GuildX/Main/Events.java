package me.Dupps.GuildX.Main;


import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.ChunkBorderManager;
import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.MessageManager;
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
			b.setType((Material) b.getMetadata("SPAWNED").get(0).value());
			b.removeMetadata("SPAWNED", Plugin.plugin);
			ChunkBorderManager.removeBlock(b);
		}
		Chunk c = e.getBlock().getChunk();
		if(cm.chunkIsClaimed(c.getX(), c.getZ()) && !e.getPlayer().isOp() && !gm.getGuildwName(cm.getChunkOwner(c.getX(), c.getZ())).isRaidable())
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getClickedBlock()!=null) {
			if(!e.getClickedBlock().getType().equals(Material.AIR)) {
				Chunk c = e.getClickedBlock().getChunk();
				if(cm.chunkIsClaimed(c.getX(), c.getZ()) && !e.getPlayer().isOp() && !gm.getGuildwName(cm.getChunkOwner(c.getX(), c.getZ())).isRaidable())
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
		
		if(!cm.chunkIsClaimed(fromx, fromz) && cm.chunkIsClaimed(endx, endz)) {
			p.sendTitle("", ChatColor.YELLOW + cm.getChunkOwner(endx, endz), 2, 15, 2);
			msg.print("msg.guild.enterclaim", p, cm.getChunkOwner(endx, endz), null, cm.getChunkOwner(endx, endz));
		}
		
		else if(cm.chunkIsClaimed(fromx, fromz) && cm.chunkIsClaimed(endx, endz)) {
			if(!cm.getChunkOwner(fromx, fromz).equals(cm.getChunkOwner(endx, endz))) {
				p.sendTitle("", ChatColor.YELLOW + cm.getChunkOwner(endx, endz), 2, 15, 2);
				msg.print("msg.guild.enterclaim", p, cm.getChunkOwner(endx, endz), null, cm.getChunkOwner(endx, endz));
			}
				
		}
	}
	
	@EventHandler
	public void blockPlaced(BlockPlaceEvent event) {
		Chunk c = event.getBlock().getChunk();
		if(cm.chunkIsClaimed(c.getX(), c.getZ()) && !event.getPlayer().isOp() && !gm.getGuildwName(cm.getChunkOwner(c.getX(), c.getZ())).isRaidable())
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
	public void onPlayerClickOnItem(InventoryClickEvent e){
        if(e.getRawSlot() == e.getSlot() && e.getInventory().getTitle().equals("Guild Display")){
        	e.setCancelled(true);
    	}    	
    }
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(gm.isInGuild(p.getUniqueId().toString())) {
				Guild g = gm.getGuildwPlayer(p.getUniqueId().toString());
				if((g.getLives()-1) == 0) {
					g.setLives(0);
					g.setRaidable(true);
					Bukkit.getServer().broadcastMessage(ChatColor.RED + ""+ChatColor.BOLD + g.toString() + " is now raidable!");
					GuildManager.raidableGuilds.put(g, Plugin.plugin.getConfig().getInt("default.guild.raidtime"));
				}
				else if ((g.getLives()-1) > 0)
					g.setLives(g.getLives()-1);
			}
			
		}
	}

}
