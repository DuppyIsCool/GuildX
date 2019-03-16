package me.Dupps.GuildX.Guilds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.InviteManager;
import net.md_5.bungee.api.ChatColor;

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
			if(!g.getLeader().equals(null) && g.getLeader().equalsIgnoreCase(puuid))
				return g;
			if(!g.getAdmins().equals(null) && g.getAdmins().contains(puuid))
				return g;
			if(!g.getMembers().equals(null) && g.getMembers().contains(puuid))
				return g;
		}
		return null;
	}
	
	public boolean isAdmin(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(!g.getLeader().equals(null) && g.getLeader().equalsIgnoreCase(puuid))
				return false;
			if(!g.getAdmins().equals(null) && g.getAdmins().contains(puuid))
				return true;
			if(!g.getMembers().equals(null) && g.getMembers().contains(puuid))
				return false;
		}
		
		return false;
	}
	
	public boolean isLeader(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(!g.getLeader().equals(null) && g.getLeader().equalsIgnoreCase(puuid))
				return true;
			if(!g.getAdmins().equals(null) && g.getAdmins().contains(puuid))
				return false;
			if(!g.getMembers().equals(null) && g.getMembers().contains(puuid))
				return false;
		}
		
		return false;
	}
	
	public boolean isMember(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(!g.getLeader().equals(null) && g.getLeader().equalsIgnoreCase(puuid))
				return false;
			if(!g.getAdmins().equals(null) && g.getAdmins().contains(puuid))
				return false;
			if(!g.getMembers().equals(null) && g.getMembers().contains(puuid))
				return true;
		}
		
		return false;
	}
	
	public boolean hasInvite(String player, String guild) {
		for(Invites i : InviteManager.getInvites()) {
			if(i.getPlayer().equalsIgnoreCase(player) && i.getGuild().equalsIgnoreCase(guild)) {
				return true;
			}
			
		}
		return false;
	}

	public void sendInviteMessage(String displayName, String guild) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(displayName)) {
				p.sendMessage(ChatColor.GREEN + "You have been invited to join the guild "+ChatColor.YELLOW + guild);
			}
		}
	}
	
	public void addPlayerToGuild(String puuid, String guild) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.toString().equalsIgnoreCase(guild)) {
				List<String> members = g.getMembers();
				try {
					members.add(puuid);
					g.setMembers(members);
				}
				catch(NullPointerException e) {
					List<String> memb = new ArrayList<String>();
					memb.add(puuid);
					g.setMembers(memb);
				}
			}
		}
	}
}
