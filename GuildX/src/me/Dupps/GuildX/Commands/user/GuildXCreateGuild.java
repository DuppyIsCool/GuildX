package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.MessageManager;
import me.Dupps.GuildX.Managers.TeamManager;

public class GuildXCreateGuild implements CMD {
	private MessageManager msg = new MessageManager();
	private GuildMethods gm = new GuildMethods();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			if(canExecute(sender)) {
				Player p = (Player) sender;
				if(!gm.isInGuild(p.getUniqueId().toString())) {
					if(args[1].length() <= Plugin.plugin.getConfig().getInt("default.guild.namelength")) {
						if(!gm.guildExists(args[1])) {
							if(!bannedName(args[1])) {
								//By this point the inputed name should not be taken, should not be banned, and is short enough.
								
								//Variable declaration
								String puuid = p.getUniqueId().toString();
								Guild guild = new Guild();
								ArrayList<String> members = new ArrayList<String>();
								ArrayList<String> admins = new ArrayList<String>();
								//Putting variables into guild object and adding guild to GuildManager's list
								guild.setGuildname(args[1]);
								guild.setLeader(puuid);
								guild.setLives(Plugin.plugin.getConfig().getInt("default.guild.lives"));
								guild.setAdmins(admins);
								guild.setMembers(members);
								//Add Guild to GuildManager
								GuildManager.addGuild(guild); 
								TeamManager.addPlayer(p, guild.toString());
								msg.print("msg.guild.created", p, guild.toString(), null, null);
							}else msg.print("msg.guild.error.bannedguildname", p,args[1], null, null);
						}else msg.print("msg.guild.error.nametaken", sender, null, null, args[1]);
					}else msg.print("msg.guild.error.nametoolong", sender, null, null, null);
				}else msg.print("msg.guild.error.alreadyinguild", sender, gm.getGuildwPlayer(p.getUniqueId().toString()).toString(), null, null);
			}else msg.print("error.nopermission", sender, null, null, null);
		}else msg.printToConsole("error.playeronly", null, null, null, null);
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
	
	private boolean bannedName(String name) {
		if(!isAlphanumeric(name))
			return true;
		if(GuildManager.bannedNames != null && !GuildManager.bannedNames.isEmpty()) {
			for(String e : GuildManager.bannedNames) {
				if(name.equalsIgnoreCase(e))
					return true;
					
			}
		}
		return false;
	}
	
	public boolean isAlphanumeric(String str)
	{
	    char[] charArray = str.toCharArray();
	    for(char c:charArray)
	    {
	        if (!Character.isLetterOrDigit(c))
	            return false;
	    }
	    return true;
	}
	

}
