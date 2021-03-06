package me.Dupps.GuildX.Guilds;

import java.util.ArrayList;

import org.bukkit.Location;

import me.Dupps.GuildX.Chunks.Chunks;


public class Guild {
	private String guildname,leader;
	private ArrayList<String> members,admins;
	private ArrayList<Chunks> chunks;
	private int lives,raidtimer;
	private Location home;
	private boolean raidable;
	
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
	public boolean isRaidable() {
		return raidable;
	}
	public void setRaidable(boolean raidable) {
		this.raidable = raidable;
	}
	public int getRaidtimer() {
		return raidtimer;
	}
	public void setRaidtimer(int raidtimer) {
		this.raidtimer = raidtimer;
	}
	public Location getHome() {
		return home;
	}
	public void setHome(Location home) {
		this.home = home;
	}
}
