package me.Dupps.GuildX.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Dupps.GuildX.Commands.user.*;
import me.Dupps.GuildX.Managers.MessageManager;

public class CMDExecutor implements CommandExecutor{
	private MessageManager msg = new MessageManager();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("guild")) {
			
			if(args.length == 0) {
				msg.print("error.invalidargs", sender, null, null, null);				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				GuildXCreateGuild command = new GuildXCreateGuild();
				
				if(args.length == 2) {
					command.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("invite")) {
				GuildXInvitePlayer command = new GuildXInvitePlayer();
				
				if(args.length == 2) {
					command.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("join")) {
				GuildXJoinGuild command = new GuildXJoinGuild();
				
				if(args.length == 2) {
					command.Execute(sender,args);
					return true;
				}
				else {
					msg.print("error.invalidargs", sender, null, null, null);
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("delete")) {
				GuildXDeleteGuild command = new GuildXDeleteGuild();
				GuildXDeleteOtherGuild command1 = new GuildXDeleteOtherGuild();
				if(args.length == 1) {
					command.Execute(sender,args);
					return true;
				}
				if(args.length == 2) {
					command1.Execute(sender, args);
					return true;
				}
				msg.print("error.invalidargs", sender, null, null, null);
				return true;
			}
			
			return true;
		}
		return false;
	}

}
