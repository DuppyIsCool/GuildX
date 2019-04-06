package me.Dupps.GuildX.Main;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Dupps.GuildX.Managers.ChunkBorderManager;
import me.Dupps.GuildX.Managers.ConfigManager;

public class Events implements Listener{
	private ConfigManager cfgm = new ConfigManager();
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

}
