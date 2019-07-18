package me.Dupps.GuildX.Tasks;

import java.util.HashMap;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Commands.user.GuildXClaim;
import me.Dupps.GuildX.Main.Plugin;


public class ChunkBorder extends BukkitRunnable {
	private int counter,claimcounter;
	private Chunk c;
    private HashMap<Block,Integer> blockmap;
    public ChunkBorder(int counter,int claimcounter,Chunk c, HashMap<Block,Integer> blockmap) {
    	if (counter < 1 || claimcounter < 1) {
            throw new IllegalArgumentException("Counter must be greater than 1");
        } else {
            this.counter = counter;
            this.blockmap = blockmap;
            this.c = c;
            this.claimcounter = claimcounter;
        }
    }
    
	@Override
	public void run() {
		if (counter > 0) { 
			updateBorder();
            counter--;
            
        } else if(claimcounter > 0) {
        	claimcounter--;
        }
        else {
        	GuildXClaim.chunkmap.remove(c);
        	this.cancel();
        }
	}

	
	private void updateBorder() {
		if(blockmap.size() > 0) {
	    	for(Block b : blockmap.keySet()) {
	    		if(blockmap.get(b) <= 0) {
	    			b.setType((Material) b.getMetadata("SPAWNED").get(0).value());
	    			b.removeMetadata("SPAWNED", Plugin.plugin);
	    		}
	    		else{
	    			blockmap.put(b, blockmap.get(b) -1);
	    		}
	    		
	    	}
		}
	}
}
