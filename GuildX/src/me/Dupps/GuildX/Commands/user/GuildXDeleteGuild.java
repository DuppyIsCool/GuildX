package me.Dupps.GuildX.Commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXDeleteGuild implements CMD {
	
	private GuildMethods gm = new GuildMethods();
	private MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		//Removes the sender's guild object from GuildManager's arraylist of guilds.
		if((sender instanceof Player)) {
			Player p = (Player) sender;
			
			if(canExecute(sender)) {
				String puuid = p.getUniqueId().toString();
				
				if(gm.isInGuild(puuid)) {
					if(gm.isLeader(puuid)) {
						String guild = gm.getGuildwPlayer(puuid).toString();
						GuildManager.removeGuild(gm.getGuildwPlayer(puuid));
						msg.print("msg.guild.deleted", p, guild, null, null);
					}else msg.print("msg.guild.error.ranktoolow", sender, gm.getGuildwPlayer(puuid).toString(), null, null);
				}else msg.print("msg.guild.error.notinguild", sender, null, null, null);
			}else msg.print("error.nopermission", sender, null, null, null);
		}else msg.printToConsole("error.playeronly", null, null, null, null);
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
