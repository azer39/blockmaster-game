package levitation.group.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class config {
	
	private static FileConfiguration cfg;
	private static File file;
	
	public static void initConfig() {
		file = new File("plugins/Block Master/", "config.yml");
		cfg = YamlConfiguration.loadConfiguration(file);
		cfg.options().copyDefaults(true);
		cfg.options().header("BlockMaster Config");
		cfg.addDefault("config.enabled", true);
		cfg.addDefault("blockmaster.minPlayer", 8);
		cfg.addDefault("blockmaster.gamemode", "normal");
		cfg.addDefault("blockmaster.gameCounter", 0);
		cfg.addDefault("blockmaster.deathScore", 10);
		cfg.addDefault("blockmaster.randomEffects", "BLINDNESS,FAST_DIGGING,JUMP");
		cfg.addDefault("blockmaster.blocklist", "DIRT,IRON_ORE,LEAVES");
		saveConfig();
	}
	
	public static void saveConfig() {
		try {
			cfg.save(file);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static FileConfiguration getCfg() {
		return cfg;
	}

}
