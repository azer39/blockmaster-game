package levitation.group.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;

import levitation.group.data;
import levitation.group.game.gameStart;
import levitation.group.game.player_obj;

public class utils {
	
	public static void clearChat(Player player, String prefix, String message)
	{
		for (int i = 0; i < 105; i++) 
		{
			Bukkit.broadcastMessage("");
		}
		String clearMsg = prefix + " " + message;
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', createPlaceholders(player, clearMsg)));
	}
	
	public static String createPlaceholders(Player player, String format)
	{
		return format.replaceAll("%displayname", player.getDisplayName()).replaceAll("%name", player.getName());
	}
	
	public static int getRanking(Player p) {
		int rank = 1;
		
		for(Player all : data.gamePlayers) {
			if(getScore(all) > getScore(p)){
				rank++;
			}
		}
				
		return rank;
	}
	
	public static Player getWinner() {
		Player winner = null;
		int winnerscore = 0;
		for(player_obj po : player_obj.playerList) {
			if(po.score > winnerscore) {
				winner = po.p;
				winnerscore = po.score;
			}
		}
		return winner;
	}
	
	public static void increaseBlockCounter(Player p) {
		for(player_obj po : player_obj.playerList) {
			if(po.p == p) {
				po.blockCounter++;
			}
		}
	}
	
	public static int getScore(Player p) {
		int a = 0;
		for(player_obj po : player_obj.playerList) {
			if(po.p == p) {
				a = po.score;
			}
		}
		return a;
	}
	
	public static int getBlockCounter(Player p) {
		int a = 0;
		for(player_obj po : player_obj.playerList) {
			if(po.p == p) {
				a = po.blockCounter;
			}
		}
		return a;
	}
	
	public static void updateScore(Player p, int modifier) {
		for(player_obj po : player_obj.playerList) {
			if(po.p == p) {
				po.score += modifier;
			}
		}
	}

	public static <E> void removePlayer(Player p) {
		List<player_obj> backUp = player_obj.playerList;
		player_obj.playerList.clear();
		
		for(player_obj a : backUp) {
			if(a.p != p) {
				player_obj.playerList.add(a);
			}
		}
	}
	
	public static void stopScheduler(int id) {
		Bukkit.getScheduler().cancelTask(id);
	}
	
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static double getValue(double a, double b) {
		double diff = a - b;
		if(diff < 0) {
			diff = diff * (-1);
		}
		return diff;
	}
	
	public static void resetPlayer(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.setFlying(false);
		p.setAllowFlight(false);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().clear();
		p.getEquipment().clear();
	}
	
	public static void addItem(org.bukkit.inventory.Inventory inv, org.bukkit.Material Material, Integer stackSize, String itemName, Integer position) {
		org.bukkit.inventory.ItemStack i = new org.bukkit.inventory.ItemStack(Material, 1);
		org.bukkit.inventory.meta.ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(itemName);
		i.setItemMeta(meta);
		
		inv.setItem(position, i);
	}
	
	public static void addEquipment(Player p) {
		if(!gameStart.gameStarted) {
			addItem(p.getInventory(), org.bukkit.Material.NETHER_STAR, 1, "§5ACHIEVEMENTS", 0);
		}else {
			
			if(data.gamePlayers.contains(p)) {
				p.getInventory().setItem(0, createUnbreakableItem(org.bukkit.Material.IRON_PICKAXE, "§cSpitzhacke"));
				p.getInventory().setItem(1, createUnbreakableItem(org.bukkit.Material.IRON_AXE, "§cAxt"));
				p.getInventory().setItem(2, createUnbreakableItem(org.bukkit.Material.IRON_SHOVEL, "§cSchaufel"));
				p.getInventory().setItem(3, createUnbreakableItem(org.bukkit.Material.SHEARS, "§cSchere"));
			}
			
		}
	}
	
	public static org.bukkit.inventory.ItemStack createUnbreakableItem(org.bukkit.Material m, String displayName){
		
		org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(m, 1);
		org.bukkit.inventory.meta.ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);
		itemMeta.setUnbreakable(true);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static PotionEffect createRandomEffect() {
		FileConfiguration cfg = config.getCfg();
		String str = cfg.getString("blockmaster.randomEffects");
		String[] split = str.split(",");
		
		ArrayList<PotionEffectType> potionList = new ArrayList<PotionEffectType>();
		
		for(String el : split) {
			PotionEffectType a = PotionEffectTypeWrapper.getByName(el);
			potionList.add(a);
		}
		int rdm = getRandomNumberInRange(0, potionList.size() - 1);
		
		PotionEffect a = new PotionEffect(potionList.get(rdm), getRandomNumberInRange(15, 45)*20, getRandomNumberInRange(1, 2)) ;
		
		return a;
	}
	
	public static void clearWorld(Player p) {
		for(Entity all : Bukkit.getWorld(p.getWorld().getName()).getEntities()) {
			if(!(all instanceof Player)) {
				all.remove();
			}
		}
	}
}
