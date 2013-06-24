
package me.zeus.Quakecraft.Objects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Enumeration.RailgunType;
import me.zeus.Quakecraft.Enumeration.Upgrade;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class QPlayer implements Serializable
{
	
	
	private static final long serialVersionUID = -1794427337772023746L;
	String name;
	transient String map;
	int points;
	int kills;
	int deaths;
	List<Upgrade> upgrades;
	Upgrade currentUpgrade;
	transient boolean inGame;
	transient int gameKills;
	
	
	
	public QPlayer(String name)
	{
		this.name = name;
		this.points = 0;
		this.kills = 0;
		this.deaths = 0;
		this.upgrades = new ArrayList<Upgrade>();
		this.currentUpgrade = Upgrade.NULL;
	}
	
	
	
	/**
	 * 
	 * @return returns the player's username
	 */
	public String getName()
	{
		return name;
	}
	
	
	
	/**
	 * 
	 * @return returns the player's current points
	 */
	public int getPoints()
	{
		return points;
	}
	
	
	
	/**
	 * 
	 * @return returns the player's total kills
	 */
	public int getKills()
	{
		return kills;
	}
	
	
	
	/**
	 * 
	 * @return returns the player's total deaths
	 */
	public int getDeaths()
	{
		return deaths;
	}
	
	
	
	/**
	 * 
	 * @return returns the name of the map the player is on
	 */
	public String getMap()
	{
		return map;
	}
	
	
	
	/**
	 * 
	 * @return returns the Bukkit player object
	 */
	public Player getPlayer()
	{
		return Bukkit.getPlayer(name);
	}
	
	
	
	/**
	 * 
	 * @return returns if player has any upgrades
	 */
	public boolean hasUpgrades()
	{
		return upgrades.size() > 0;
	}
	
	
	
	/**
	 * 
	 * @return returns a list of upgrades
	 */
	public List<Upgrade> getUpgrades()
	{
		return upgrades;
	}
	
	
	
	/**
	 * 
	 * @param name2
	 * @return returns a QPlayer object which was asked for
	 */
	public static QPlayer get(String name2)
	{
		return Quakecraft.getInstance().getPlayers().get(name2);
	}
	
	
	
	/**
	 * 
	 * @return returns if a player is in an active lobby/game
	 */
	public boolean inGame()
	{
		return inGame;
	}
	
	
	
	public void setInGame(boolean b)
	{
		this.inGame = b;
	}
	
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	
	public void setPoints(int amt)
	{
		this.points = amt;
	}
	
	
	
	public void setKills(int amt)
	{
		this.kills = amt;
	}
	
	
	
	public void setDeaths(int amt)
	{
		this.deaths = amt;
	}
	
	
	
	public void addKills(int toAdd)
	{
		this.kills += toAdd;
	}
	
	
	
	public void addDeaths(int toAdd)
	{
		this.deaths += toAdd;
	}
	
	
	
	public void addPoints(int toAdd)
	{
		this.points += toAdd;
	}
	
	
	
	public void removePoints(int parseInt)
	{
		this.points -= parseInt;
	}
	
	
	
	public void setMap(String name)
	{
		this.map = name;
	}
	
	
	
	public void setCurrentUpgrade(Upgrade upgrade)
	{
		this.currentUpgrade = upgrade;
	}
	
	
	
	public int getGameKills()
	{
		return gameKills;
	}
	
	
	
	public void addGameKills(int i)
	{
		gameKills += i;
	}
	
	
	
	public void prepare()
	{
		getPlayer().getInventory().clear();
		getPlayer().getActivePotionEffects().clear();
		switch (currentUpgrade)
		{
			case GUN_DIAMOND:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.DIAMOND));
				break;
			case GUN_DIAMOND_AXE:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.DAXE));
				break;
			case GUN_GOLD:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.GOLD));
				break;
			case GUN_IRON:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.IRON));
				break;
			case GUN_STONE:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun(RailgunType.STONE));
				break;
			case HAT_ICE:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.ICE, 1));
				getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
				break;
			case HAT_DIAMOND:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
				break;
			case HAT_DISPENSER:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.DISPENSER, 1));
				break;
			case HAT_LANTERN:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.JACK_O_LANTERN, 1));
				break;
			case HAT_MAJESTIC:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
				break;
			case HAT_MELON:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.MELON, 1));
				break;
			case HAT_REDSTONE:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK, 1));
				break;
			case HAT_SPACEMAN:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.GLASS, 1));
				break;
			case HAT_TNT:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getInventory().setHelmet(new ItemStack(Material.TNT, 1));
				break;
			case KIT_COMMANDER:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				getPlayer().getPlayer().getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
				break;
			case KIT_ELITE:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				ItemStack c = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
				LeatherArmorMeta meta = (LeatherArmorMeta) c.getItemMeta();
				meta.setColor(Color.BLUE);
				c.setItemMeta(meta);
				getPlayer().getInventory().setChestplate(c);
				break;
			case KIT_MAJESTIC:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				ItemStack cc = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
				LeatherArmorMeta meta2 = (LeatherArmorMeta) cc.getItemMeta();
				meta2.setColor(Color.YELLOW);
				cc.setItemMeta(meta2);
				getPlayer().getInventory().setChestplate(cc);
				break;
			case KIT_SOLDIER:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				ItemStack ccc = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
				LeatherArmorMeta meta3 = (LeatherArmorMeta) ccc.getItemMeta();
				meta3.setColor(Color.BLUE);
				ccc.setItemMeta(meta3);
				getPlayer().getInventory().setChestplate(ccc);
				break;
			case NULL:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				break;
			default:
				getPlayer().getInventory().addItem(Quakecraft.getInstance().getGameHandler().getGun());
				break;
		}
		
		GameMap gm = GameMap.getMap(map);
		if (map == null)
			return;
		SerializableLocation[] locs = gm.getSpawns().toArray(new SerializableLocation[gm.getSpawns().size() - 1]);
		int select = new Random().nextInt(locs.length);
		getPlayer().teleport(locs[select].getLocation());
	}
	
	
	
}
