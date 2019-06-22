package me.Dupps.GuildX.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.Dupps.GuildX.Chunks.Chunks;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Main.Plugin;

public class GuildManager {
	private static ArrayList<Guild> guilds = new ArrayList<Guild>();
	public static ArrayList<String> bannedNames = new ArrayList<String>();
	public static HashMap<Guild,Integer> raidableGuilds = new HashMap<Guild,Integer>();
	private ConfigManager cfgm = new ConfigManager();
	public void setupGuilds() {
		//Cycles through config file and sets up the object ArrayList of guilds
		for(String guild : cfgm.getGuilds().getKeys(false)) {
			Guild g = new Guild();
			g.setGuildname(guild);
			g.setLeader(cfgm.getGuilds().getString(guild + ".leader"));
			g.setMembers((ArrayList<String>) cfgm.getGuilds().getStringList(guild + ".members"));
			g.setAdmins((ArrayList<String>) cfgm.getGuilds().getStringList(guild + ".admins"));
			g.setLives(cfgm.getGuilds().getInt(guild + ".lives"));
			
			//Set home
			if(cfgm.getGuilds().getConfigurationSection(guild + ".home") != null) {
				Location home = new Location(Bukkit.getServer().getWorld(cfgm.getGuilds().getString(guild + ".home.world")),
						cfgm.getGuilds().getInt(guild + ".home.x"),
						cfgm.getGuilds().getInt(guild + ".home.y"),
						cfgm.getGuilds().getInt(guild + ".home.z"));
				g.setHome(home);
			}
			
			//Adds chunks
			ArrayList<Chunks> chunks = new ArrayList<Chunks>();
			if(cfgm.getGuilds().getConfigurationSection(guild + ".chunks") != null) {
				for(String chunk : cfgm.getGuilds().getConfigurationSection(guild + ".chunks").getKeys(false)) {
					int x = cfgm.getGuilds().getInt(guild + ".chunks."+chunk + ".x");
					int z = cfgm.getGuilds().getInt(guild + ".chunks."+chunk + ".z");
					Chunks c = new Chunks(x,z,g.toString());
					chunks.add(c);
				}
				g.setChunks(chunks);
			}
			addGuild(g);
		}
		//Sets up banned names
		getBannedNames();
	}
	
	public void saveGuilds() {
		//Clears the old config file
		for(String key : cfgm.getGuilds().getKeys(false))
			cfgm.getGuilds().set(key, null);
		
		//Saves guilds from object array to config
		for(Guild g : guilds) {
			cfgm.getGuilds().set(g.toString() + ".leader",g.getLeader());
			cfgm.getGuilds().set(g.toString() + ".members",g.getMembers());
			cfgm.getGuilds().set(g.toString() + ".admins",g.getAdmins());
			cfgm.getGuilds().set(g.toString() + ".lives",g.getLives());
			
			//Puts chunks into config
			if(g.getChunks() != null && !g.getChunks().isEmpty()) {
				for(Chunks c : g.getChunks()) {
					cfgm.getGuilds().set(g.toString() + ".chunks." + c.toString() + ".x", c.getX());
					cfgm.getGuilds().set(g.toString() + ".chunks." + c.toString() + ".z", c.getZ());
				}
			}
			
			if(g.getHome() != null) {
				Location loc = g.getHome();
				cfgm.getGuilds().set(g.toString() + ".home.x",loc.getBlockX());
				cfgm.getGuilds().set(g.toString() + ".home.y",loc.getBlockY());
				cfgm.getGuilds().set(g.toString() + ".home.z",loc.getBlockZ());
				cfgm.getGuilds().set(g.toString() + ".home.world",loc.getWorld().getName());
			}
		}
	}
	
	public static List<Guild> getGuilds() {
		return guilds;
	}

	public static void addGuild(Guild g) {
		guilds.add(g);
	}
	
	public static void removeGuild(Guild g) {
		guilds.remove(g);
	}
	
	private void getBannedNames() {
		String s = Plugin.plugin.getConfig().getString("default.guild.bannednames");
		String[] cmds = {"create","delete","claim","unclaim","invite","kick","leave","join","promote","demote","info","reload"};
		if(s != null && !s.isEmpty()) {
			String[] array = s.split(",");
			for(String e : array)
				bannedNames.add(e);
		}
		for(String e : cmds)
			bannedNames.add(e);
			
	}
	
}
