
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.GameMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;



public class EVT_SignUpdate implements Listener
{
	
	
	@EventHandler
	public void onSignUpdate(SignChangeEvent e)
	{
		if (e.getLine(0).equalsIgnoreCase("[quake]") && e.getPlayer().hasPermission("quakecraft.create"))
		{
			if (e.getLine(1).equals("") || e.getLine(2).equals(""))
				return;
			e.setLine(0, "§a[Join]");
			e.setLine(2, "0/" + e.getLine(2));
			e.setLine(3, "§aLOBBY");
			GameMap map = new GameMap(e.getLine(1));
			Quakecraft.getInstance().getMaps().put(map.getName(), map);
			e.getPlayer().sendMessage("§aSign created.");
		}
	}
}
