package me.Dupps.GuildX.Tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.Dupps.GuildX.Commands.user.GuildXHome;
import me.Dupps.GuildX.Main.Plugin;
import net.md_5.bungee.api.ChatColor;

public class HomeTP extends BukkitRunnable{
    private int counter;
    private Player p;
    public HomeTP(int counter, Player p) {
        if (counter < 1) {
            throw new IllegalArgumentException("Counter must be greater than 1");
        } else {
        	this.p = p;
            this.counter = counter;
        }
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        if (counter > 0) { 
        	p.sendTitle(ChatColor.BLUE + "Teleporting...",ChatColor.DARK_BLUE+""+counter--+"", 1, 20, 1);
        } else {
        	Location home = (Location) p.getMetadata("homeloc").get(0).value();
			p.removeMetadata("homeloc", Plugin.plugin);
			p.teleport(home);
			p.sendMessage(ChatColor.GREEN + "You have been teleported home!");
			GuildXHome.homemap.remove(p);
            this.cancel();
        }
    }
}
