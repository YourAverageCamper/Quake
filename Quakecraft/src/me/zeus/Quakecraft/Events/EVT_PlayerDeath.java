
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;



public class EVT_PlayerDeath implements Listener
{
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player dead = e.getEntity();
		if (dead.getKiller() != null && dead.getKiller() instanceof Player)
		{
			Player killer = dead.getKiller();
			QPlayer qdead = QPlayer.get(dead.getName());
			QPlayer qkiller = QPlayer.get(killer.getName());
		}
	}
}
