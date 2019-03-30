package me.Dupps.GuildX.Main;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Dupps.GuildX.Managers.ConfigManager;

public class Events implements Listener{
	private ConfigManager cfgm = new ConfigManager();
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if (b.getMetadata("PLACED").toString().length() > 7) {
			e.getPlayer().sendMessage("EYO YOU PLACED THAT");
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

}
