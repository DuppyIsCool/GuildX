package me.Dupps.GuildX.Timers;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Main.Main;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.GuildManager;
import net.md_5.bungee.api.ChatColor;

public class raidTimer extends BukkitRunnable {
	private int raidInterval;
	private long now;
    private long later;
    private Main plugin;
	public raidTimer() {
	    	this.plugin = Plugin.plugin;
	        raidInterval = 0;
	        now = System.currentTimeMillis();
	        runTaskTimer(plugin, 2, 2);
    }
	@Override
	public void run() {
		raidTimers();
		
	}
	public void raidTimers() {
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
        	    	entry.getKey().setLives(1);
        	    	Bukkit.getServer().broadcastMessage(ChatColor.RED + ""+ChatColor.BOLD + entry.getKey().toString() + " is no longer raidable");
        	    	entries.remove();
        	    }

        	}
        	
        	raidInterval = 0;

        }
	}

}
