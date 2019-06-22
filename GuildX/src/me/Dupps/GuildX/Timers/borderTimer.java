package me.Dupps.GuildX.Timers;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Commands.user.GuildXHome;
import me.Dupps.GuildX.Main.Main;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.ChunkBorderManager;
import net.md_5.bungee.api.ChatColor;


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
        	updateBorder();
        	updateHomeTP();
        	borderInterval = 0;

        }
	}
	
	private void updateBorder() {
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
	}
	
	private void updateHomeTP() {
		for(Player p : GuildXHome.homemap.keySet()) {
			if(GuildXHome.homemap.get(p) > 0) {
				p.sendTitle(ChatColor.BLUE + "Teleporting...",ChatColor.DARK_BLUE+""+GuildXHome.homemap.get(p)+"", 1, 20, 1);
				GuildXHome.homemap.put(p, GuildXHome.homemap.get(p)-1);
			}
			else {
				Location home = (Location) p.getMetadata("homeloc").get(0).value();
				p.removeMetadata("homeloc", Plugin.plugin);
				p.teleport(home);
				p.sendMessage(ChatColor.GREEN + "You have been teleported home!");
				GuildXHome.homemap.remove(p);
			}
		}
	}
}
