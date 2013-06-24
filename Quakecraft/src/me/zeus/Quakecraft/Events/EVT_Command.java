
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;



public class EVT_Command implements Listener
{
	
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e)
	{
		if (QPlayer.get(e.getPlayer().getName()).inGame())
		{
			if (!e.getMessage().equals("/quake leave"))
			{
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cCommands disabled! Please use /quake leave!");
			}
		}
	}
}
