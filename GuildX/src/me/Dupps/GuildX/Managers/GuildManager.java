package me.Dupps.GuildX.Managers;

import java.util.ArrayList;
import java.util.List;


import me.Dupps.GuildX.Guilds.Guild;

public class GuildManager {
	private static List<Guild> guilds = new ArrayList<Guild>();
	private ConfigManager cfgm = new ConfigManager();
	
	public void setupGuilds() {
		for(String guild : cfgm.getGuilds().getKeys(false)) {
			Guild g = new Guild();
			g.setLeader(cfgm.getGuilds().getString(guild + ".leader"));
			g.setMembers(cfgm.getGuilds().getStringList(guild + ".members"));
			g.setAdmins(cfgm.getGuilds().getStringList(guild + ".admins"));
			g.setLives(cfgm.getGuilds().getInt(guild + ".lives"));
			addGuild(g);
		}
	}
	
	public void saveGuilds() {
		for(Guild g : guilds) {
			cfgm.getGuilds().set(g.toString() + ".leader",g.getLeader());
			cfgm.getGuilds().set(g.toString() + ".members",g.getMembers());
			cfgm.getGuilds().set(g.toString() + ".admins",g.getAdmins());
			cfgm.getGuilds().set(g.toString() + ".lives",g.getLives());
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
