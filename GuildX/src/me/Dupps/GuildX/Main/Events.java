package me.Dupps.GuildX.Main;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Events implements Listener{
	
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
		System.out.println("new metadata");
		b.setMetadata("PLACED", new FixedMetadataValue(Plugin.plugin, "something"));
	}

}
