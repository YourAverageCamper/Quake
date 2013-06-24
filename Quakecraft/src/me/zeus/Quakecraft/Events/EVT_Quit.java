
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Enumeration.LeaveReason;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;



public class EVT_Quit implements Listener
{
	
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		Bukkit.getServer().getPluginManager()
		        .callEvent(new GameLeaveEvent(e.getPlayer(), QPlayer.get(e.getPlayer().getName()), LeaveReason.QUIT, GameMap.getMap(QPlayer.get(e.getPlayer().getName()).getMap())));
	}
}
