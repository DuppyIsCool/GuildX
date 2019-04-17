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

public class GuildXUnclaimAll implements CMD{
	GuildMethods gm = new GuildMethods();
	ChunkMethods cm = new ChunkMethods();
	MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			if(gm.isInGuild(puuid)) {
				
				Guild g = gm.getGuildwPlayer(puuid);
				
				if(gm.isLeader(puuid)) {
					
					//Removes all guild chunks
					g.setChunks(new ArrayList<Chunks>());
					msg.print("msg.guild.unclaimall", sender, null, null, null);
				}else msg.print("msg.guild.error.ranktoolow", sender, null, null, null);
			}else msg.print("msg.guild.error.notinguild", sender, null, null, null);	
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Unclaims all chunks of land for the guild";
	}

	@Override
	public String getUsage() {
		return "/guild unclaim all";
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
