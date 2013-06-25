
package me.zeus.Quakecraft.Events;


import java.util.HashMap;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;



public class EVT_Railgun implements Listener
{
	
	
	@EventHandler
	public void onGun(RailgunKillEvent e)
	{
		QPlayer qkiller = QPlayer.get(e.getKiller().getName());
		QPlayer qdead = QPlayer.get(e.getKilled().getName());
		final GameMap map = GameMap.getMap(qkiller.getMap());
		
		qkiller.addPoints(1);
		qkiller.addKills(1);
		qkiller.addGameKills(1);
		qdead.addDeaths(1);
		
		for (QPlayer qp : map.getPlayers().values())
			qp.getPlayer().sendMessage("§7" + e.getKilled().getName() + " was nailed by " + e.getKiller().getName() + "'s " + e.getWeaponType().toString().toLowerCase() + " railgun!");
		
		if (qkiller.getGameKills() >= 30)
		{
			qkiller.addPoints(10);
			
			for (QPlayer qp : map.getPlayers().values())
			{
				qp.doEndGame();
				qp.getPlayer().getWorld().spawnEntity(qp.getPlayer().getLocation(), EntityType.FIREWORK);
				qp.getPlayer().sendMessage("§b§l===============================================");
				qp.getPlayer().sendMessage("§6§l" + qkiller.getName() + " won the game!");
				qp.getPlayer().sendMessage("§b§l===============================================");
			}
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Quakecraft.getInstance(), new Runnable()
			{
				
				
				@Override
				public void run()
				{
					for (QPlayer qp : map.getPlayers().values())
					{
						qp.getPlayer().teleport(Quakecraft.getInstance().getGameHandler().lobby);
						Quakecraft.getInstance().getScores().addPlayer(qp.getPlayer());
					}
					map.setInProgress(false);
					map.setPlayers(new HashMap<String, QPlayer>());
					
					for (Player p : Bukkit.getServer().getOnlinePlayers())
						if (!QPlayer.get(p.getName()).inGame())
							Quakecraft.getInstance().getScores().toggleHideBoard(p);
				}
			}, 20L * 5);
			
		}
		
		
		
	}
}
