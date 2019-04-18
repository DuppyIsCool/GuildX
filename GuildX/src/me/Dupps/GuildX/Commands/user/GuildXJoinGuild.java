package me.Dupps.GuildX.Commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.InviteManager;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXJoinGuild implements CMD {
	MessageManager msg = new MessageManager();
	GuildMethods gm = new GuildMethods();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			//Checks if they have an invite and then delete that invite and add them to the guild.
			if(!gm.isInGuild(puuid)) {
				if(gm.hasInvite(p.getName(), args[1])) {
					if(gm.guildExists(args[1])) {
						InviteManager.deleteInvite(p.getName(), args[1]);
						gm.addPlayerToGuild(puuid, args[1]);
						msg.print("msg.guild.joined", sender, gm.getGuildwPlayer(puuid).toString(), null, null);
					}else msg.print("msg.guild.error.doesnotexist", sender, null, null, args[1]);
				}else msg.print("msg.guild.error.noinvite", sender, null, null, args[1]);
			}else msg.print("msg.guild.error.alreadyinguild", sender, gm.getGuildwPlayer(puuid).toString(), null, null);
		}else msg.print("error.nopermission", sender, null, null, null);
	}

	@Override
	public String getDescription() {
		return "Joins a guild";
	}

	@Override
	public String getUsage() {
		return "/g join (name)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player)) {
			msg.print("error.playeronly", null, null, null, null);
			return false;
		}
		else if (sender.hasPermission("guildx.join"))
			return true;
		else if (sender.isOp())
			return true;
		return false;
	}
	

}
