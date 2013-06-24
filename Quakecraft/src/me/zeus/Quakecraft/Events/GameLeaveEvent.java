
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Enumeration.LeaveReason;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class GameLeaveEvent extends Event
{
	
	
	private static final HandlerList handlers = new HandlerList();
	Player p;
	QPlayer qp;
	LeaveReason reason;
	GameMap map;
	
	
	
	public GameLeaveEvent(Player p, QPlayer qp, LeaveReason reason, GameMap map)
	{
		this.p = p;
		this.qp = qp;
		this.reason = reason;
		this.map = map;
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
	
	
	
	public Player getPlayer()
	{
		return p;
	}
	
	
	
	public QPlayer getQPlayer()
	{
		return qp;
	}
	
	
	
	public LeaveReason getReason()
	{
		return reason;
	}
	
	
	
	public GameMap getMap()
	{
		return map;
	}
}
