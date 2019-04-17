package me.Dupps.GuildX.Commands.user;

import org.bukkit.command.CommandSender;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.MessageManager;
import net.md_5.bungee.api.ChatColor;

public class GuildXList implements CMD{
	private GuildMethods gm = new GuildMethods();
	private MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		
		int pages = GuildManager.getGuilds().size()/8;
		Guild g;
		//Check if an extra page is needed
		if(GuildManager.getGuilds().size()%8 != 0)
			pages++;
		
		if(args.length == 1) {
			if(!GuildManager.getGuilds().isEmpty()) {
				sender.sendMessage(ChatColor.GOLD + "===" + ChatColor.GREEN + "Guilds" + ChatColor.GOLD + "===");
				for(int i = 0; i < 8; i ++) {
					if(i < GuildManager.getGuilds().size()) {
						g = GuildManager.getGuilds().get(i);
						if(g.getChunks() != null && !g.getChunks().isEmpty())
						sender.sendMessage(ChatColor.GREEN + g.toString()+ 
								ChatColor.GOLD + " "+ gm.getAllMembers(g).size()+ "/" + g.getChunks().size() + "/"+ g.getLives());
						else
							sender.sendMessage(ChatColor.GREEN + g.toString()+ 
									ChatColor.GOLD +" "+ gm.getAllMembers(g).size()+ "/" + 0 + "/"+ g.getLives());
					}
				}
			}
			else
				msg.print("msg.guild.error.noguilds", sender, null, null, null);
		}
		else if(args.length == 2) {
			if(!GuildManager.getGuilds().isEmpty()) {
				if(isNumeric(args[1])) {
					int num = Integer.parseInt(args[1]);
					if(!(num > pages) && num >= 0) {
						sender.sendMessage(ChatColor.GOLD + "===" + ChatColor.GREEN + "Guilds" + ChatColor.GOLD + "===");
						for(int i = num * 8 ; i < num * 8 + 8; i++) {
							if(i < GuildManager.getGuilds().size()) {
								g = GuildManager.getGuilds().get(i);
								if(g.getChunks() != null && !g.getChunks().isEmpty())
								sender.sendMessage(ChatColor.GREEN + g.toString()+ 
										ChatColor.GOLD + " "+gm.getAllMembers(g).size()+ "/" + g.getChunks().size() + "/"+ g.getLives());
								else
									sender.sendMessage(ChatColor.GREEN + g.toString()+ 
											ChatColor.GOLD + " "+gm.getAllMembers(g).size()+ "/" + 0 + "/"+ g.getLives());
							}
						}
						
					}
					else {
						msg.print("msg.guild.error.invalidpage", sender, null, null, null);
					}
				}
				else {
					msg.print("msg.guild.error.invalidpage", sender, null, null, null);
				}
			}
			else
				msg.print("msg.guild.error.noguilds", sender, null, null, null);
			
		}
		
	}

	@Override
	public String getDescription() {
		return "Returns a list of guilds";
	}

	@Override
	public String getUsage() {
		return "/guild list (page)";
	}

	@Override
	public boolean canExecute(CommandSender sender) {
		if(sender.hasPermission("guildx.list"))
			return true;
		if(sender.isOp())
			return true;
		return false;
	}
	
	private static boolean isNumeric(String str) {
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

}
