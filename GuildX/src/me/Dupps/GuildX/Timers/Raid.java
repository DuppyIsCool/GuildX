package me.Dupps.GuildX.Timers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Guilds.Guild;
import net.md_5.bungee.api.ChatColor;

public class Raid extends BukkitRunnable {
	private int counter;
    private Guild g;
    public Raid(int counter, Guild g) {
        if (counter < 1) {
            throw new IllegalArgumentException("Counter must be greater than 1");
        } else {
        	this.g = g;
            this.counter = counter;
            Bukkit.broadcastMessage(ChatColor.RED + g.toString() + " is now raidable!");
        }
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        if (counter > 0) { 
        	counter--;
        } else {
        	g.setLives(1);
        	Bukkit.broadcastMessage(ChatColor.GREEN + "The raid on "+g.toString() + " has ended!");
            this.cancel();
        }
    }
}
