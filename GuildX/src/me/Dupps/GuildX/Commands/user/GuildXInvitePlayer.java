package me.Dupps.GuildX.Commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Guilds.Invites;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.InviteManager;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXInvitePlayer implements CMD {
	MessageManager msg = new MessageManager();
	GuildMethods gm = new GuildMethods();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		
		if(canExecute(sender)) {
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			
			//Lmao I can't even read this! - Duppy
			if(gm.isInGuild(puuid)) { //Checks if the sender is in a guild
				String guild = gm.getGuildwPlayer(puuid).toString();
				if(gm.isAdmin(puuid) || gm.isLeader(puuid)) { //Checks if they are admin or higher
					if(!gm.hasInvite(args[1], guild)) { //Checks to make sure sender doesn't already have an invite
						if(!args[1].equalsIgnoreCase(p.getName())) { //Checks to make sure they aren't inviting themselves
							if(gm.isPlayerOnline(args[1])) { //Checks if they the victim is online
								if(!gm.isInGuild(gm.getPlayerWName(args[1]).getUniqueId().toString())) { //Checks to make sure the player isn't already in a guild.
									//Checks to make sure the guild hasn't reached their max player amount.
									if(!((gm.getAllMembers(gm.getGuildwPlayer(puuid)).size() + 1) > Plugin.plugin.getConfig().getInt("default.guild.maxplayers"))) {
										//Creates an invite object.
										Invites i = new Invites(args[1], guild, Plugin.plugin.getConfig().getInt("default.invites.duration"));
										InviteManager.addInvite(i);
										msg.print("msg.guild.sentinvite", sender, guild, null, args[1]);
										CommandSender s = (CommandSender) gm.getPlayerWName(args[1]);
										msg.print("msg.guild.receivedinvite", s , null, p, guild);
									}else msg.print("msg.guild.error.maxplayersinguild", sender, null, null, null);
								}else msg.print("msg.guild.error.playeralreadyinguild", sender, null, gm.getPlayerWName(args[1]), null);
							}else { msg.print("error.playernotonline",sender,null,null,args[1]);}
						}else msg.print("msg.guild.error.inviteself", sender, guild, null, null);
					}else msg.print("msg.guild.error.playerhasinvite", sender,guild, null, args[1]);
				}else msg.print("msg.guild.error.ranktoolow", sender,guild, null, null);
			}else msg.print("msg.guild.error.notinguild", sender, null, null, null);
		}else msg.print("error.nopermission", sender, null, null, null);
		
	}

	@Override
	public String getDescription() {
		return "Invites a player to your guild";
	}

	@Override
	public String getUsage() {
		return "/guild invite (name)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player)) {
			msg.print("error.playeronly", null, null, null, null);
			return false;
		}
		else if(sender.hasPermission("guildx.invite"))
			return true;
		else if(sender.isOp())
			return true;
		return false;
	}
	

}
