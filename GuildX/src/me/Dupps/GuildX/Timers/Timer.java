package me.Dupps.GuildX.Timers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Main.Main;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.ChunkBorderManager;
import me.Dupps.GuildX.Managers.GuildManager;
import net.md_5.bungee.api.ChatColor;

public class Timer extends BukkitRunnable {
	private int borderInterval,lifeInterval,raidInterval;
	private long now;
    private long later;
    private Main plugin;
    public Timer() {
    	this.plugin = Plugin.plugin;
        borderInterval = 0;
        lifeInterval = 0;
        raidInterval = 0;
        now = System.currentTimeMillis();
        runTaskTimer(plugin, 2, 2);
    }
    
	@Override
	public void run() {
		borderTimer();
		lifeTimer();
		raidTimer();
		
	}
	
	public void borderTimer() {
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
	
	public void lifeTimer() {
		later = System.currentTimeMillis();
        if ((later - now) >= 1000) {
            int addition = (int) ((later - now) / 1000);
            lifeInterval += addition;
            now = System.currentTimeMillis();
        }
        if(lifeInterval >= Plugin.plugin.getConfig().getInt("default.lives.interval") && lifeInterval > 0) {
        	for(Guild g : GuildManager.getGuilds()) {
        		int incr = Plugin.plugin.getConfig().getInt("default.lives.increment");
        		if((g.getLives() + incr )<= Plugin.plugin.getConfig().getInt("default.lives.max") && !g.isRaidable()) {
        			g.setLives(g.getLives() +incr);
        		}
        		
        	}
        	lifeInterval = 0;

        }
	}
	
	public void raidTimer() {
		later = System.currentTimeMillis();
        if ((later - now) >= 1000) {
            int addition = (int) ((later - now) / 1000);
            raidInterval += addition;
            now = System.currentTimeMillis();
        }
        if(raidInterval >= 1 && raidInterval > 0) {
        	
        	Iterator<Map.Entry<Guild, Integer>> entries = GuildManager.raidableGuilds.entrySet().iterator();
        	
        	while (entries.hasNext()) {
        	    Map.Entry<Guild, Integer> entry = entries.next();
        	    
        	    if(entry.getValue() > 0)
        	    	entry.setValue(entry.getValue()-1);
        	    else {
        	    	entry.getKey().setRaidable(false);
        	    	Bukkit.getServer().broadcastMessage(ChatColor.RED + ""+ChatColor.BOLD + entry.getKey().toString() + " is no longer raidable");
        	    	entries.remove();
        	    }
        	}
        	
        	raidInterval = 0;

        }
	}

}
