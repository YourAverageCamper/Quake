
package me.zeus.Quakecraft.Objects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.zeus.Quakecraft.Quakecraft;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;



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
	
	
	
	public void prepare()
	{
		getPlayer().getInventory().clear();
		getPlayer().getActivePotionEffects().clear();
		switch (currentUpgrade)
		{
			case GUN_DIAMOND:
				break;
			case GUN_GOLD:
				break;
			case GUN_IRON:
				break;
			case GUN_STONE:
				break;
			case HAT_CACTUS:
				break;
			case HAT_DIAMOND:
				break;
			case HAT_DISPENSER:
				break;
			case HAT_LANTERN:
				break;
			case HAT_MAJESTIC:
				break;
			case HAT_MELON:
				break;
			case HAT_REDSTONE:
				break;
			case HAT_SPACEMAN:
				break;
			case HAT_TNT:
				break;
			case KIT_COMMANDER:
				break;
			case KIT_ELITE:
				break;
			case KIT_MAJESTIC:
				break;
			case KIT_SOLDIER:
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
		SerializableLocation[] locs = gm.getSpawns().toArray(new SerializableLocation[gm.getSpawns().size()]);
		int select = new Random().nextInt(locs.length) - 1;
		getPlayer().teleport(locs[select].getLocation());
		getPlayer().setGameMode(GameMode.ADVENTURE);
	}
	
}
