package levitation.group.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import levitation.group.main;
import levitation.group.utils.config;

public class cmd_setspawn implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("blockmaster.setspawn") || p.isOp()) {
				if(args.length == 1 || args.length == 2) {
					
					Location loc = p.getLocation();
					
					FileConfiguration cfg = config.getCfg();
					switch (args[0].toLowerCase()) {
					case "lobby":
						cfg.set("blockmaster.lobby.world", p.getWorld().getName());
						cfg.set("blockmaster.lobby.x", loc.getX());
						cfg.set("blockmaster.lobby.y", loc.getY());
						cfg.set("blockmaster.lobby.z", loc.getZ());
						cfg.set("blockmaster.lobby.yaw", loc.getYaw());
						cfg.set("blockmaster.lobby.pitch", loc.getPitch());
						
						config.saveConfig();
						p.sendMessage(main.prefix + "Du hast den Lobby-Spawn gesetzt!");
						
						break;

					case "game":
						
						if(args.length == 2) {
							int id = Integer.valueOf(args[1]);
							
							cfg.set("blockmaster.game." + id + ".world", p.getWorld().getName());
							cfg.set("blockmaster.game." + id + ".x", loc.getX());
							cfg.set("blockmaster.game." + id + ".y", loc.getY());
							cfg.set("blockmaster.game." + id + ".z", loc.getZ());
							cfg.set("blockmaster.game." + id + ".yaw", loc.getYaw());
							cfg.set("blockmaster.game." + id + ".pitch", loc.getPitch());
							
							int gameCounter = cfg.getInt("blockmaster.gameCounter");
							gameCounter++;
							cfg.set("blockmaster.gameCounter", gameCounter);
							
							config.saveConfig();
							p.sendMessage(main.prefix + "Du hast den Game-Spawn für " + id + " gesetzt!");
						}else {
							p.sendMessage(main.help);
						}
						

						
						break;
					
					case "spectator":
						
						cfg.set("blockmaster.spectator.world", p.getWorld().getName());
						cfg.set("blockmaster.spectator.x", loc.getX());
						cfg.set("blockmaster.spectator.y", loc.getY());
						cfg.set("blockmaster.spectator.z", loc.getZ());
						cfg.set("blockmaster.spectator.yaw", loc.getYaw());
						cfg.set("blockmaster.spectator.pitch", loc.getPitch());
						
						config.saveConfig();
						p.sendMessage(main.prefix + "Du hast den Spectator-Spawn gesetzt!");
						
						break;
					
					case "end":
						
						cfg.set("blockmaster.end.world", p.getWorld().getName());
						cfg.set("blockmaster.end.x", loc.getX());
						cfg.set("blockmaster.end.y", loc.getY());
						cfg.set("blockmaster.end.z", loc.getZ());
						cfg.set("blockmaster.end.yaw", loc.getYaw());
						cfg.set("blockmaster.end.pitch", loc.getPitch());
						
						config.saveConfig();
						p.sendMessage(main.prefix + "Du hast den End-Spawn gesetzt!");
						
						break;
												
					default:
						p.sendMessage(main.help);		
						break;
					}
					
				}else {
					p.sendMessage(main.help);					
				}
			}else {
				p.sendMessage(main.noPerms);
			}			
		}else {
			sender.sendMessage("Please run this Command as a Player!");			
		}
		
		return false;
	}

}
