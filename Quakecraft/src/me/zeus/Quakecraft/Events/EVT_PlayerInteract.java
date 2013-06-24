
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Enumeration.RailgunType;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;



public class EVT_PlayerInteract implements Listener
{
	
	
	@EventHandler
	public void onClick(final PlayerInteractEvent e)
	{
		if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getType().equals(Material.EMERALD))
			Quakecraft.getInstance().getMenu().open(e.getPlayer());
		if (e.getClickedBlock() != null)
		{
			if (!e.getClickedBlock().getType().equals(Material.WALL_SIGN))
				return;
			
			Sign sign = (Sign) e.getClickedBlock().getState();
			if (isSign(sign))
			{
				
				GameMap gamemap = Quakecraft.getInstance().getMaps().get(sign.getLine(1));
				
				if (gamemap == null || gamemap.getLocation() == null)
					return;
				
				if (gamemap.inProgress())
				{
					e.getPlayer().sendMessage("§cGame is in progress.");
					return;
				}
				
				if (gamemap.isVIP())
				{
					if (!e.getPlayer().hasPermission("quakecraft.upgrades.vip"))
					{
						e.getPlayer().sendMessage("§cSorry, that game is VIP only.");
						return;
					}
				}
				
				gamemap.getPlayers().put(e.getPlayer().getName(), QPlayer.get(e.getPlayer().getName()));
				QPlayer.get(e.getPlayer().getName()).setMap(gamemap.getName());
				e.getPlayer().teleport(gamemap.getLocation());
				QPlayer.get(e.getPlayer().getName()).setInGame(true);
				gamemap.checkPlayers();
			}
		}
		else if (QPlayer.get(e.getPlayer().getName()).getMap() != null)
		{
			if (!GameMap.getMap(QPlayer.get(e.getPlayer().getName()).getMap()).inProgress())
				return;
			
			if (e.getPlayer().getItemInHand() == null)
				return;
			
			Bukkit.getServer().getPluginManager().callEvent(new RailgunShootEvent(e.getPlayer(), RailgunType.grabType(e.getPlayer().getItemInHand())));
		}
	}
	
	
	
	private boolean isSign(Sign sign)
	{
		if (sign.getLine(0) != null && sign.getLine(0).equalsIgnoreCase("§a[Join]") || sign.getLine(0).equalsIgnoreCase("§5[VIP]"))
			return true;
		return false;
	}
	
	
	
}
