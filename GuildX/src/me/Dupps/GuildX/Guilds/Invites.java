package me.Dupps.GuildX.Guilds;

public class Invites {
	private String player,guild;
	private int time;
	
	public Invites(String player, String guild, int time) {
		this.player = player;
		this.guild = guild;
		this.time = time;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
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
