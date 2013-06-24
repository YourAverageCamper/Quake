
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;



public class EVT_GameLeave implements Listener
{
	
	
	@EventHandler
	public void onLeave(GameLeaveEvent e)
	{
		Player p = e.getPlayer();
		QPlayer qp = e.getQPlayer();
		GameMap gm = GameMap.getMap(qp.getMap());
		
		if (gm != null)
		{
			gm.getPlayers().remove(p.getName());
			qp.setMap(null);
		}
	}
}
