package me.Dupps.GuildX.Guilds;

import me.Dupps.GuildX.Managers.GuildManager;

public class GuildMethods {

	public boolean isInGuild(String puuid) {
		
		for(Guild g : GuildManager.getGuilds()) {
			if(g.getAdmins() != null && g.getAdmins().contains(puuid)) {
				return true;
			}
			else if(g.getLeader() != null && g.getLeader().equals(puuid)) {
				return true;
			}
			else if(g.getMembers() != null && g.getMembers().contains(puuid)) {
				return true;
			}
		}
		
		return false;
	}
	
	public  boolean guildExists(String guild) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.toString().equalsIgnoreCase(guild))
				return true;
		}
		
		return false;
	}
	
	public Guild getGuildwName(String guild) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.toString().equalsIgnoreCase(guild))
				return g;
		}
		
		return null;
	}
	
	public Guild getGuildwPlayer(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.getLeader().equalsIgnoreCase(puuid))
				return g;
			if(g.getAdmins().contains(puuid))
				return g;
			if(g.getMembers().contains(puuid))
				return g;
		}
		return null;
	}
	
	public boolean isAdmin(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.getLeader().equalsIgnoreCase(puuid))
				return false;
			if(g.getAdmins().contains(puuid))
				return true;
			if(g.getMembers().contains(puuid))
				return false;
		}
		
		return false;
	}
	
	public boolean isLeader(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.getLeader().equalsIgnoreCase(puuid))
				return true;
			if(g.getAdmins().contains(puuid))
				return false;
			if(g.getMembers().contains(puuid))
				return false;
		}
		
		return false;
	}
	
	public boolean isMember(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.getLeader().equalsIgnoreCase(puuid))
				return false;
			if(g.getAdmins().contains(puuid))
				return false;
			if(g.getMembers().contains(puuid))
				return true;
		}
		
		return false;
	}
	
	
}
