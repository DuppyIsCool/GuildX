package me.Dupps.GuildX.Managers;

import java.util.HashMap;

import org.bukkit.block.Block;

public class ChunkBorderManager {
	public static HashMap<Block, Integer> blockmap = new HashMap<Block,Integer>();
	
	public static int getTime(Block b) {
		return blockmap.get(b);
	}
	
	//Putting a block that already exists in the blockmap simply overrides the value
	public static void addBlock(Block b, int time) {
		blockmap.put(b, time);
	}
	
	public static boolean isATimedBlock(Block b) {
		return blockmap.containsKey(b);
	}
	
	public static void removeBlock(Block b) {
		blockmap.remove(b);
	}
	
}
