package me.Dupps.GuildX.Managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Dupps.GuildX.Main.*;

public class ConfigManager {
	//Variable Declaration
	public Main plugin = Plugin.plugin;
	private ConsoleCommandSender sender = Main.getConsole();
	private static FileConfiguration guildcfg;
	private static File guildfile;
	private static FileConfiguration playerscfg;
	private static File playersfile;
	
	
	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		guildfile = new File(plugin.getDataFolder(), "guilds.yml");
		playersfile = new File(plugin.getDataFolder(), "players.yml");
		
		if (!guildfile.exists()) {
			plugin.saveResource("guilds.yml", false);
			sender.sendMessage(ChatColor.GREEN + "guilds.yml has been created");

		}

		if (!playersfile.exists()) {
			plugin.saveResource("players.yml", false);
			sender.sendMessage(ChatColor.GREEN + "players.yml has been created");

		}
		
		guildcfg = YamlConfiguration.loadConfiguration(guildfile);
		playerscfg = YamlConfiguration.loadConfiguration(playersfile);
		
		}

	public FileConfiguration getGuilds() {
		return guildcfg;
	}
	
	public FileConfiguration getPlayers() {
		return playerscfg;
	}
	

	public void saveGuilds() {
		try {
			guildcfg.save(guildfile);
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "guilds.yml has been saved");

		} catch (IOException e) {
			sender.sendMessage(ChatColor.RED + "Could not save the guilds.yml file");
		}
	}
	
	public void savePlayers() {
		try {
			playerscfg.save(playersfile);
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "players.yml has been saved");

		} catch (IOException e) {
			sender.sendMessage(ChatColor.RED + "Could not save the players.yml file");
		}
	}

	public void reloadGuilds() {
		guildcfg = YamlConfiguration.loadConfiguration(guildfile);
		sender.sendMessage(ChatColor.BLUE + "guilds.yml has been reload");

	}
	
	public void reloadPlayers() {
			playerscfg = YamlConfiguration.loadConfiguration(playersfile);
			sender.sendMessage(ChatColor.BLUE + "players.yml has been reload");
	}
	
}
