package me.Dupps.GuildX.Guilds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.InviteManager;
import net.md_5.bungee.api.ChatColor;

public class GuildMethods {
	ConfigManager cfgm = new ConfigManager();
	
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
	
	public String getUUID(String name) {
		for(String key: cfgm.getPlayers().getKeys(false)) {
			if(key.equalsIgnoreCase(name)) {
				return cfgm.getPlayers().getString(key + ".uuid");
			}
		}
		return null;
	}
	
	public String getName(String uuid) {
		for(String key: cfgm.getPlayers().getKeys(false)) {
			if(cfgm.getPlayers().getString(key + ".uuid").equalsIgnoreCase(uuid)) {
				return key;
			}
		}
		return null;
	}
	
	public boolean isOffline(String uuid) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getUniqueId().toString().equalsIgnoreCase(uuid))
				return false;
		}
		return true;
	}
	
	public Player getPlayer(String uuid) {
		if(!isOffline(uuid)) {
			for(Player p : Bukkit.getOnlinePlayers())
				if(p.getUniqueId().toString().equalsIgnoreCase(uuid))
					return p;
		}
		return null;
	}
	
	public ArrayList<String> getAllMembers(Guild g){
		ArrayList<String> members = new ArrayList<String>();
		members.add(g.getLeader());
		for(String e : g.getAdmins())
			members.add(e);
		for(String e : g.getMembers())
			members.add(e);
		
		return members;
	}
	

	public void sendInviteMessage(String displayName, String guild) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(displayName)) {
				p.sendMessage(ChatColor.GREEN + "You have been invited to join the guild "+ChatColor.YELLOW + guild);
			}
		}
	}
	
	public boolean isPlayerOnline(String player) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(player))
				return true;
		}
		return false;
	}
	
	public void addPlayerToGuild(String puuid, String guild) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.toString().equalsIgnoreCase(guild)) {
				ArrayList<String> members = g.getMembers();
				try {
					members.add(puuid);
					System.out.println("added "+puuid+ " to guild "+g.toString());
					g.setMembers(members);
				}
				catch(NullPointerException e) {
					ArrayList<String> memb = new ArrayList<String>();
					memb.add(puuid);
					System.out.println("added "+puuid+ " to guild "+g.toString());
					g.setMembers(memb);
				}
			}
		}
	}
	
	public String getRank(String puuid) {
		for(Guild g : GuildManager.getGuilds()) {
			if(g.getMembers().contains(puuid))
				return "Member";
			if(g.getAdmins().contains(puuid))
				return "Admin";
			if(g.getLeader().equals(puuid))
				return "Leader";
		}
		return null;
	}
	
	public Player getPlayerWName(String player) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(player))
				return p;
		}
		return null;
	}
	
	public Player getPlayerWUUID(String uuid) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(uuid))
				return p;
		}
		return null;
	}
}
