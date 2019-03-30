package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXLeaveGuild implements CMD{
	private GuildMethods gm = new GuildMethods();
	private MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			if(gm.isInGuild(puuid)) {
				
				Guild g = gm.getGuildwPlayer(puuid);
				ArrayList<String> members = g.getMembers();
				ArrayList<String> admins = g.getMembers();
				
				 if(gm.isMember(puuid)) {
					 members.remove(puuid);
					 g.setMembers(members);
					 msg.print("msg.guild.leftguild", sender, g.getGuildname(), null, null);
				 }
				 
				 if(gm.isAdmin(puuid)) {
					 admins.remove(puuid);
					 g.setAdmins(admins);
					 msg.print("msg.guild.leftguild", sender, g.getGuildname(), null, null);
				 }
				
				 if(gm.isLeader(puuid)) {
					 msg.print("msg.guild.error.leaveasleader", sender, null, null, null);
				 }
				
				
				
			}else msg.print("msg.guild.error.notinguild", sender, null, null, null);
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Leaves a guild";
	}

	@Override
	public String getUsage() {
		return "/guild leave";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player))
			return false;
		else if(sender.hasPermission("guildx.leave")) 
			return true;
		else if(sender.isOp())
			return true;
		return false;
	}
	

}
