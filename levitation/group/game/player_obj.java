package levitation.group.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class player_obj {
	
	public Player p;
	public int score;
	public int blockCounter;
	
	public static List<player_obj> playerList = new ArrayList<player_obj>();
	
	public player_obj(Player _p, int _score, int _blockCounter) {
		this.p = _p;
		this.score = _score;
		this.blockCounter = _blockCounter;
	}
}
