
package me.zeus.Quakecraft.Events;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;



public class EVT_PlayerJoin implements Listener
{
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		if (!Quakecraft.getInstance().getPlayers().containsKey(e.getPlayer().getName()))
			try
			{
				File f = new File(Quakecraft.getInstance().getDataFolder() + "/players/" + e.getPlayer().getName());
				QPlayer player = new QPlayer(e.getPlayer().getName());
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(player);
				oos.close();
				Quakecraft.getInstance().getPlayers().put(player.getName(), player);
			}
			catch (IOException ioe)
			{
				System.out.println("There was a critical error when saving data for " + e.getPlayer().getName() + "! Data either does not exist or is corrupt!");
			}
		if (e.getPlayer().getWorld().getName().equalsIgnoreCase(Quakecraft.getInstance().getGameHandler().lobby.getWorld().getName()))
		{
			Quakecraft.getInstance().getScores().addPlayer(e.getPlayer());
			if (!e.getPlayer().getInventory().contains(new ItemStack(Material.EMERALD, 1)))
			{
				e.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getShop());
				e.getPlayer().updateInventory();
			}
		}
		QPlayer.get(e.getPlayer().getName()).setInGame(false);
		QPlayer.get(e.getPlayer().getName()).setMap(null);
		
	}
}
