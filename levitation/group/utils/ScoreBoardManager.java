package levitation.group.utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;

import levitation.group.game.gameStart;


public class ScoreBoardManager {
	
    public static void setScoreboard(org.bukkit.entity.Player p){
        org.bukkit.scoreboard.Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        @SuppressWarnings("deprecation")
		org.bukkit.scoreboard.Objective ob = sb.registerNewObjective("lobby", "system");
        ob.setDisplaySlot(DisplaySlot.SIDEBAR);
        ob.setDisplayName("§7» §cPanoria.net§7 «");
        if(gameStart.gameStarted) {
            ob.getScore("§6").setScore(12);
            ob.getScore("§aBlock Master").setScore(11);
            ob.getScore("§7» §6" + p.getName() ).setScore(10);
            ob.getScore("§7").setScore(9);
            ob.getScore("§7Platz:").setScore(8);
            ob.getScore("§7» §e" + utils.getRanking(p) ).setScore(7);
            ob.getScore("§0").setScore(6);
            ob.getScore("§7Score:").setScore(5);
            ob.getScore("§7» §e" + utils.getScore(p)).setScore(4);
            ob.getScore("§2").setScore(3);
            ob.getScore("§7Blocks").setScore(2);
            ob.getScore("§7» §b" + utils.getBlockCounter(p)).setScore(1);
            ob.getScore("§4  ").setScore(0);
        }else {
            ob.getScore("§6").setScore(9);
            ob.getScore("§aBlock Master").setScore(8);
            ob.getScore("§7» §3" + p.getName()).setScore(7);
            ob.getScore("§7").setScore(6);
            ob.getScore("§7Score:").setScore(5);
            ob.getScore("§7» §e" + utils.getScore(p) ).setScore(4);
            ob.getScore("§2").setScore(3);
            ob.getScore("§7Blocks").setScore(2);
            ob.getScore("§7» §b" + utils.getBlockCounter(p)).setScore(1);
            ob.getScore("§4  ").setScore(0);
        }        
        p.setScoreboard(sb);
    }
}
