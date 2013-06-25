
package me.zeus.Quakecraft.Events;


import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.TempData;
import me.zeus.Quakecraft.Enumeration.RailgunType;
import me.zeus.Quakecraft.Objects.Cooldown;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;



public class EVT_RailgunShoot implements Listener
{
	
	
	@EventHandler
	public void onShoot(RailgunShootEvent e)
	{
		Player p = e.getShooter();
		RailgunType gun = e.getGunType();
		switch (gun)
		{
			case DAXE:
				if (Cooldown.tryCooldown(p, "fwk", 1700))
				{
					e.getShooter()
					        .getWorld()
					        .strikeLightning(
					                new Location(e.getShooter().getWorld(), e.getShooter().getLocation().getX() + 2, e.getShooter().getLocation().getY(), e.getShooter().getLocation().getZ() + 2));
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					fw.setVelocity(p.getLocation().getDirection());
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.AQUA).build());
					fw.setFireworkMeta(meta);
					track(fw, p);
				}
				break;
			case DIAMOND:
				if (Cooldown.tryCooldown(p, "fwk", 1900))
				{
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					fw.setVelocity(p.getLocation().getDirection());
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).build());
					fw.setFireworkMeta(meta);
					track(fw, p);
				}
				break;
			case GOLD:
				if (Cooldown.tryCooldown(p, "fwk", 2100))
				{
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					fw.setVelocity(p.getLocation().getDirection());
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).build());
					fw.setFireworkMeta(meta);
					track(fw, p);
				}
				break;
			case IRON:
				if (Cooldown.tryCooldown(p, "fwk", 2400))
				{
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					fw.setVelocity(p.getLocation().getDirection());
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).build());
					fw.setFireworkMeta(meta);
					track(fw, p);
				}
				break;
			case STONE:
				if (Cooldown.tryCooldown(p, "fwk", 2700))
				{
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					fw.setVelocity(p.getLocation().getDirection());
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).build());
					fw.setFireworkMeta(meta);
					track(fw, p);
				}
				break;
			case WOOD:
				if (Cooldown.tryCooldown(p, "fwk", 3000))
				{
					Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					fw.setVelocity(p.getLocation().getDirection());
					FireworkMeta meta = fw.getFireworkMeta();
					meta.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).build());
					fw.setFireworkMeta(meta);
					track(fw, p);
				}
				break;
			default:
				break;
		}
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
						if (ee instanceof Player)
							if (!((Player) ee).getName().equalsIgnoreCase(shooter.getName()))
							{
								Bukkit.getServer().getPluginManager().callEvent(new RailgunKillEvent(shooter, (Player) ee, RailgunType.grabType(shooter.getItemInHand())));
								((Player) ee).setHealth(0);
							}
					if (data.time < 1)
						Bukkit.getServer().getScheduler().cancelTask(data.taskID);
					data.time--;
					
				}
			}, 5L, 1L);
	}
}
