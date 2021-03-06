package me.Dupps.GuildX.Commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXDeleteOtherGuild implements CMD{
	private MessageManager msg = new MessageManager();
	private GuildMethods gm = new GuildMethods();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		//Removes the inputed guild object from GuildManager's arraylist of guilds.
		if(canExecute(sender)) { 
			if(gm.guildExists(args[1])) {
				String temp = gm.getGuildwName(args[1]).toString();
				GuildManager.removeGuild(gm.getGuildwName(args[1]));
				msg.print("msg.guild.deleted",sender , temp, null, temp);
			}
			else msg.print("msg.guild.error.doesnotexist", sender, null, null, args[1]);
		}
		else msg.print("error.nopermission", sender, null, null, args[1]);
		
	}

	@Override
	public String getDescription() {
		return "Deletes another player's guild.";
	}

	@Override
	public String getUsage() {
		return "/guild delete (name)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player)) 
			return true;
		else {
			Player p = (Player) sender;
			if(p.isOp())
				return true;
			else if(p.hasPermission("guildx.delete.other"))
				return true;
			
		}
		return false;
	}

}
