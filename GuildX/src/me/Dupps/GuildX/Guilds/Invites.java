package me.Dupps.GuildX.Guilds;

public class Invites {
	private String guild,uuid;
	private int time;
	
	public Invites(String player, String guild, int time) {
		this.uuid = player;
		this.guild = guild;
		this.time = time;
	}
	public String getPlayer() {
		return uuid;
	}
	public void setPlayer(String player) {
		this.uuid = player;
	}
	public String getGuild() {
		return guild;
	}
	public void setGuild(String guild) {
		this.guild = guild;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
