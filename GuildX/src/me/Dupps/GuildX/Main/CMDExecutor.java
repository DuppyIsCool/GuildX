package me.Dupps.GuildX.Main;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Dupps.GuildX.Commands.admin.*;
import me.Dupps.GuildX.Commands.user.*;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;
import net.md_5.bungee.api.ChatColor;

public class CMDExecutor implements CommandExecutor{
	private MessageManager msg = new MessageManager();
	private GuildXCreateGuild create = new GuildXCreateGuild();
	private GuildXInvitePlayer invite = new GuildXInvitePlayer();
	private GuildXJoinGuild join = new GuildXJoinGuild();
	private GuildXDeleteGuild delete = new GuildXDeleteGuild();
	private GuildXDeleteOtherGuild deleteother = new GuildXDeleteOtherGuild();
	private GuildXLeaveGuild leave = new GuildXLeaveGuild();
	private GuildXKickPlayer kick = new GuildXKickPlayer();
	private GuildXReload reload = new GuildXReload();
	private GuildXDemotePlayer demote = new GuildXDemotePlayer();
	private GuildXPromotePlayer promote = new GuildXPromotePlayer();
	private GuildXClaim claim = new GuildXClaim();
	private GuildXUnclaim unclaim = new GuildXUnclaim();
	private GuildXInfo info = new GuildXInfo();
	private GuildXUnclaimAll unclaimall = new GuildXUnclaimAll();
	private GuildXList list = new GuildXList();
	
	private GuildMethods gm = new GuildMethods();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("guild")) {
			
			if(args.length == 0) {
				msg.print("error.invalidargs", sender, null, null, null);				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				
				if(args.length == 2) {
					create.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("info")) {
				
				if(args.length == 2) {
					info.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("invite")) {
				
				if(args.length == 2) {
					invite.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("join")) {
				
				if(args.length == 2) {
					join.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("kick")) {
				
				if(args.length == 2) {
					kick.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("promote")) {
							
				if(args.length == 2) {
					promote.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("demote")) {
				
				if(args.length == 2) {
					demote.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("leave")) {
				
				if(args.length == 1) {
					leave.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("reload")) {
				
				if(args.length == 1) {
					reload.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("claim")) {
				
				if(args.length == 1) {
					claim.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("unclaim")) {
				
				if(args.length == 1) {
					unclaim.Execute(sender, args);
					return true;
				}
				else if(args.length == 2 && args[1].equalsIgnoreCase("all")) {
					unclaimall.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("delete")) {
				if(args.length == 1) {
					delete.Execute(sender,args);
					return true;
				}
				if(args.length == 2) {
					deleteother.Execute(sender, args);
					return true;
				}
				msg.print("error.invalidargs", sender, null, null, null);
				return true;
			}
			
			//Help Command
			
			if(args[0].equalsIgnoreCase("help")) {
				if(sender.hasPermission("guildx.help")) {
					ArrayList<String> commandusage = new ArrayList<String>();
					ArrayList<String> commanddesc = new ArrayList<String>();
					//Adding usage text, and description text
					if(sender.hasPermission("guildx.create") || sender.isOp()) {
						commandusage.add(create.getUsage());
						commanddesc.add(create.getDescription());
					}
					if(sender.hasPermission("guildx.delete") || sender.isOp()) {
						commandusage.add(delete.getUsage());
						commanddesc.add(delete.getDescription());
					}
					if(sender.hasPermission("guildx.deleteother") || sender.isOp()) {
						commandusage.add(deleteother.getUsage());
						commanddesc.add(deleteother.getDescription());
					}
					if(sender.hasPermission("guildx.claim") || sender.isOp()) {
						commandusage.add(claim.getUsage());
						commanddesc.add(claim.getDescription());
					}
					if(sender.hasPermission("guildx.unclaim") || sender.isOp()) {
						commandusage.add(unclaim.getUsage());
						commanddesc.add(unclaim.getDescription());
					}
					if(sender.hasPermission("guildx.unclaimall") || sender.isOp()) {
						commandusage.add(unclaimall.getUsage());
						commanddesc.add(unclaimall.getDescription());
					}
					if(sender.hasPermission("guildx.invite") || sender.isOp()) {
						commandusage.add(invite.getUsage());
						commanddesc.add(invite.getDescription());
					}
					if(sender.hasPermission("guildx.kick") || sender.isOp()) {
						commandusage.add(kick.getUsage());
						commanddesc.add(kick.getDescription());
					}
					if(sender.hasPermission("guildx.join") || sender.isOp()) {
						commandusage.add(join.getUsage());
						commanddesc.add(join.getDescription());
					}
					if(sender.hasPermission("guildx.leave") || sender.isOp()) {
						commandusage.add(leave.getUsage());
						commanddesc.add(leave.getDescription());
					}
					if(sender.hasPermission("guildx.promote") || sender.isOp()) {
						commandusage.add(promote.getUsage());
						commanddesc.add(promote.getDescription());
					}
					if(sender.hasPermission("guildx.info") || sender.isOp()) {
						commandusage.add(info.getUsage());
						commanddesc.add(info.getDescription());
					}
					if(sender.hasPermission("guildx.reload") || sender.isOp()) {
						commandusage.add(reload.getUsage());
						commanddesc.add(reload.getDescription());
					}
					
					int pages = commandusage.size()/8;
					//Check if an extra page is needed
					if(commandusage.size()%8 != 0)
						pages++;
					
					if(args.length == 1) {
						sender.sendMessage(ChatColor.GOLD + "===" + ChatColor.GREEN + "GuildX Commands" + ChatColor.GOLD + "===");
						for(int i = 0; i < 8; i ++) {
							sender.sendMessage(commandusage.get(i) + " - "+ChatColor.RED + commanddesc.get(i));
						}
						return true;
					}
					else if(args.length == 2) {
						if(isNumeric(args[1])) {
							int num = Integer.parseInt(args[1]);
							if(!(num > pages)) {
								sender.sendMessage(ChatColor.GOLD + "===" + ChatColor.GREEN + "GuildX Commands" + ChatColor.GOLD + "===");
								for(int i = num * 8 + 1; i < num * 8 + 8; i++) {
									if(i < commandusage.size()) {
										sender.sendMessage(commandusage.get(i) + " - "+ChatColor.RED + commanddesc.get(i));
									}
								}
								return true;
							}
							else {
								msg.print("msg.guild.error.invalidpage", sender, null, null, null);
								return true;
							}
						}
						else {
							msg.print("msg.guild.error.invalidpage", sender, null, null, null);
							return true;
						}
						
					}
					
					
				}
				else {
				msg.print("error.nopermission", sender, null, null, null);
				return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("list")) {
				if(args.length <= 2) {
					list.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			//Displays info for a guild if it's entered
			if(gm.guildExists(args[0]) && args.length == 1) {
				String[] inputs = {"info",args[0]};
				info.Execute(sender, inputs);
				return true;
			}
			
			
			//End of line
			msg.print("error.nocommand", sender, null, null, null);
			
			return true;
		}
		return false;
	}
	private static boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}

}
