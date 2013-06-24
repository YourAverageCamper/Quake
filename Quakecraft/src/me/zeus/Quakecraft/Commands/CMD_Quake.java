
package me.zeus.Quakecraft.Commands;


import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Enumeration.Upgrade;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CMD_Quake implements CommandExecutor
{
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		QPlayer qp = Quakecraft.getInstance().getPlayers().get(sender.getName());
		if (args.length < 1)
		{
			if (sender.hasPermission("quakecraft.commands.help"))
			{
				sender.sendMessage("§2Quakecraft commands:");
				
				sender.sendMessage("§e/quake points add <user> <amt> ");
				sender.sendMessage("§e/quake points set <user> <amt>");
				sender.sendMessage("§e/quake points remove <user> <amt>");
				sender.sendMessage("§e/quake points reset <user>");
				
				sender.sendMessage("§a/quake equip <name>");
				sender.sendMessage("§a/quake unequip <name>");
				
			}
		}
		else if (args.length == 1)
		{
			if (args[0].equals("leave"))
			{
				if (qp.inGame())
				{
					qp.setInGame(false);
					GameMap.getMap(qp.getMap()).getPlayers().remove(sender.getName());
					qp.setMap(null);
					qp.getPlayer().teleport(Quakecraft.getInstance().getGameHandler().lobby);
				}
				else
				{
					sender.sendMessage("§cYou are not even in a game!");
				}
			}
		}
		else if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("equip"))
			{
				if (!qp.hasUpgrades())
					return false;
				for (Upgrade upgrade : qp.getUpgrades())
				{
					if (!args[1].equalsIgnoreCase(upgrade.toString().replace("_", "")))
						continue;
					qp.setCurrentUpgrade(upgrade);
					sender.sendMessage("§aEquipped " + args[1]);
				}
			}
			else if (args[0].equalsIgnoreCase("unequip"))
			{
				if (!qp.hasUpgrades())
					return false;
				for (Upgrade upgrade : qp.getUpgrades())
				{
					if (!args[1].equalsIgnoreCase(upgrade.toString().replace("_", "")))
						continue;
					qp.setCurrentUpgrade(Upgrade.NULL);
					sender.sendMessage("§aUnequipped " + args[1]);
				}
			}
		}
		else if (args.length == 3)
		{
			if (args[0].equalsIgnoreCase("points"))
			{
				Player player = Bukkit.getPlayer(args[2]);
				if (player != null)
					switch (args[1])
					{
						case "reset":
							Quakecraft.getInstance().getPlayers().get(player.getName()).setPoints(0);
							sender.sendMessage("§cReset points for §o" + player.getName());
							break;
					}
			}
		}
		else if (args.length == 4)
			if (args[0].equalsIgnoreCase("points"))
			{
				Player player = Bukkit.getPlayer(args[2]);
				if (player != null)
					switch (args[1])
					{
						case "add":
							Quakecraft.getInstance().getPlayers().get(player.getName()).addPoints(Integer.parseInt(args[3]));
							sender.sendMessage("§9Added §e" + args[3] + " §9points to " + player.getName() + "'s account");
							break;
						case "remove":
							Quakecraft.getInstance().getPlayers().get(player.getName()).removePoints(Integer.parseInt(args[3]));
							sender.sendMessage("§9Removed §e" + args[3] + " §9points from " + player.getName() + "'s account");
							break;
						case "set":
							Quakecraft.getInstance().getPlayers().get(player.getName()).setPoints(Integer.parseInt(args[3]));
							sender.sendMessage("§9Set " + player.getName() + "'s points to §e" + args[3]);
							break;
					}
			}
		return false;
	}
}
