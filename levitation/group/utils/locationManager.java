package levitation.group.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class locationManager {
	static FileConfiguration cfg = config.getCfg();
	static int c = cfg.getInt("blockmaster.gameCounter");	
	static int id = 0;


	
	public static Location getLobbySpawn() {	
		Location ret = new Location(Bukkit.getWorld(cfg.getString("blockmaster.lobby.world")), cfg.getDouble("blockmaster.lobby.x"), cfg.getDouble("blockmaster.lobby.y"), cfg.getDouble("blockmaster.lobby.z"), (float) cfg.getDouble("blockmaster.lobby.yaw"), (float) cfg.getDouble("blockmaster.lobby.pitch"));
		return ret;
	}
	
	public static Location getGameSpawn() {	
		
		if(c > 1) {
			id = utils.getRandomNumberInRange(1, c);
		}else {
			id = 1;
		}
		
		Location ret = new Location(Bukkit.getWorld(cfg.getString("blockmaster.game." + id + ".world")), cfg.getDouble("blockmaster.game." + id + ".x"), cfg.getDouble("blockmaster.game." + id + ".y"), cfg.getDouble("blockmaster.game." + id + ".z"), (float) cfg.getDouble("blockmaster.game." + id + ".yaw"), (float) cfg.getDouble("blockmaster.game." + id + ".pitch"));
		return ret;
	}
	
	public static Location getSpectatorSpawn() {	
		Location ret = new Location(Bukkit.getWorld(cfg.getString("blockmaster.spectator.world")), cfg.getDouble("blockmaster.spectator.x"), cfg.getDouble("blockmaster.spectator.y"), cfg.getDouble("blockmaster.spectator.z"), (float) cfg.getDouble("blockmaster.spectator.yaw"), (float) cfg.getDouble("blockmaster.spectator.pitch"));
		return ret;
	}
	
	public static Location getFinishSpawn() {	
		Location ret = new Location(Bukkit.getWorld(cfg.getString("blockmaster.end.world")), cfg.getDouble("blockmaster.end.x"), cfg.getDouble("blockmaster.end.y"), cfg.getDouble("blockmaster.end.z"), (float) cfg.getDouble("blockmaster.end.yaw"), (float) cfg.getDouble("blockmaster.end.pitch"));
		return ret;
	}

}
