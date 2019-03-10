package me.Dupps.GuildX.Commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.GuildManager;

public class GuildXDeleteGuild implements CMD {
	
	private GuildMethods gm = new GuildMethods();

	@Override
	public void Execute(CommandSender sender, String[] args) {
		if((sender instanceof Player)) {
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			if(gm.isInGuild(puuid)) {
				if(gm.isLeader(puuid)) {
					GuildManager.removeGuild(gm.getGuildwPlayer(puuid));
				}
			}
		}
	}

	@Override
	public String getDescription() {
		return "Deletes the sender's guild";
	}

	@Override
	public String getUsage() {
		return "/guild delete";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(sender.hasPermission("guildx.delete"))
			return true;
		
		return false;
	}

}
