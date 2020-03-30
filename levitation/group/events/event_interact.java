package levitation.group.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import levitation.group.game.gameStart;
import levitation.group.utils.utils;

public class event_interact implements Listener{
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getMaterial() == Material.NETHER_STAR) {
			
			/*
			 * 
			 * Implement MYSQL STATS SYSTEM FOR ACHIEVEMENTS
			 * 
			 * */
			
			if(gameStart.gameStarted) {
				Inventory achievementInv = Bukkit.createInventory(null, 9, "§5ACHIEVEMENTS");
				utils.addItem(achievementInv, Material.GRAY_DYE, 1, "§7Blockmaster", 0);
				utils.addItem(achievementInv, Material.GRAY_DYE, 1, "§7Winmaster", 1);
				
				p.openInventory(achievementInv);
			}
		}
	}

}
