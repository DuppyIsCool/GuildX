package me.Dupps.GuildX.Timers;

import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Main.Main;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.GuildManager;

public class lifeTimer extends BukkitRunnable{
	private int lifeInterval;
	private long now;
    private long later;
    private Main plugin;
    
    public lifeTimer() {
    	this.plugin = Plugin.plugin;
        lifeInterval = 0;
        now = System.currentTimeMillis();
        runTaskTimer(plugin, 2, 2);
    }
    
	@Override
	public void run() {
		lifeTimers();
		
	}
	public void lifeTimers() {
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
}
