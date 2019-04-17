package me.Dupps.GuildX.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Timers.borderTimer;
import me.Dupps.GuildX.Timers.lifeTimer;
import me.Dupps.GuildX.Timers.raidTimer;

public class Main extends JavaPlugin{
	//Variables
	private static ConsoleCommandSender console;
	private ConfigManager cfgm;
	private GuildManager guildmanager = new GuildManager();
	public void onEnable() {
		Plugin.plugin = this;
		//Setup timers
		@SuppressWarnings("unused")
		borderTimer timer = new borderTimer();
		@SuppressWarnings("unused")
		raidTimer rtimer = new raidTimer();
		@SuppressWarnings("unused")
		lifeTimer ltimer = new lifeTimer();
		//Declare console sender
		console = Bukkit.getServer().getConsoleSender();
		//Sets up events and command execution
		Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
		this.getCommand("guild").setExecutor(new CMDExecutor());
		
		//Sets up guilds and guild configuration
		getConfig().options().copyDefaults(true);
		loadConfigManager();
		loadConfig(); 
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "GuildX has been enabled");
		guildmanager.setupGuilds();
		saveConfig();
	}
	
	public void onDisable() {
		guildmanager.saveGuilds();
		cfgm.savePlayers();
		cfgm.saveGuilds();
	}
	public static ConsoleCommandSender getConsole() {
		return console;
	}
	public void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
	}
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

}
