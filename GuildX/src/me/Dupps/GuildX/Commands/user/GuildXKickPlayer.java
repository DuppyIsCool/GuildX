package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXKickPlayer implements CMD{
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
							if(higherRank(puuid,vuuid)) { //Checks if sender is a higher rank than victim & if they are in the same guild
								//Removes victim from guild
								ArrayList<String> members = new ArrayList<String>();
								members = g.getMembers();
								members.remove(vuuid);
								g.setMembers(members);
								msg.print("msg.guild.kickedplayer", sender, p.getName(), null, args[1]);
							
							}else msg.print("msg.guild.error.nothighrank", sender, null, null, null);
						}else msg.print("msg.guild.error.vnotinguild", sender, null, null, args[1]);
					}else msg.print("msg.guild.error.notinguild", sender, null, null, null);
				}else msg.print("msg.guild.error.kickself", sender, null, null, null);
			}else msg.print("error.noplayer", sender, null, null, args[1]);
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Kicks a player from the sender's guild";
	}

	@Override
	public String getUsage() {
		return "/guild kick (player)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player)) 
			return false;
		if(sender.hasPermission("guildx.kick"))
			return true;
		if(sender.isOp())
			return true;
		
		return false;
	}
	//Checks if puuid is higher rank of vuuid. Also Returns false if they aren't in the same guild.
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
