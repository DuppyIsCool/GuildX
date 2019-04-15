package me.Dupps.GuildX.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Dupps.GuildX.Chunks.Chunks;
import me.Dupps.GuildX.Guilds.Guild;

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
		
		String[] cmds = {"create","delete","claim","unclaim","invite","kick","leave","join","promote","demote","info","reload"};
		for(String e : cmds)
			bannedNames.add(e);
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
			
			if(g.getChunks() != null && !g.getChunks().isEmpty()) {
				for(Chunks c : g.getChunks()) {
					cfgm.getGuilds().set(g.toString() + ".chunks." + c.toString() + ".x", c.getX());
					cfgm.getGuilds().set(g.toString() + ".chunks." + c.toString() + ".z", c.getZ());
				}
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
	
}
