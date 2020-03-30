package levitation.group.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import levitation.group.game.gameStart;

public class cmd_start implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!gameStart.gameStarted) {
			if(sender instanceof Player) {
				if(sender.hasPermission("blockmaster.start") || sender.isOp()) {
					gameStart.counter = 6;
				}
			}
		}
		return false;
	}

}
