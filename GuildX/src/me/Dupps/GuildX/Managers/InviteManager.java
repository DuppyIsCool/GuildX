package me.Dupps.GuildX.Managers;

import java.util.ArrayList;

import me.Dupps.GuildX.Guilds.Invites;

public class InviteManager {
	private static ArrayList<Invites> invites = new ArrayList<Invites>();
	
	public static ArrayList<Invites> getInvites(){
		return invites;
	}
	
	public static void addInvite(Invites i) {
		invites.add(i);
	}
	
	public static void deleteInvite(String player, String guild) {
		for(Invites i : invites) {
			if(i.getPlayer().equalsIgnoreCase(player) && i.getGuild().equalsIgnoreCase(guild)) {
				invites.remove(i);
			}
		}
	}
}
