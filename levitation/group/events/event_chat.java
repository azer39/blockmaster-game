package levitation.group.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import levitation.group.data;

@SuppressWarnings({ "deprecation" })
public class event_chat implements Listener{
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		
		if(data.gamePlayers.contains(p)) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.sendMessage("§a" + p.getName() + " §8» §7" + e.getMessage());
			}
		}else if(data.spectators.contains(p)){
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!(data.gamePlayers.contains(p))) {
					all.sendMessage("§8SPEC | §7" + p.getName() + " §8» §7" + e.getMessage());
				}
			}
		}else {
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.sendMessage("§7" + p.getName() + " §8» §7" + e.getMessage());				
			}
		}
	}

}
