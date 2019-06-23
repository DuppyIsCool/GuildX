package me.Dupps.GuildX.Tasks;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Commands.user.GuildXClaim;
import me.Dupps.GuildX.Main.Plugin;


public class ChunkBorder extends BukkitRunnable {
	private int counter;
    private HashMap<Block,Integer> blockmap;
    public ChunkBorder(int counter,HashMap<Block,Integer> blockmap) {
    	if (counter < 1) {
            throw new IllegalArgumentException("Counter must be greater than 1");
        } else {
            this.counter = counter;
            this.blockmap = blockmap;
        }
    }
    
	@Override
	public void run() {
		if (counter > 0) { 
			updateBorder();
            counter--;
        } else {
            this.cancel();
        }
	}

	
	private void updateBorder() {
    	for(Block b : blockmap.keySet()) {
    		if(blockmap.get(b) <= 0) {
    			b.setType((Material) b.getMetadata("SPAWNED").get(0).value());
    			b.removeMetadata("SPAWNED", Plugin.plugin);
    			GuildXClaim.chunkmap.remove(b.getChunk());
    		}
    		else{
    			blockmap.put(b, blockmap.get(b) -1);
    		}
    		
    	}
    	
	}
	
	
}
