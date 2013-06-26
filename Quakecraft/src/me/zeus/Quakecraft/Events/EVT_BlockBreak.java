
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;



public class EVT_BlockBreak implements Listener
{
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		if (QPlayer.get(e.getPlayer().getName()).isEndGame())
		{
			e.setCancelled(true);
		}
	}
}
