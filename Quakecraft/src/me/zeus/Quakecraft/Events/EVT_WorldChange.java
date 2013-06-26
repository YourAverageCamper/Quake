
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Quakecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;



public class EVT_WorldChange implements Listener
{
	
	
	@EventHandler
	public void onWorld(PlayerTeleportEvent e)
	{
		if (e.getFrom().getWorld().getName().equals(Quakecraft.getInstance().getGameHandler().lobby.getWorld().getName()))
			Quakecraft.getInstance().getScores().removePlayer(e.getPlayer());
		
		if (e.getTo().getWorld().getName().equals(Quakecraft.getInstance().getGameHandler().lobby.getWorld().getName()))
		{
			Quakecraft.getInstance().getScores().addPlayer(e.getPlayer());
		}
	}
}
