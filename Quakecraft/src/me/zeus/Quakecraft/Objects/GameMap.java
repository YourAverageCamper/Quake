
package me.zeus.Quakecraft.Objects;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Events.GameStartEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;



public class GameMap implements Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	SerializableLocation location;
	transient Map<String, QPlayer> players;
	List<SerializableLocation> spawns;
	int maxPlayers;
	int minPlayers;
	boolean inProgress = false;
	boolean vip;
	
	
	
	public GameMap(String name)
	{
		this.name = name;
		this.minPlayers = 0;
		this.maxPlayers = 0;
		this.players = new HashMap<String, QPlayer>();
		this.spawns = new ArrayList<SerializableLocation>();
		this.vip = false;
	}
	
	
	
	public String getName()
	{
		return name;
	}
	
	
	
	public Location getLocation()
	{
		return location.getLocation();
	}
	
	
	
	public int getMinimumPlayers()
	{
		return minPlayers;
	}
	
	
	
	public int getMaximumPlayers()
	{
		return maxPlayers;
	}
	
	
	
	public Map<String, QPlayer> getPlayers()
	{
		return players;
	}
	
	
	
	public List<SerializableLocation> getSpawns()
	{
		return spawns;
	}
	
	
	
	public static GameMap getMap(String mapName)
	{
		return Quakecraft.getInstance().getMaps().get(mapName);
	}
	
	
	
	public boolean inProgress()
	{
		return inProgress;
	}
	
	
	
	public boolean isVIP()
	{
		return vip;
	}
	
	
	
	/* * * * * */
	
	public void save()
	{
		try
		{
			File f = new File(Quakecraft.getInstance().getDataFolder() + "/maps/" + name);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(this);
			oos.close();
			Quakecraft.getInstance().getLogger().info("Map: " + name + " was saved.");
		}
		catch (IOException ioe)
		{
			Quakecraft.getInstance().getLogger().severe("There was a critical error when saving data for map: " + name + "! Data either does not exist or is corrupt!");
		}
	}
	
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	
	public void setMinPlayers(int min)
	{
		this.minPlayers = min;
	}
	
	
	
	public void setMaxPlayers(int max)
	{
		this.maxPlayers = max;
	}
	
	
	
	public void setLocation(Location l)
	{
		this.location = new SerializableLocation(l);
	}
	
	
	
	public void setPlayers(Map<String, QPlayer> players)
	{
		this.players = players;
	}
	
	
	
	public void setInProgress(boolean b)
	{
		this.inProgress = b;
	}
	
	
	
	public void checkPlayers()
	{
		if (!inProgress)
		{
			if (players.size() >= minPlayers)
			{
				Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(players, this, location, spawns, minPlayers, maxPlayers, vip));
				start();
				this.setInProgress(true);
			}
		}
	}
	
	
	transient int taskID;
	
	
	
	public void start()
	{
		taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Quakecraft.getInstance(), new Runnable()
		{
			
			
			int time = 60;
			int intervals[] = new int[] { 60, 45, 30, 15, 10, 5, 4, 3, 2, 1 };
			
			
			
			@Override
			public void run()
			{
				if (time < 1 || time == 0)
				{
					Bukkit.getServer().broadcastMessage("§6Game on map §3" + name + " §6is starting!");
					cancel();
					for (QPlayer player : players.values())
					{
						player.prepare();
					}
				}
				if (players.keySet().size() < minPlayers)
				{
					cancel();
					for (QPlayer player : players.values())
					{
						player.getPlayer().teleport(Quakecraft.getInstance().getGameHandler().lobby);
						player.getPlayer().sendMessage("§4Game cancelled due to lack of players. Returning to lobby...");
					}
				}
				for (int i : intervals)
				{
					if (time == i)
					{
						for (QPlayer player : players.values())
						{
							player.getPlayer().sendMessage("§6Game is starting in: §c" + i + " §6seconds!");
						}
					}
				}
				time--;
			}
		}, 0L, 20L);
	}
	
	
	
	public void cancel()
	{
		Bukkit.getServer().getScheduler().cancelTask(taskID);
	}
	
	
	
}
