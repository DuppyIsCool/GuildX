package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Chunks.Chunks;
import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXUnclaim implements CMD {
	GuildMethods gm = new GuildMethods();
	ChunkMethods cm = new ChunkMethods();
	MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			
			//Variable Declaration
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			int x = p.getLocation().getChunk().getX();
			int z = p.getLocation().getChunk().getZ();
			
			if(gm.isInGuild(puuid)) {
				
				Guild g = gm.getGuildwPlayer(puuid);
				
				if(gm.isAdmin(puuid) || gm.isLeader(puuid)) {
					if(cm.chunkIsClaimed(x,z)) {
						if(cm.getChunkOwner(x, z).equalsIgnoreCase(g.toString())) {
							//Gets chunk arraylist, and removes the current chunk from the list.
							ArrayList<Chunks> chunks = new ArrayList<Chunks>();
							chunks = g.getChunks();
							g.setChunks(cm.removeChunk(chunks, x, z));
							msg.print("msg.guild.unclaimed", sender, g.toString(),null, null);
							
						}else msg.print("msg.guild.error.chunktaken", sender, null, null, cm.getChunkOwner(x, z));
					}else msg.print("msg.guild.error.chunknotclaimed", sender, null, null, cm.getChunkOwner(x, z));
				}else msg.print("msg.guild.error.ranktoolow", sender, null, null, null);
			}else msg.print("msg.guild.error.notinguild", sender, null, null, null);	
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Unclaims a chunk of land for the guild";
	}

	@Override
	public String getUsage() {
		return "/guild unclaim";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player))
			return false;
		if(sender.hasPermission("guildx.claim"))
			return true;
		if(sender.isOp())
			return true;
		return false;
	}
}
