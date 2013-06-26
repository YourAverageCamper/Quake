
package me.zeus.Quakecraft.Events;


import java.util.HashMap;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
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
		final GameMap gm = GameMap.getMap(qp.getMap());
		qp.getPlayer().getInventory().clear();
		
		if (gm != null)
		{
			gm.getPlayers().remove(p.getName());
			qp.setMap(null);
			if (!qp.getPlayer().getInventory().contains(Quakecraft.getInstance().getGameHandler().getShop()))
			{
				qp.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getShop());
			}
			
			if (gm.getPlayers().keySet().size() < gm.getMinimumPlayers())
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Quakecraft.getInstance(), new Runnable()
				{
					
					
					@Override
					public void run()
					{
						for (QPlayer qp : gm.getPlayers().values())
						{
							qp.getPlayer().teleport(Quakecraft.getInstance().getGameHandler().lobby);
							Quakecraft.getInstance().getScores().updateBalance(qp.getPlayer().getName());
						}
						gm.setInProgress(false);
						gm.setPlayers(new HashMap<String, QPlayer>());
					}
				}, 20L * 1);
			}
		}
		
	}
}
