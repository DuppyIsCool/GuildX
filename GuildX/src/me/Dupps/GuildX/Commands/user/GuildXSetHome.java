package me.Dupps.GuildX.Commands.user;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Chunks.ChunkMethods;
import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXSetHome implements CMD {
	private GuildMethods gm = new GuildMethods();
	private ChunkMethods cm = new ChunkMethods();
	private MessageManager msg = new MessageManager();
	
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			if(gm.isInGuild(puuid)) {
				if(gm.isLeader(puuid) || gm.isAdmin(puuid)) {
					
					Location loc = p.getLocation();
					Chunk c = p.getLocation().getChunk();
					Guild g = gm.getGuildwPlayer(puuid);
					
					if(cm.chunkIsClaimed(c.getX(), c.getZ()) && cm.getChunkOwner(c.getX(), c.getZ()).equalsIgnoreCase(g.toString())) {
						
						g.setHome(loc);
						msg.print("msg.guild.sethome", sender, loc.getBlockX() + ","+loc.getBlockX() +","+loc.getBlockZ(), null, null);
					}
					else
						msg.print("msg.guild.error.cannotsethome", sender, null, null, null);
					
				}
				else
					msg.print("msg.guild.error.ranktoolow", sender, null, null, null);
			}
			else
				msg.print("msg.guild.error.notinguild", sender, null, null, null);
		}
		else
			msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Sets guild home";
	}

	@Override
	public String getUsage() {
		return "/guild sethome";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(sender.hasPermission("guildx.sethome") && sender instanceof Player)
			return true;
		else
			return false;
	}
	

}
