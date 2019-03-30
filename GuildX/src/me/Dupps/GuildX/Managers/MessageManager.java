package me.Dupps.GuildX.Managers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Main.Main;
import me.Dupps.GuildX.Main.Plugin;

public class MessageManager {
	private GuildMethods gm = new GuildMethods();
	
	public void print(String path, CommandSender sender, String pstring, Player r, String rstring) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			String message = "";
			try{message = Plugin.plugin.getConfig().getString(path);}
			catch(NullPointerException e) {Main.getConsole().sendMessage(ChatColor.RED + "Error getting path "+path);}
			
			try{message = message.replaceAll("%player%", p.getDisplayName());}
			catch(NullPointerException e) {}
			try{message = message.replaceAll("%pstring%", pstring);}
			catch(NullPointerException e) {}
			try{message = message.replaceAll("%receiver%", r.getDisplayName());}
			catch(NullPointerException e) {}
			try{message = message.replaceAll("%rstring%", rstring);}
			catch(NullPointerException e) {}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}
		else {
			String message = "";
			try{message = Plugin.plugin.getConfig().getString(path);}
			catch(NullPointerException e) {}
			try{message = message.replaceAll("%receiver%", r.getDisplayName());}
			catch(NullPointerException e) {}
			try{message = message.replaceAll("%rstring%",rstring);}
			catch(NullPointerException e) {}
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}
			
	}
	
	public void printToConsole(String path,Player p, String pguild, Player r, String rguild) {
		String message = "";
		try{message = Plugin.plugin.getConfig().getString(path);}
		catch(NullPointerException e) {Main.getConsole().sendMessage(ChatColor.RED + "Error getting path "+path);}
		
		try{message = message.replaceAll("%player%", p.getDisplayName());}
		catch(NullPointerException e) {}
		try{message = message.replaceAll("%pguild%", gm.getGuildwPlayer(p.getUniqueId().toString()).toString());}
		catch(NullPointerException e) {}
		try{message = message.replaceAll("%receiver%", r.getDisplayName());}
		catch(NullPointerException e) {}
		try{message = message.replaceAll("%rguild%", gm.getGuildwPlayer(r.getUniqueId().toString()).toString());}
		catch(NullPointerException e) {}
		
		message = ChatColor.translateAlternateColorCodes('&', message);
		Main.getConsole().sendMessage(message);
	}
	
}
