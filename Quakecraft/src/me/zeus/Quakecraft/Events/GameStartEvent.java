
package me.zeus.Quakecraft.Events;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;
import me.zeus.Quakecraft.Objects.SerializableLocation;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class GameStartEvent extends Event
{
	
	
	private static final HandlerList handlers = new HandlerList();
	
	Map<String, QPlayer> players;
	GameMap map;
	Location location;
	List<SerializableLocation> spawns;
	int minPlayers;
	int maxPlayers;
	boolean vip;
	
	
	
	public GameStartEvent(Map<String, QPlayer> players, GameMap gameMap, SerializableLocation location, List<SerializableLocation> spawns, int minPlayers, int maxPlayers, boolean vip)
	{
		this.players = players;
		this.map = gameMap;
		this.location = location.getLocation();
		this.spawns = spawns;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.vip = vip;
	}
	
	
	
	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	
	
	public Set<String> getUsernames()
	{
		return players.keySet();
	}
	
	
	
	public Collection<QPlayer> getQPlayers()
	{
		return players.values();
	}
	
	
	
	public GameMap getMap()
	{
		return map;
	}
	
	
	
	public Location getLocation()
	{
		return location;
	}
	
	
	
	public List<SerializableLocation> getSpawns()
	{
		return spawns;
	}
	
	
	
	public int getMinPlayers()
	{
		return minPlayers;
	}
	
	
	
	public int getMaxPlayers()
	{
		return maxPlayers;
	}
	
	
	
	public boolean isVIP()
	{
		return vip;
	}
	
}
