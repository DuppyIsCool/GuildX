package me.Dupps.GuildX.Main;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.ChunkBorderManager;
import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.GuildManager;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener{
	private ConfigManager cfgm = new ConfigManager();
	private GuildMethods gm = new GuildMethods();
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if (!b.getMetadata("SPAWNED").isEmpty()) {
			e.setCancelled(true);
			b.setType((Material) b.getMetadata("SPAWNED").get(0).value());
			b.removeMetadata("SPAWNED", Plugin.plugin);
			ChunkBorderManager.removeBlock(b);
		}
	}
	
	@EventHandler
	public void blockPlaced(BlockPlaceEvent event) {
		Block b = event.getBlock();
		b.setMetadata("PLACED", new FixedMetadataValue(Plugin.plugin, "something"));
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
