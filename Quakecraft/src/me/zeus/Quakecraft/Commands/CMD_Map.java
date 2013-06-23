
package me.zeus.Quakecraft.Commands;


import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.SerializableLocation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CMD_Map implements CommandExecutor
{
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args)
	{
		if (!sender.hasPermission("quakecraft.map"))
			return false;
		if (args.length < 2)
		{
			StringBuilder sb = new StringBuilder();
			for (String name : Quakecraft.getInstance().getMaps().keySet())
			{
				sb.append(name).append(" ");
			}
			sender.sendMessage("§d§lList of maps: §r" + sb.toString());
		}
		else if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("create"))
			{
				GameMap map = new GameMap(args[1]);
				map.setLocation(((Player) sender).getLocation());
				map.setMinPlayers(6);
				map.setMaxPlayers(16);
				map.save();
				sender.sendMessage("§aMap created: " + args[1]);
			}
			else if (args[0].equalsIgnoreCase("teleport"))
			{
				if (Quakecraft.getInstance().getMaps().get(args[1]) != null)
				{
					((Player) sender).teleport(Quakecraft.getInstance().getMaps().get(args[1]).getLocation());
					sender.sendMessage("§aTeleported to " + args[1] + ".");
				}
			}
			else if (args[0].equalsIgnoreCase("setspawn"))
			{
				for (String mapName : Quakecraft.getInstance().getMaps().keySet())
				{
					String name2 = mapName.replace(" ", "_");
					if (!args[1].equalsIgnoreCase(name2))
						continue;
					
					GameMap map = GameMap.getMap(mapName);
					map.getSpawns().add(new SerializableLocation(((Player) sender).getLocation()));
					sender.sendMessage("§eCreated a spawn here. Total number of spawns: §a" + map.getSpawns().size());
				}
			}
			else if (args[0].equalsIgnoreCase("setlocation"))
			{
				for (String mapName : Quakecraft.getInstance().getMaps().keySet())
				{
					String name2 = mapName.replace(" ", "_");
					if (!args[1].equalsIgnoreCase(name2))
						continue;
					
					GameMap map = GameMap.getMap(mapName);
					map.setLocation(((Player) sender).getLocation());
					sender.sendMessage("§eSet location here");
				}
			}
		}
		else if (args.length == 3)
		{
			if (args[0].equalsIgnoreCase("setmin"))
			{
				for (String mapName : Quakecraft.getInstance().getMaps().keySet())
				{
					String name2 = mapName.replace(" ", "_");
					if (!args[1].equalsIgnoreCase(name2))
						continue;
					GameMap map = GameMap.getMap(mapName);
					map.setMinPlayers(Integer.parseInt(args[2]));
					sender.sendMessage("§5Updated min players to " + args[2]);
				}
			}
			else if (args[0].equalsIgnoreCase("setmax"))
			{
				for (String mapName : Quakecraft.getInstance().getMaps().keySet())
				{
					String name2 = mapName.replace(" ", "_");
					if (!args[1].equalsIgnoreCase(name2))
						continue;
					GameMap map = GameMap.getMap(mapName);
					map.setMaxPlayers(Integer.parseInt(args[2]));
					sender.sendMessage("§5Updated max players to " + args[2]);
				}
			}
		}
		
		
		return false;
	}
}
