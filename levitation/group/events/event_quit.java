package levitation.group.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import levitation.group.data;
import levitation.group.main;
import levitation.group.game.endGame;
import levitation.group.game.gameStart;
import levitation.group.utils.utils;

public class event_quit implements Listener{
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(data.spectators.contains(p)) {
			e.setQuitMessage(null);
			rP(p, data.spectators);
		}else {
			e.setQuitMessage(main.prefix + "§c" + p.getName() + " §ehat das Spiel verlassen!");
			rP(p, data.gamePlayers);
			utils.removePlayer(p);
		}
		if(!gameStart.gameStarted) {
			
			if(data.gamePlayers.contains(p)) {
				data.gamePlayers.remove(p);
			}
			
			if(Bukkit.getOnlinePlayers().size() < main.playerNeeded) {
				utils.stopScheduler(gameStart.startG);
				gameStart.countdownStarted = false;
			}
		}else {
			if(data.gamePlayers.size() == 1) {
				utils.stopScheduler(gameStart.runG);
				endGame.end();
			}
		}

	}
	
	private void rP(Player p, List<Player> pp) {
		List<Player> backUp = pp;
		pp.clear();
		
		for(Player a : backUp) {
			if(a != p) {
				pp.add(a);
			}
		}
	}

}
