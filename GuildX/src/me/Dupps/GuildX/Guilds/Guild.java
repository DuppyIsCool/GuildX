package me.Dupps.GuildX.Guilds;

import java.util.ArrayList;

import me.Dupps.GuildX.Chunks.Chunks;


public class Guild {
	private String guildname,leader;
	private ArrayList<String> members,admins;
	private ArrayList<Chunks> chunks;
	private int lives;
	
	public String getGuildname() {
		return guildname;
	}
	public void setGuildname(String guildname) {
		this.guildname = guildname;
	}
	public ArrayList<String> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	public ArrayList<String> getAdmins() {
		return admins;
	}
	public void setAdmins(ArrayList<String> admins) {
		this.admins = admins;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Override
	public String toString() {
		return guildname;
		
	}
	public ArrayList<Chunks> getChunks() {
		return chunks;
	}
	public void setChunks(ArrayList<Chunks> chunks) {
		this.chunks = chunks;
	}
}
