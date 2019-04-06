package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXDemotePlayer implements CMD {

	GuildMethods gm = new GuildMethods();
	MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			String vuuid = gm.getUUID(args[1]);
			
			if(vuuid != null && !vuuid.isEmpty()) { //Checks if the player they entered exists.
				if(!vuuid.equalsIgnoreCase(puuid)) { //Checks to make sure they didn't enter themselves
					if(gm.isInGuild(puuid)) { //Checks if sender is a in a guild
						
						Guild g = gm.getGuildwPlayer(puuid); //Grabs sender's guild
						
						if(gm.isInGuild(vuuid)) { //Checks if the victim is in a guild
							
							if(higherRank(puuid,vuuid)) {
								String rank = gm.getRank(vuuid);
								
								if(rank.equalsIgnoreCase("admin")) { //Demotes victim to leader from admin
									ArrayList<String> admins = new ArrayList<String>();
									ArrayList<String> members = new ArrayList<String>();
									admins = g.getAdmins();
									members = g.getMembers();
									
									admins.remove(vuuid);
									members.add(vuuid);
									
									g.setMembers(members);
									g.setAdmins(admins);
									msg.print("msg.guild.demote", sender, args[1], null, "Member");
								}
								
								else if(rank.equalsIgnoreCase("member")) {//Throws message error trying to demote a member
									msg.print("msg.guild.error.lowdemote", sender, args[1], null, null);
								}
								
								
							}else msg.print("msg.guild.error.ranktoolow", sender, null, null, null);
						}else msg.print("msg.guild.error.vnotinguild", sender, null, null, args[1]);
					}else msg.print("msg.guild.error.notinguild", sender, null, null, null);
				}else msg.print("msg.guild.error.demoteself", sender, null, null, null);
			}else msg.print("error.noplayer", sender, null, null, args[1]);
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}
		

	@Override
	public String getDescription() {
		return "Promotes a player in the sender's guild";
	}

	@Override
	public String getUsage() {
		return "/guild promote (player)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player))
			return false;
		if(sender.hasPermission("guildx.promote"))
			return true;
		if(sender.isOp())
			return true;
		return false;
	}
	
	private boolean higherRank(String puuid, String vuuid) {
		
		if(gm.getGuildwPlayer(puuid).equals(gm.getGuildwPlayer(vuuid))) {
			String prank = gm.getRank(puuid); //Player rank, not prank >:(
			String vrank = gm.getRank(vuuid);
			
			if(prank.equalsIgnoreCase("leader"))
				return true;
			else if(prank.equalsIgnoreCase("admin")&&vrank.equalsIgnoreCase("member"))
				return true;
			else
				return false;
			
		}
		
		return false;
	}

}
