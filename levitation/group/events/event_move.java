package levitation.group.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import levitation.group.main;
import levitation.group.game.gameStart;
import levitation.group.utils.locationManager;
import levitation.group.utils.utils;

public class event_move implements Listener{
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(gameStart.gameStarted) {
			Location gameSpawn = locationManager.getGameSpawn();
			double xDiff = utils.getValue(e.getTo().getX(), gameSpawn.getX());
			double zDiff = utils.getValue(e.getTo().getZ(), gameSpawn.getZ());
			
			if(xDiff > 250 || zDiff > 250) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(main.prefix + "§cDu du bist zu weit vom Spawn entfernt!");
			}
		}
	}
}
