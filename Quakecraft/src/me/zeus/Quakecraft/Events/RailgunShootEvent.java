
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Enumeration.RailgunType;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class RailgunShootEvent extends Event
{
	
	
	private static final HandlerList handlers = new HandlerList();
	Player shooter;
	QPlayer qshooter;
	RailgunType gun;
	
	
	
	public RailgunShootEvent(Player shooter, RailgunType gun)
	{
		this.shooter = shooter;
		this.gun = gun;
		this.qshooter = QPlayer.get(shooter.getName());
	}
	
	
	
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	
	
	public Player getShooter()
	{
		return shooter;
	}
	
	
	
	public QPlayer getQShooter()
	{
		return qshooter;
	}
	
	
	
	public RailgunType getGunType()
	{
		return gun;
	}
}
