
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;



public class EVT_Railgun implements Listener
{
	
	
	@EventHandler
	public void onGun(RailgunKillEvent e)
	{
		QPlayer qkiller = QPlayer.get(e.getKiller().getName());
		QPlayer qdead = QPlayer.get(e.getKilled().getName());
		
		Bukkit.getServer().broadcastMessage("§7" + e.getKilled().getName() + " was nailed by " + e.getKiller().getName() + "'s " + e.getWeaponType().toString().toLowerCase() + " railgun!");
		qkiller.addPoints(1);
		qkiller.addKills(1);
		qkiller.addGameKills(1);
		qdead.addDeaths(1);
	}
}
