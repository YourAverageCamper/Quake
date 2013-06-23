
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.TempData;
import me.zeus.Quakecraft.Objects.Cooldown;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;



public class EVT_PlayerInteract implements Listener
{
	
	
	@EventHandler
	public void onClick(final PlayerInteractEvent e)
	{
		if (e.getClickedBlock() != null)
		{
			if (!e.getClickedBlock().getType().equals(Material.WALL_SIGN))
				return;
			
			Sign sign = (Sign) e.getClickedBlock().getState();
			if (isSign(sign))
			{
				
				GameMap gamemap = Quakecraft.getInstance().getMaps().get(sign.getLine(1));
				
				if (gamemap == null)
					return;
				
				if (gamemap.inProgress())
				{
					e.getPlayer().sendMessage("§cGame is in progress.");
					return;
				}
				gamemap.getPlayers().put(e.getPlayer().getName(), QPlayer.get(e.getPlayer().getName()));
				QPlayer.get(e.getPlayer().getName()).setMap(gamemap.getName());
				e.getPlayer().teleport(gamemap.getLocation());
				gamemap.checkPlayers();
				
			}
		}
		else if (QPlayer.get(e.getPlayer().getName()).getMap() != null)
		{
			if (e.getPlayer().getItemInHand() == null)
				return;
			if (e.getPlayer().getItemInHand().getType().equals(Material.WOOD_HOE))
			{
				if (Cooldown.tryCooldown(e.getPlayer(), "fwk", 3000))
				{
					Firework fw = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
					fw.setVelocity(e.getPlayer().getLocation().getDirection());
					track(fw, e.getPlayer());
				}
			}
			else if (e.getPlayer().getItemInHand().getType().equals(Material.STONE_HOE))
			{
				if (Cooldown.tryCooldown(e.getPlayer(), "fwk", 2500))
				{
					Firework fw = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
					fw.setVelocity(e.getPlayer().getLocation().getDirection());
					track(fw, e.getPlayer());
				}
			}
			else if (e.getPlayer().getItemInHand().getType().equals(Material.IRON_HOE))
			{
				if (Cooldown.tryCooldown(e.getPlayer(), "fwk", 2000))
				{
					Firework fw = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
					fw.setVelocity(e.getPlayer().getLocation().getDirection());
					track(fw, e.getPlayer());
				}
			}
			else if (e.getPlayer().getItemInHand().getType().equals(Material.GOLD_HOE))
			{
				if (Cooldown.tryCooldown(e.getPlayer(), "fwk", 1500))
				{
					Firework fw = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
					fw.setVelocity(e.getPlayer().getLocation().getDirection());
					track(fw, e.getPlayer());
				}
			}
			else if (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE))
			{
				if (Cooldown.tryCooldown(e.getPlayer(), "fwk", 1300))
				{
					Firework fw = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
					fw.setVelocity(e.getPlayer().getLocation().getDirection());
					track(fw, e.getPlayer());
				}
			}
		}
	}
	
	
	
	private boolean isSign(Sign sign)
	{
		if (sign.getLine(0) != null && sign.getLine(0).equalsIgnoreCase("§a[Join]") || sign.getLine(0).equalsIgnoreCase("§5[VIP]"))
			return true;
		return false;
	}
	
	
	
	private void track(final Entity e, final Player shooter)
	{
		final TempData data = new TempData();
		if (e instanceof Firework)
			data.taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Quakecraft.getInstance(), new Runnable()
			{
				
				
				@Override
				public void run()
				{
					for (Entity ee : e.getNearbyEntities(0.5, 0.5, 0.5))
					{
						if (ee instanceof Player)
						{
							if (!((Player) ee).getName().equalsIgnoreCase(shooter.getName()))
							{
								((Player) ee).setHealth(0);
							}
						}
					}
					if (data.time < 1)
						Bukkit.getServer().getScheduler().cancelTask(data.taskID);
					data.time--;
					
				}
			}, 5L, 1L);
	}
}
