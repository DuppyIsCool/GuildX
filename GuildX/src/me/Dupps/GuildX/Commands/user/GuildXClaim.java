package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Chunks.Chunks;
import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.ChunkBorderManager;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXClaim implements CMD {
	GuildMethods gm = new GuildMethods();
	ChunkMethods cm = new ChunkMethods();
	MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			int x = p.getLocation().getChunk().getX();
			int z = p.getLocation().getChunk().getZ();
			Chunk chunk = p.getLocation().getChunk();
			
			if(gm.isInGuild(puuid)) {
				
				Guild g = gm.getGuildwPlayer(puuid);
				
				if(gm.isAdmin(puuid) || gm.isLeader(puuid)) {
					if(!cm.chunkIsClaimed(x,z)) {
						if(!cm.isBordering(x, z) || cm.getBorderingGuild(x, z).equalsIgnoreCase(g.toString())) {
							Chunks c = new Chunks(x,z,g.toString());
							
							ArrayList<Chunks> chunks = new ArrayList<Chunks>();
							chunks = g.getChunks();
							
							if(chunks != null && !chunks.isEmpty()) {
								chunks.add(c);
							}
							else {
								chunks = new ArrayList<Chunks>();
								chunks.add(c);
							}
							
							g.setChunks(chunks);
							createBorder(chunk);
							msg.print("msg.guild.claimed", sender, g.toString(),null, null);
							
						}else msg.print("msg.guild.error.bordering", sender, null, null, cm.getBorderingGuild(x, z));
					}else msg.print("msg.guild.error.chunktaken", sender, null, null, cm.getChunkOwner(x, z));
				}else msg.print("msg.guild.error.ranktoolow", sender, null, null, null);
			}else msg.print("msg.guild.error.notinguild", sender, null, null, null);	
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Claims a chunk of land for the sender's guild";
	}

	@Override
	public String getUsage() {
		return "/guild claim";
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
	
	private void createBorder(Chunk c) {
		
		int x = c.getX() * 16;
		int z = c.getZ() * 16;
		org.bukkit.World w = c.getWorld();
		int time = 15;
		
		for(int i = 1; i < 16; i ++) {
			Block b = w.getHighestBlockAt(x+i,z).getLocation().add(0,-1,0).getBlock();
			b.setMetadata("SPAWNED", new FixedMetadataValue(Plugin.plugin, b.getType()));
			b.setType(Material.GLOWSTONE);
			ChunkBorderManager.addBlock(b, time);
		}
		
		for(int i = 0; i < 16; i ++) {
			Block b = w.getHighestBlockAt(x,z+i).getLocation().add(0,-1,0).getBlock();
			b.setMetadata("SPAWNED", new FixedMetadataValue(Plugin.plugin, b.getType()));
			b.setType(Material.GLOWSTONE);
			ChunkBorderManager.addBlock(b, time);
		}
		
		for(int i = 0; i < 15; i ++) {
			Block b = w.getHighestBlockAt(x-i+15,z+15).getLocation().add(0,-1,0).getBlock();
			b.setMetadata("SPAWNED", new FixedMetadataValue(Plugin.plugin, b.getType()));
			b.setType(Material.GLOWSTONE);
			ChunkBorderManager.addBlock(b, time);
		}
		
		for(int i = 1; i < 15; i ++) {
			Block b = w.getHighestBlockAt(x+15,z-i+15).getLocation().add(0,-1,0).getBlock();
			b.setMetadata("SPAWNED", new FixedMetadataValue(Plugin.plugin, b.getType()));
			b.setType(Material.GLOWSTONE);
			ChunkBorderManager.addBlock(b, time);
		}
		
		
	}

}
