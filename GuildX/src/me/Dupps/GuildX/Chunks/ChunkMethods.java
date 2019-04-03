package me.Dupps.GuildX.Chunks;

import java.util.ArrayList;

import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Managers.GuildManager;

public class ChunkMethods {

	public boolean chunkIsClaimed(int x, int z) {
		for(Guild g : GuildManager.getGuilds()) {
			try {
				for(Chunks c : g.getChunks()) {
					if(c.getX() == x && c.getZ() == z)
						return true;
				}
			}
			catch(NullPointerException e) {}
		}
		return false;
	}
	
	public String getChunkOwner(int x,int z) {
		for(Guild g : GuildManager.getGuilds()) {
			for(Chunks c : g.getChunks()) {
				if(c.getX() == x && c.getZ() == z)
					return g.toString();
			}
		}
		return null;
	}
	
	public ArrayList<Chunks> removeChunk(ArrayList<Chunks> chunks, int x, int z) {
		if(chunks != null && !chunks.isEmpty()) {
			for(Chunks c : chunks) {
				if(c.getZ() == z && c.getX() == x) {
					chunks.remove(c);
					return chunks;
				}
			}
		
		}
		return null;
	}
	
	public boolean isBordering(int x, int z) {
		for(Guild g : GuildManager.getGuilds()) {
			try {
				for(Chunks c : g.getChunks()) {
					//Checks for the bordering chunks
					if((c.getX() + 1 == x && c.getZ() == z)
						||(c.getX() - 1 == x && c.getZ() == z)
						||(c.getX() == x && c.getZ() - 1 == z)
						||(c.getX() == x && c.getZ() + 1 == z))
						return true;
				}
			}
			catch(NullPointerException e) {}
		}
		return false;
	}
	
	public String getBorderingGuild(int x, int z) {
		for(Guild g : GuildManager.getGuilds()) {
			try {
				for(Chunks c : g.getChunks()) {
					//Checks for the bordering chunks
					if((c.getX() + 1 == x && c.getZ() == z)
						||(c.getX() - 1 == x && c.getZ() == z)
						||(c.getX() == x && c.getZ() - 1 == z)
						||(c.getX() == x && c.getZ() + 1 == z))
						return g.toString();
				}
			}
			catch(NullPointerException e) {}
		}
		return null;
	}
	
}
