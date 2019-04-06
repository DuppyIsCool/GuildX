package me.Dupps.GuildX.Commands.user;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.Dupps.GuildX.Commands.CMD;
import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Guilds.GuildMethods;
import me.Dupps.GuildX.Managers.MessageManager;
import net.md_5.bungee.api.ChatColor;

public class GuildXInfo implements CMD {
	private GuildMethods gm = new GuildMethods();
	private MessageManager msg = new MessageManager();
	@Override
	public void Execute(CommandSender sender, String[] args) {
		//Args Layout: args[0] = "info", args[1] = (guildName)
		
		if(canExecute(sender)) {
			
			//Grabs player
			Player p = (Player) sender;
			
			if(gm.guildExists(args[1])) {
				//Begin message to send
				
				Guild g = gm.getGuildwName(args[1]); //Grab guild object
				Inventory inv;
				ItemStack item;
				SkullMeta smeta;
				Player player;
				ArrayList<String> waitList = new  ArrayList<String>();
				
				//Create inventory and count
				inv = Bukkit.createInventory(null, 36, "Guild Display");
				int count = 9; //Starts on a new line (First one is reserved for guild icon
				
				//Creates the Beacon Data Block
				item = new ItemStack(Material.BEACON,1);
				ItemMeta metas = item.getItemMeta();
				metas.setDisplayName(ChatColor.GOLD + g.toString());
				ArrayList<String> lores = new ArrayList<String>();
				//Setting lore
				lores.add(ChatColor.GREEN + "Name: "+ChatColor.YELLOW + g.toString());
				lores.add(ChatColor.GREEN + "Leader: "+ChatColor.YELLOW + gm.getName(g.getLeader()));
				lores.add(ChatColor.GREEN + "Size: "+ChatColor.YELLOW + gm.getAllMembers(g).size() + " players");
				lores.add(ChatColor.GREEN + "Lives: "+ChatColor.YELLOW + g.getLives());
				if(g.getChunks() != null && !g.getChunks().isEmpty())
					lores.add(ChatColor.GREEN + "Claims: "+ChatColor.YELLOW + g.getChunks().size() + "/??");
				else
					lores.add(ChatColor.GREEN + "Claims: "+ChatColor.YELLOW + "0" + "/??");
				metas.setLore(lores);
				item.setItemMeta(metas);
				//Adds beacon to inventory
				inv.setItem(4,item);
				
				//Adding online players
				for(String e : gm.getAllMembers(g)) {
					if(!gm.isOffline(e)) {
						player = gm.getPlayer(e);
						item = new ItemStack(Material.PLAYER_HEAD, 1);
						
						//Getting the meta, modifying it, and setting it to the item stack
						smeta = (SkullMeta) item.getItemMeta();
				        smeta.setOwningPlayer(player);
				        smeta.setDisplayName(ChatColor.GOLD + player.getDisplayName());
				        
				        ArrayList<String> lore = new ArrayList<String>();//List<String> wouldn't compile for some reason
				        
				        lore.add(ChatColor.YELLOW +gm.getRank(e));
				        smeta.setLore(lore);
				        item.setItemMeta(smeta);
				        
				        //Creating the item in the inventory at slot # 'count'
				        inv.setItem(count, item);
						count++;
						
					}
					else
						waitList.add(e);
				}
				
				//Adding offline players
				for(String e : waitList) {
					
					item = new ItemStack(Material.SKELETON_SKULL,1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.RED + gm.getName(e));
					 
			        ArrayList<String> lore = new ArrayList<String>();//List<String> wouldn't compile for some reason
			        
			        lore.add(ChatColor.YELLOW +gm.getRank(e));
					meta.setLore(lore);
					item.setItemMeta(meta);
					
					inv.setItem(count, item);
					count++;
					
				}
				//Opens the inventory for the player
				p.openInventory(inv);
			}else msg.print("msg.guild.error.doesnotexist", sender, null, null, args[1]);
		}else msg.print("error.nopermission", sender, null, null, null);

	}

	@Override
	public String getDescription() {
		return "Grabs info about a guild";
	}

	@Override
	public String getUsage() {
		return "/guild info (name)";
	}

	@Override
	//Checks to make sure the player is not a cmd block or console and has guildx.info or is operator
	public boolean canExecute(CommandSender sender) {
		if(!(sender instanceof Player))
			return false;
		if(sender.hasPermission("guildx.info"))
			return true;
		if(sender.isOp())
			return true;
		return false;
	}
	
}
