
package me.zeus.Quakecraft.Events;


import java.util.Random;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Enumeration.RailgunType;
import me.zeus.Quakecraft.Enumeration.Upgrade;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;
import me.zeus.Quakecraft.Objects.SerializableLocation;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class EVT_Respawn implements Listener
{
	
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		if (!QPlayer.get(e.getPlayer().getName()).inGame())
			return;
		
		final QPlayer player = QPlayer.get(e.getPlayer().getName());
		
		if (!GameMap.getMap(player.getMap()).inProgress())
			return;
		SerializableLocation[] locs = GameMap.getMap(player.getMap()).getSpawns().toArray(new SerializableLocation[GameMap.getMap(player.getMap()).getSpawns().size() - 1]);
		int select = new Random().nextInt(locs.length);
		e.setRespawnLocation(locs[select].getLocation());
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Quakecraft.getInstance(), new Runnable()
		{
			
			
			@Override
			public void run()
			{
				player.getPlayer().getInventory().clear();
				player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
				for (Upgrade up : player.getCurrentUpgrades())
				{
					switch (up)
					{
						case GUN_DIAMOND_AXE:
							player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.DAXE));
							break;
						case GUN_DIAMOND:
							player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.DIAMOND));
							break;
						case GUN_GOLD:
							player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.GOLD));
							break;
						case GUN_IRON:
							player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.IRON));
							break;
						case GUN_STONE:
							player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.STONE));
							break;
						case HAT_ICE:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.ICE, 1));
							player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
							break;
						case HAT_DIAMOND:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
							break;
						case HAT_DISPENSER:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.DISPENSER, 1));
							break;
						case HAT_LANTERN:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.JACK_O_LANTERN, 1));
							break;
						case HAT_MAJESTIC:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
							break;
						case HAT_MELON:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.MELON, 1));
							break;
						case HAT_REDSTONE:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK, 1));
							break;
						case HAT_SPACEMAN:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.GLASS, 1));
							break;
						case HAT_TNT:
							player.getPlayer().getInventory().setHelmet(new ItemStack(Material.TNT, 1));
							break;
						case KIT_COMMANDER:
							player.getPlayer().getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
							break;
						case KIT_ELITE:
							ItemStack c = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
							LeatherArmorMeta meta = (LeatherArmorMeta) c.getItemMeta();
							meta.setColor(Color.BLUE);
							c.setItemMeta(meta);
							break;
						case KIT_MAJESTIC:
							ItemStack cc = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
							LeatherArmorMeta meta2 = (LeatherArmorMeta) cc.getItemMeta();
							meta2.setColor(Color.YELLOW);
							cc.setItemMeta(meta2);
							break;
						case KIT_SOLDIER:
							ItemStack ccc = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
							LeatherArmorMeta meta3 = (LeatherArmorMeta) ccc.getItemMeta();
							meta3.setColor(Color.BLUE);
							ccc.setItemMeta(meta3);
							break;
						case NULL:
							player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
							break;
						case TRACKING_DEVICE:
							player.getPlayer().getInventory().addItem(new ItemStack(Material.COMPASS, 1));
							break;
						default:
							break;
					}
					player.getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				}
			}
		}, 1L);
	}
}
