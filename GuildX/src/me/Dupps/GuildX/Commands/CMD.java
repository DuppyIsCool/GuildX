package me.Dupps.GuildX.Commands;

import org.bukkit.command.CommandSender;

public interface CMD { 
	public void Execute(CommandSender sender, String[] args);
	public String getDescription();
	public String getUsage();
	public boolean canExecute(CommandSender sender);
}
