package me.Dupps.GuildX.Commands.user;


import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitTask;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Main.Plugin;
import me.Dupps.GuildX.Managers.MessageManager;
import me.Dupps.GuildX.Tasks.HomeTP;

public class GuildXHome implements CMD {
	public static HashMap<Player,BukkitTask> homemap = new HashMap<Player,BukkitTask>();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		MessageManager msg = new MessageManager();
		GuildMethods gm = new GuildMethods();
		
		if(canExecute(sender)) {
			Player p = (Player) sender;
			String puuid = p.getUniqueId().toString();
			int tptime = 15;
			
			if(gm.isInGuild(puuid)) {
				
				Guild g = gm.getGuildwPlayer(puuid);
				
				if(g.getHome() != null) {
					if(!homemap.containsKey(p)) {
						Location home = g.getHome();
						p.setMetadata("homeloc", new FixedMetadataValue(Plugin.plugin, home));
						BukkitTask task = new HomeTP(tptime,p).runTaskTimer(Plugin.plugin, 0,20);
						homemap.put(p, task);
						msg.print("msg.guild.tpstart", sender, null, null, null);
					}
					else
						msg.print("msg.guild.error.alreadyteleporting", sender, null, null, null);
				}
				else
					msg.print("msg.guild.error.nohome", sender, null, null, null);
			}
			else
				msg.print("msg.guild.error.notinguild", sender, null, null, null);
		}
		else
			msg.print("error.nopermission", sender, null, null, null);
			
		
	}

	@Override
	public String getDescription() {
		return "Teleports to your guild's home";
	}

	@Override
	public String getUsage() {
		return "/guild home";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(sender.hasPermission("guildx.home") && sender instanceof Player)
			return true;
		else
			return false;
	}

}
