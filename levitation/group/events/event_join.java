package levitation.group.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import levitation.group.data;
import levitation.group.main;
import levitation.group.game.gameStart;
import levitation.group.utils.config;
import levitation.group.utils.locationManager;
import levitation.group.utils.utils;

public class event_join implements Listener{
	
	private static boolean cleared = false;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {		
		Player p = e.getPlayer();
		
		utils.resetPlayer(p);
		
		FileConfiguration cfg = config.getCfg();
				
		String[] poses = {"lobby.world", "lobby.x", "lobby.y", "lobby.z", "lobby.yaw", "lobby.pitch",
						"game.1.world", "game.1.x", "game.1.y", "game.1.z", "game.1.yaw", "game.1.pitch",
						"spectator.world", "spectator.x", "spectator.y", "spectator.z", "spectator.yaw", "spectator.pitch",
						"end.world"
						};
		int counter = 0;
		for(String c : poses) {
			if(cfg.contains("blockmaster." + c)) {
				counter++;
			}
		}
		if(counter == 19) {
			
			if(gameStart.gameStarted) {
				data.spectators.add(p);
				
				for(Player inGame : data.gamePlayers) {
					inGame.hidePlayer(p);
				}
				p.setFlySpeed((float) 0.5);
				p.setAllowFlight(true);
				p.setFlying(true);
				
				if(!cleared) {
					utils.clearWorld(p);
					System.out.println("BlockMaster | Cleared all Entities!");
					cleared = true;
				}
				
				p.teleport(locationManager.getSpectatorSpawn());
			}else {
				p.teleport(locationManager.getLobbySpawn());
				if(!data.gamePlayers.contains(p)) {
					data.gamePlayers.add(p);
				}
			}
			
		}else {
			Bukkit.broadcastMessage(main.prefix + "§4COMPLETE THE SETUP!");
			return;
		}
		

		if((Bukkit.getOnlinePlayers().size() > main.playerNeeded - 1) && !(gameStart.countdownStarted)) {
			gameStart.startGame();
		}
		e.setJoinMessage(main.prefix + "§a" + p.getName()  + "§7 hat das Spiel betreten!");
		utils.addEquipment(p);
	}
}
