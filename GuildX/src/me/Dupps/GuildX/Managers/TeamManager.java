package me.Dupps.GuildX.Managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;


public class TeamManager {
	private static ScoreboardManager scmanager = Bukkit.getScoreboardManager();
	private static Scoreboard gboard = scmanager.getMainScoreboard();
	
	public static void createTeam(String name) {
		Team t = gboard.registerNewTeam(name);
		t.setPrefix(ChatColor.BLUE + "["+ChatColor.AQUA+name +ChatColor.BLUE+ "] ");
		t.setAllowFriendlyFire(false);
		t.setCanSeeFriendlyInvisibles(true);
	}
	
	public static void removeTeam(String name) {
		if(gboard.getTeam(name) != null) {
			Team t = gboard.getTeam(name);
			t.unregister();
		}
	}
	
	public static void addPlayer(Player p, String team) {
		if(gboard.getTeam(team) != null) {
			gboard.getTeam(team).addEntry(p.getName());
		}
	}
	
	public static void removePlayer(Player p, String team) {
		if(gboard.getTeam(team) != null) {
			if(gboard.getTeam(team).hasEntry(p.getName())) {
				gboard.getTeam(team).removeEntry(p.getName());
			}
		}
	}
	
	public static boolean teamExists(String team) {
		if(gboard.getTeam(team) != null)
			return true;
		else
			return false;
	}
	public static Team getTeam(String team) {
		if(gboard.getTeam(team) != null) {
			return gboard.getTeam(team);
		}
		else
			return null;
	}
	
	
}
