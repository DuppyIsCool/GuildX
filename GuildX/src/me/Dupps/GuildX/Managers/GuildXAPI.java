package me.Dupps.GuildX.Managers;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;

public class GuildXAPI {
	
	private  ChunkMethods cm = new ChunkMethods();
	private   GuildMethods gm = new GuildMethods();
	
	public  boolean isInGuild(String playeruuid) {
		return gm.isInGuild(playeruuid);
	}
	public  boolean guildExists(String guildname) {
		return gm.guildExists(guildname);
	}
	public  Guild getGuildWithName(String guild) {
		return gm.getGuildwName(guild);
	}
	public  Guild getGuildWithPlayer(String playeruuid) {
		return gm.getGuildwPlayer(playeruuid);
	}
	public  boolean isAdmin(String playeruuid) {
		return gm.isAdmin(playeruuid);
	}
	public  boolean isLeader(String playeruuid) {
		return gm.isLeader(playeruuid);
	}
	public  boolean isMember(String playeruuid) {
		return gm.isMember(playeruuid);
	}
	public  boolean hasInvite(String playername, String guild) {
		return gm.hasInvite(playername, guild);
	}
	public  String getUUID(String playername) {
		return gm.getUUID(playername);
	}
	public  String getName(String playeruuid) {
		return gm.getName(playeruuid);
	}
	public  boolean isOffline(String playeruuid) {
		return gm.isOffline(playeruuid);
	}
	public  Player getPlayer(String playeruuid) {
		return gm.getPlayer(playeruuid);
	}
	public  ArrayList<String> getAllMembers(Guild g){
		return gm.getAllMembers(g);
	}
	public  void addPlayerToGuild(String playeruuid, String guild){
		gm.addPlayerToGuild(playeruuid,guild);
	}
	public  String getRank(String playeruuid){
		return gm.getRank(playeruuid);
	}
	public  boolean chunkIsClaimed(int x, int z){
		return cm.chunkIsClaimed(x,z);
	}
	public  String getChunkOwner(int x, int z){
		return cm.getChunkOwner(x,z);
	}
	public  boolean isBordering(int x, int z){
		return cm.isBordering(x,z);
	}
	public  String getBorderingGuild(int x, int z){
		return cm.getBorderingGuild(x,z);
	}

}