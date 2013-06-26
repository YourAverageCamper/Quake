
package me.zeus.Quakecraft.Events;


import java.util.HashMap;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
		
		if (qkiller.getPlayer().hasPermission("quakecraft.misc.doublepoints"))
		{
			qkiller.addPoints(2);
		}
		else
		{
			qkiller.addPoints(1);
		}
		
		qkiller.addKills(1);
		qkiller.addGameKills(1);
		qkiller.getPlayer().playSound(qkiller.getPlayer().getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
		qdead.getPlayer().playSound(qkiller.getPlayer().getLocation(), Sound.GHAST_SCREAM, 1.0F, 2.0F);
		qdead.addDeaths(1);
		
		for (QPlayer qp : map.getPlayers().values())
		{
			qp.getPlayer().sendMessage("§7" + e.getKilled().getName() + " was nailed by " + e.getKiller().getName() + "'s " + e.getWeaponType().toString().toLowerCase() + " railgun!");
		}
		
		if (qkiller.getGameKills() >= 30)
		{
			if (qkiller.getPlayer().hasPermission("quakecraft.misc.doublepoints"))
			{
				qkiller.addPoints(10);
			}
			else
			{
				qkiller.addPoints(20);
			}
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
							Quakecraft.getInstance().getScores().updateBalance(p.getName());
				}
			}, 20L * 5);
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "reload");
		}
	}
}
