package me.Dupps.GuildX.Managers;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;

public class GuildXAPI {
	
	private static ChunkMethods cm = new ChunkMethods();
	private static GuildMethods gm = new GuildMethods();
	
	public static boolean isInGuild(String playeruuid) {
		return gm.isInGuild(playeruuid);
	}
	public static boolean guildExists(String guildname) {
		return gm.guildExists(guildname);
	}
	public static Guild getGuildWithName(String guild) {
		return gm.getGuildwName(guild);
	}
	public static Guild getGuildWithPlayer(String playeruuid) {
		return gm.getGuildwPlayer(playeruuid);
	}
	public static boolean isAdmin(String playeruuid) {
		return gm.isAdmin(playeruuid);
	}
	public static boolean isLeader(String playeruuid) {
		return gm.isLeader(playeruuid);
	}
	public static boolean isMember(String playeruuid) {
		return gm.isMember(playeruuid);
	}
	public static boolean hasInvite(String playername, String guild) {
		return gm.hasInvite(playername, guild);
	}
	public static String getUUID(String playername) {
		return gm.getUUID(playername);
	}
	public static String getName(String playeruuid) {
		return gm.getName(playeruuid);
	}
	public static boolean isOffline(String playeruuid) {
		return gm.isOffline(playeruuid);
	}
	public static Player getPlayer(String playeruuid) {
		return gm.getPlayer(playeruuid);
	}
	public static ArrayList<String> getAllMembers(Guild g){
		return gm.getAllMembers(g);
	}
	public static void addPlayerToGuild(String playeruuid, String guild){
		gm.addPlayerToGuild(playeruuid,guild);
	}
	public static String getRank(String playeruuid){
		return gm.getRank(playeruuid);
	}
	public static boolean chunkIsClaimed(int x, int z){
		return cm.chunkIsClaimed(x,z);
	}
	public static String getChunkOwner(int x, int z){
		return cm.getChunkOwner(x,z);
	}
	public static boolean isBordering(int x, int z){
		return cm.isBordering(x,z);
	}
	public static String getBorderingGuild(int x, int z){
		return cm.getBorderingGuild(x,z);
	}

}
