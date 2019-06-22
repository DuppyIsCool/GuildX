package me.Dupps.GuildX.Main;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Commands.admin.GuildXReload;
import me.Dupps.GuildX.Commands.user.GuildXClaim;
import me.Dupps.GuildX.Commands.user.GuildXCreateGuild;
import me.Dupps.GuildX.Commands.user.GuildXDeleteGuild;
import me.Dupps.GuildX.Commands.user.GuildXDeleteOtherGuild;
import me.Dupps.GuildX.Commands.user.GuildXDemotePlayer;
import me.Dupps.GuildX.Commands.user.GuildXHome;
import me.Dupps.GuildX.Commands.user.GuildXInfo;
import me.Dupps.GuildX.Commands.user.GuildXInvitePlayer;
import me.Dupps.GuildX.Commands.user.GuildXJoinGuild;
import me.Dupps.GuildX.Commands.user.GuildXKickPlayer;
import me.Dupps.GuildX.Commands.user.GuildXLeaveGuild;
import me.Dupps.GuildX.Commands.user.GuildXList;
import me.Dupps.GuildX.Commands.user.GuildXPromotePlayer;
import me.Dupps.GuildX.Commands.user.GuildXSetHome;
import me.Dupps.GuildX.Commands.user.GuildXUnclaim;
import me.Dupps.GuildX.Commands.user.GuildXUnclaimAll;
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
	private GuildXHome home = new GuildXHome();
	private GuildXSetHome sethome = new GuildXSetHome();
	
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
				else if(args.length == 1) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						if(gm.isInGuild(p.getUniqueId().toString())) {
							String[] inputs = {"info",gm.getGuildwPlayer(p.getUniqueId().toString()).toString()};
							info.Execute(sender,inputs);
							return true;
						}
					}
					
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
					if(args.length == 1) {
						sendHelpMessage(sender, 1);
						return true;
					}
					else if(args.length == 2) {
						if(isNumeric(args[1])) {
							
							int num = Integer.parseInt(args[1]);
							sendHelpMessage(sender,num +1);
							return true;
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
			if(args[0].equalsIgnoreCase("home")) {
				
				if(args.length == 1) {
					home.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("sethome")) {
				
				if(args.length == 1) {
					sethome.Execute(sender, args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			//Displays info for a guild if it's entered
			if(gm.guildExists(args[0])) {
				if(args.length == 1) {
					String[] inputs = {"info",args[0]};
					info.Execute(sender, inputs);
					return true;
				}
				 
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
	private void sendHelpMessage(CommandSender sender, int page) {
		ArrayList<String> commandusage = new ArrayList<String>();
		ArrayList<String> commanddesc = new ArrayList<String>();
		int pagecount;
		int amountperpage = 5;
		
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
		if(sender.hasPermission("guildx.home") || sender.isOp()) {
			commandusage.add(home.getUsage());
			commanddesc.add(home.getDescription());
		}
		if(sender.hasPermission("guildx.sethome") || sender.isOp()) {
			commandusage.add(sethome.getUsage());
			commanddesc.add(sethome.getDescription());
		}
		
		pagecount = commandusage.size()/amountperpage;
		
		//Check if an extra page is needed
		if(commandusage.size()%amountperpage != 0)
			pagecount++;
		
		if(page <= pagecount && page >= 0) { //Checks if the page is a valid page
			ArrayList<String> pages = new ArrayList<String>();
			//Add List of commands (as a singular string) into the array list 'pages'
			for(int j = 0; j < pagecount; j++) {
				String list = ChatColor.GOLD + "===" + ChatColor.GREEN + "GuildX Commands" + ChatColor.GOLD + "===";
				for(int i = 0; i < amountperpage && (i + (j*amountperpage)) < commandusage.size(); i++) {
					list += ( "\n" +ChatColor.AQUA + "" + commandusage.get(i + (j*amountperpage)) + ChatColor.GRAY + " "+commanddesc.get(i + (j*amountperpage)));
				}
				pages.add(list);
			}
			//Sends the sender the message
			sender.sendMessage(pages.get(page-1));
		}
		else
			msg.print("msg.guild.error.invalidpage", sender, null, null, null);
	}

}
