package levitation.group.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import levitation.group.data;
import levitation.group.main;
import levitation.group.game.endGame;
import levitation.group.game.gameStart;
import levitation.group.utils.ScoreBoardManager;
import levitation.group.utils.locationManager;
import levitation.group.utils.utils;

public class eventList implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!(gameStart.gameStarted)) {
			e.setCancelled(true);
		}else {
			if(data.spectators.contains(e.getPlayer())) {
				e.setCancelled(true);
			}else {
				e.setDropItems(false);
				
				if(main.bblocks.contains(e.getBlock().getType())) {
					e.getPlayer().getInventory().addItem(new ItemStack(e.getBlock().getType(), 1));
					int score = main.bblocks.indexOf(e.getBlock().getType()) + 1;
					if(score != 1) {
						score *= 5;
					}
					
					utils.updateScore(e.getPlayer(), score);
					utils.increaseBlockCounter(e.getPlayer());
					for(Player all : data.gamePlayers) {
						ScoreBoardManager.setScoreboard(all);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		if(!gameStart.gameStarted) {
			e.setDeathMessage(null);
		}else {
			if(data.gamePlayers.contains(p)) {
				e.setDeathMessage(main.prefix + "§9" + p.getName() + " §7starb!");
				utils.updateScore(p, -main.deathScore);
			}else {
				e.setDeathMessage(null);
			}
		}
		e.setDroppedExp(0);
		e.setKeepInventory(true);
		e.setKeepLevel(true);
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if(!gameStart.gameStarted) {
			e.setRespawnLocation(locationManager.getLobbySpawn());
		}else {
			if(data.gamePlayers.contains(p)) {
				e.setRespawnLocation(locationManager.getGameSpawn());
			}else {
				e.setRespawnLocation(locationManager.getSpectatorSpawn());
			}
		}
		p.sendMessage(main.prefix + "§cDir wurden §4" + main.deathScore + " §cPunkte abgezogen!");
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {		
		if(!(gameStart.gameStarted) || endGame.gameEnded) {
			e.setCancelled(true);
		}else {
			Player p = (Player) e.getEntity();
			if(data.spectators.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
		
		if(e.getFoodLevel() != 20) {
			e.setFoodLevel(20);
		}
	}
	
	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e) {
		if(!(e.getEntity() instanceof Player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(e.getAction() != InventoryAction.NOTHING && e.getAction() != InventoryAction.UNKNOWN) {			
			e.setCancelled(true);
		}
	}
}
