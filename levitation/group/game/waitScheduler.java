package levitation.group.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import levitation.group.main;

public class waitScheduler {
	private static int c = 0;
	
	public static void wait_Scheduler() {
		c = 0;
		
		while(!(gameStart.gameStarted)) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(main.instance, new Runnable() {
				
				@Override
				public void run() {
					
					if(Bukkit.getOnlinePlayers().size() < 8) {
						c++;
						if(c%10 == 0) {
							for(Player all : Bukkit.getOnlinePlayers()) {
								all.sendMessage(main.prefix + "§eEs werden §6" + main.playerNeeded + " §ezum Start des Spiels benötigt!");
							}
						}
					}
				}
			}, 20, 20);
		}


	}

}
