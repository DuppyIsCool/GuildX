package me.Dupps.GuildX.Timers;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Main.Main;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.ChunkBorderManager;


public class borderTimer extends BukkitRunnable {
	private int borderInterval;
	private long now;
    private long later;
    private Main plugin;
    public borderTimer() {
    	this.plugin = Plugin.plugin;
        borderInterval = 0;
        now = System.currentTimeMillis();
        runTaskTimer(plugin, 2, 2);
    }
    
	@Override
	public void run() {
		borderTimers();
		
	}
	
	public void borderTimers() {
		later = System.currentTimeMillis();
        if ((later - now) >= 1000) {
            int addition = (int) ((later - now) / 1000);
            borderInterval += addition;
            now = System.currentTimeMillis();
        }
        if(borderInterval >= 1 && borderInterval > 0) {
        	ArrayList<Block> removeBlock = new ArrayList<Block>();
        	for(Block b : ChunkBorderManager.blockmap.keySet()) {
        		if(ChunkBorderManager.getTime(b) <= 0) {
        			removeBlock.add(b);
        			b.setType((Material) b.getMetadata("SPAWNED").get(0).value());
        			b.removeMetadata("SPAWNED", Plugin.plugin);
        		}
        		else{
        			ChunkBorderManager.addBlock(b, ChunkBorderManager.getTime(b) -1);
        		}
        		
        	}
        	
        	for(Block b: removeBlock) {
        		ChunkBorderManager.removeBlock(b);
        	}
        	
        	
        	borderInterval = 0;

        }
	}
	

	

}
