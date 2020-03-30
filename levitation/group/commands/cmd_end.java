package levitation.group.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import levitation.group.game.gameStart;

public class cmd_end implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(gameStart.gameStarted) {
			if(sender instanceof Player) {
				if(sender.hasPermission("blockmaster.end") || sender.isOp()) {
					gameStart.gameLength = 11;
				}
			}
		}
		return false;
	}

}
