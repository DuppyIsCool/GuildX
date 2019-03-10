package me.Dupps.GuildX.Commands.user;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Managers.GuildManager;

public class GuildXCreateGuild implements CMD {

	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			Guild guild = new Guild();
			List<String> members = null;
			List<String> admins = null;
			guild.setGuildname(args[1]);
			guild.setLeader(puuid);
			guild.setLives(10);
			guild.setAdmins(admins);
			guild.setMembers(members);
			GuildManager.addGuild(guild);
		}
	}

	@Override
	public String getDescription() {
		return "Creates a guild given a guild name";
	}

	@Override
	public String getUsage() {
		return "/guild create (name)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player))
			return false;
		else if(sender.hasPermission("guildx.create"))
			return true;
		else if(sender.isOp())
			return true;
		
		return false;
	}
	

}
