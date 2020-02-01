package me.Dupps.GuildX.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.Dupps.GuildX.Guilds.Guild;
import me.Dupps.GuildX.Managers.ConfigManager;
import me.Dupps.GuildX.Managers.GuildManager;
import me.Dupps.GuildX.Managers.TeamManager;

public class Main extends JavaPlugin{
	//Variables
	private static ConsoleCommandSender console;
	private ConfigManager cfgm;
	private GuildManager guildmanager = new GuildManager();
	public void onEnable() {
		Plugin.plugin = this;
		//Setup life timer
		lifeTimer();
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
		TeamManager.removeEmptyTeams();
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
	
	private void lifeTimer() {
		long lifedelay = (Plugin.plugin.getConfig().getInt("default.lives.interval")) * 20;
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for(Guild g : GuildManager.getGuilds()) {
            		int incr = Plugin.plugin.getConfig().getInt("default.lives.increment");
            		if((g.getLives() + incr )<= Plugin.plugin.getConfig().getInt("default.lives.max") && !g.isRaidable()) {
            			g.setLives(g.getLives() +incr);
            		}
            		
            	}
            }
        }, 0L, lifedelay);
	}
}
