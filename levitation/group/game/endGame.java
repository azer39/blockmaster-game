package levitation.group.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import levitation.group.main;
import levitation.group.utils.locationManager;

public class endGame {
	
	private static int endCounter = 21;
	public static boolean gameEnded = false;
	
	public static void end() {
		
		gameEnded = true;
		
		Location end = locationManager.getFinishSpawn();
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.teleport(end);
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main.instance, new Runnable() {
			
			@Override
			public void run() {
				
				endCounter--;
				
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.setLevel(endCounter);
				}
				
				switch (endCounter) {
				case 20: case 10: case 5: case 4: case 3: case 2: case 1:
					Bukkit.broadcastMessage(main.prefix + "§cDer Server stoppt in " + endCounter + " Sekunden!");
					break;

				case 0:
					Bukkit.shutdown();
					System.exit(1);
					break;
				default:
					break;
				}
			}
		}, 20, 20);
	}
}
