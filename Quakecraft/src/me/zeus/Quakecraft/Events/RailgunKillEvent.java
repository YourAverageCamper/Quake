
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Enumeration.RailgunType;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class RailgunKillEvent extends Event
{
	
	
	Player killer;
	Player dead;
	RailgunType railgunType;
	
	private static final HandlerList handlers = new HandlerList();
	
	
	
	public RailgunKillEvent(Player killer, Player killed, RailgunType type)
	{
		this.killer = killer;
		this.dead = killed;
		this.railgunType = type;
	}
	
	
	
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	
	
	public Player getKiller()
	{
		return killer;
	}
	
	
	
	public Player getKilled()
	{
		return dead;
	}
	
	
	
	public RailgunType getWeaponType()
	{
		return railgunType;
	}
	
}
