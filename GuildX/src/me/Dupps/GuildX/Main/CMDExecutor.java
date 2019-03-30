package me.Dupps.GuildX.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Dupps.GuildX.Commands.admin.*;
import me.Dupps.GuildX.Commands.user.*;
import me.Dupps.GuildX.Managers.MessageManager;

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
			
			//End of line
			msg.print("error.nocommand", sender, null, null, null);
			
			return true;
		}
		return false;
	}

}
