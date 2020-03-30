package levitation.group.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import levitation.group.data;
import levitation.group.main;
import levitation.group.utils.ScoreBoardManager;
import levitation.group.utils.locationManager;
import levitation.group.utils.utils;

public class gameStart {

	public static boolean countdownStarted = false;
	public static boolean gameStarted = false;
	
	public static int counter = 21;
	public static int gameLength = 300;
	public static int runG = 0;
	
	public static void runGame() {
		
		gameStarted = true;
		
		runG = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.instance, new Runnable() {
			
			@Override
			public void run() {
				gameLength--;
				
				for(Player a : Bukkit.getOnlinePlayers()) {
					a.setLevel(gameLength);
				}
				
				switch (gameLength) {
				case 240:
					Bukkit.broadcastMessage(main.prefix + "§c4 Minuten verbleibend!");
					break;
				
				case 180: case 60:
					Bukkit.broadcastMessage(main.prefix + "§c" + gameLength/60 + ((gameLength / 60 ) == 1 ? " Minute verbleibend!" : " Minuten verbleibend!"));	
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.addPotionEffect(utils.createRandomEffect());
					}
					break;
					
				case 120:
					Bukkit.broadcastMessage(main.prefix + "§c2 Minuten verbleibend!");					
					break;
					
				case 30: case 10: case 5: case 3: case 2: case 1: 
					Bukkit.broadcastMessage(main.prefix + "§c" + gameLength + " Sekunden verbleibend!");		
					break;
					
				case 0:
					for (int x = 0; x < 150; x++){
					    Bukkit.broadcastMessage("");
					}
					Bukkit.broadcastMessage("§8|-------------------+====+-------------------|");
					
					Bukkit.broadcastMessage(main.prefix + "§cDas Spiel ist vorbei!");
					Player win = utils.getWinner();
					
					Bukkit.broadcastMessage(main.prefix + "§9" + win.getName() + " §7hat das Spiel mit einem Score von §e" + utils.getScore(win));
					for(Player all : data.gamePlayers) {
						all.sendMessage(main.prefix + "Du bist Platz §6" + utils.getRanking(all) + " §7geworden!");
					}
					
					
					
					endGame.end();
					break;

				default:
					break;
				}
				
			}
		}, 20, 20);
	}
	public static int startG = 0;
	
	public static void startGame() {
		
		countdownStarted = true;
		
		startG = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.instance, new Runnable() {
			
			@Override
			public void run() {
				
				counter--;
				for(Player a : Bukkit.getOnlinePlayers()) {
					a.setLevel(counter);
				}
				
				switch (counter) {
				
				case 5: case 4: case 3: case 2: case 1: case 10: case 20:
					Bukkit.broadcastMessage(main.prefix + "§cDas Spiel startet in " + counter + " Sekunden!");
					break;

				case 0:
					
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.teleport(locationManager.getGameSpawn());
						if(!data.gamePlayers.contains(all)) {
							data.gamePlayers.add(all);									
						}
						player_obj po = new player_obj(all, 0, 0);
						player_obj.playerList.add(po);
						gameStart.gameStarted = true;
						utils.addEquipment(all);
						ScoreBoardManager.setScoreboard(all);
					}
										
					runGame();
					
					break;
				default:
					break;
				}
				
			}
		}, 20, 20);
	}
}
