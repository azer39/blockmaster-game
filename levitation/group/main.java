package levitation.group;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import levitation.group.commands.cmd_end;
import levitation.group.commands.cmd_setspawn;
import levitation.group.commands.cmd_start;
import levitation.group.events.eventList;
import levitation.group.events.event_chat;
import levitation.group.events.event_interact;
import levitation.group.events.event_join;
import levitation.group.events.event_move;
import levitation.group.events.event_quit;
import levitation.group.utils.config;

public class main extends JavaPlugin {
	
	public static final String prefix = "§aBlockMaster §8| §7";
	public static final String noPerms = prefix + "§cDazu hast du keine Rechte!";
	public static final String help = prefix + "§eHelp: /setspawn <Lobby/Game/Spectator> <Game ID>";
	public static main instance;
	public static int playerNeeded = 4;
	public static int deathScore = 5;
	public static List<Material> bblocks = new ArrayList<Material>();
	
	public final void onEnable() {
		instance = this;
		
		registerCommands();
		registerListener();
		config.initConfig();
		
		System.out.println("-=================-");
		System.out.println("   Block  Master   ");
		System.out.println("    made by azer   ");
		System.out.println("     levitation    ");
		System.out.println("-=================-");
		
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule announceAdvancements false");
		
		readCfg();
	}
	
	private final void registerCommands() {
		getCommand("setspawn").setExecutor(new cmd_setspawn());
		getCommand("start").setExecutor(new cmd_start());
		getCommand("end").setExecutor(new cmd_end());
	}
	
	private final void registerListener() {
		getServer().getPluginManager().registerEvents(new event_join(), this);
		getServer().getPluginManager().registerEvents(new event_quit(), this);
		getServer().getPluginManager().registerEvents(new eventList(), this);
		getServer().getPluginManager().registerEvents(new event_chat(), this);
		getServer().getPluginManager().registerEvents(new event_move(), this);
		getServer().getPluginManager().registerEvents(new event_interact(), this);
	}
	
	private static final void readCfg() {
		FileConfiguration cfg = config.getCfg();
		deathScore = cfg.getInt("blockmaster.deathScore");
		playerNeeded = cfg.getInt("blockmaster.minPlayer");
		String bbs = cfg.getString("blockmaster.blocklist");
		for(String a : bbs.split(",")) {
			a.replace(" ", "");
			Material b = Material.getMaterial(a);
			bblocks.add(b);
		}
	}

}
