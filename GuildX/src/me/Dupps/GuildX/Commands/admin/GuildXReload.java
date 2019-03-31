package me.Dupps.GuildX.Commands.admin;

import org.bukkit.command.CommandSender;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.MessageManager;

public class GuildXReload implements CMD{
	private ConfigManager cfgm = new ConfigManager();
	private MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		if(canExecute(sender)) {
			cfgm.reloadGuilds();
			cfgm.reloadPlayers();
			msg.print("msg.guild.reloaded", sender, null, null,null);
		}else msg.print("error.nopermission", sender, null, null, null);
	}

	@Override
	public String getDescription() {
		return "Reloads the plugin";
	}

	@Override
	public String getUsage() {
		return "/guild reload";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(sender.isOp())
			return true;
		return false;
	}

}
