package me.Dupps.GuildX.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Dupps.GuildX.Commands.user.*;

public class CMDExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("guild")) {
			if(args.length == 0)
				return false;
			
			if(args.length == 2 && args[0].equalsIgnoreCase("create")) {
				GuildXCreateGuild command = new GuildXCreateGuild();
				command.Execute(sender,args);
				return true;
			}
			
			if(args.length == 1 && args[0].equalsIgnoreCase("delete")) {
				GuildXDeleteGuild command = new GuildXDeleteGuild();
				command.Execute(sender,args);
				return true;
			}
			
			return true;
		}
		return false;
	}

}
