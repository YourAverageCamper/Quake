
package me.zeus.Quakecraft;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import me.zeus.Quakecraft.Commands.CMD_Map;
import me.zeus.Quakecraft.Commands.CMD_Quake;
import me.zeus.Quakecraft.Enumeration.Upgrade;
import me.zeus.Quakecraft.Events.EVT_Command;
import me.zeus.Quakecraft.Events.EVT_GameLeave;
import me.zeus.Quakecraft.Events.EVT_GameStart;
import me.zeus.Quakecraft.Events.EVT_PlayerInteract;
import me.zeus.Quakecraft.Events.EVT_PlayerJoin;
import me.zeus.Quakecraft.Events.EVT_Quit;
import me.zeus.Quakecraft.Events.EVT_Railgun;
import me.zeus.Quakecraft.Events.EVT_RailgunShoot;
import me.zeus.Quakecraft.Events.EVT_Respawn;
import me.zeus.Quakecraft.Events.EVT_SignUpdate;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.IconMenu;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Quakecraft extends JavaPlugin
{
	
	
	private static Quakecraft instance;
	GameHandler gameHandler;
	File rootDir;
	File playersDir;
	File signsDir;
	File mapsDir;
	File config;
	Map<String, QPlayer> players;
	Map<String, GameMap> maps;
	IconMenu menu;
	Scores scores;
	
	
	
	/*
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable()
	{
		instance = this;
		players = new HashMap<String, QPlayer>();
		maps = new HashMap<String, GameMap>();
		gameHandler = new GameHandler();
		scores = new Scores();
		init_events();
		init_commands();
		init_folders();
		init_loadData();
		init_config();
		
		
		menu = new IconMenu("§1Quake Shop", 9 * 5, new IconMenu.OptionClickEventHandler()
		{
			
			
			@Override
			public void onOptionClick(IconMenu.OptionClickEvent event)
			{
				Player player = event.getPlayer();
				QPlayer qp = QPlayer.get(player.getName());
				switch (event.getPosition())
				{
					case 0:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased space hat.");
							qp.getUpgrades().add(Upgrade.HAT_SPACEMAN);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 1:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased lantern hat.");
							qp.getUpgrades().add(Upgrade.HAT_LANTERN);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 2:
						if (qp.getPoints() > 10000)
						{
							player.sendMessage("§aPurchased ice hat.");
							qp.getUpgrades().add(Upgrade.HAT_ICE);
							qp.removePoints(10000);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 3:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased redstone hat.");
							qp.getUpgrades().add(Upgrade.HAT_REDSTONE);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 4:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased diamond hat.");
							qp.getUpgrades().add(Upgrade.HAT_DIAMOND);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 5:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased melon hat.");
							qp.getUpgrades().add(Upgrade.HAT_MELON);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 6:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased dispenser hat.");
							qp.removePoints(700);
							qp.getUpgrades().add(Upgrade.HAT_DISPENSER);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 7:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased tnt hat.");
							qp.getUpgrades().add(Upgrade.HAT_TNT);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 8:
						if (qp.getPoints() > 700)
						{
							player.sendMessage("§aPurchased majestic hat.");
							qp.getUpgrades().add(Upgrade.HAT_MAJESTIC);
							qp.removePoints(700);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 9:
						if (qp.getPoints() > 650)
						{
							player.sendMessage("§aPurchased Soldier Kit.");
							qp.getUpgrades().add(Upgrade.KIT_SOLDIER);
							qp.removePoints(650);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 10:
						if (qp.getPoints() > 2250)
						{
							player.sendMessage("§aPurchased Elite Kit.");
							qp.getUpgrades().add(Upgrade.KIT_ELITE);
							qp.removePoints(2250);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 11:
						if (qp.getPoints() > 2250)
						{
							player.sendMessage("§aPurchased Majestic Kit.");
							qp.getUpgrades().add(Upgrade.KIT_MAJESTIC);
							qp.removePoints(2250);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 12:
						if (qp.getPoints() > 2250)
						{
							player.sendMessage("§aPurchased Commander Kit.");
							qp.getUpgrades().add(Upgrade.KIT_COMMANDER);
							qp.removePoints(2250);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 18:
						if (qp.getPoints() > 1800)
						{
							player.sendMessage("§aPurchased BFG Gun");
							qp.getUpgrades().add(Upgrade.GUN_STONE);
							qp.removePoints(1800);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 19:
						if (qp.getPoints() > 3500)
						{
							player.sendMessage("§aPurchased Superior Gun");
							qp.getUpgrades().add(Upgrade.GUN_IRON);
							qp.removePoints(3500);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 20:
						if (qp.getPoints() > 3500)
						{
							player.sendMessage("§aPurchased Hyperbeam Gun");
							qp.getUpgrades().add(Upgrade.GUN_GOLD);
							qp.removePoints(3500);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 21:
						if (qp.getPoints() > 3500)
						{
							player.sendMessage("§aPurchased Creeper Gun");
							qp.getUpgrades().add(Upgrade.GUN_DIAMOND);
							qp.removePoints(3500);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
					case 22:
						if (qp.getPoints() > 17000)
						{
							player.sendMessage("§aPurchased Diamond Gun");
							qp.getUpgrades().add(Upgrade.GUN_DIAMOND_AXE);
							qp.removePoints(17000);
						}
						else
							player.sendMessage("§cNot enough points to do this!");
						break;
				}
				event.setWillClose(true);
			}
			//!f
		}, this)
		.setOption(0, new ItemStack(Material.GLASS, 1), "§cSpaceman Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(1, new ItemStack(Material.JACK_O_LANTERN, 1), "§cLantern Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(2, new ItemStack(Material.ICE, 1), "§cIce Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §610000"})
		.setOption(3, new ItemStack(Material.REDSTONE_BLOCK, 1), "§cRedstone Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(4, new ItemStack(Material.DIAMOND_BLOCK, 1), "§cDiamond Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(5, new ItemStack(Material.MELON_BLOCK, 1), "§cMelon Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(6, new ItemStack(Material.DISPENSER, 1), "§cDispenser Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(7, new ItemStack(Material.TNT, 1), "§cTNT Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(8, new ItemStack(Material.GOLD_HELMET, 1), "§cMajestic Hat", new String[]{"§rCollect all the hats!", "", "§rPrice: §6700"})
		.setOption(9,  new ItemStack(Material.LEATHER_CHESTPLATE, 1), "§cSoldier Kit", new String[]{"§rCosmetic Armor", "", "§rPrice: §6650"})
		.setOption(10,  colorize(Color.BLUE), "§cElite Kit", new String[]{"§rCosmetic Armor", "", "§rPrice: §62250"})
		.setOption(11,  colorize(Color.YELLOW), "§cMajestic Kit", new String[]{"§rCosmetic Armor", "", "§rPrice: §62250"})
		.setOption(12,  colorize(Color.BLACK), "§cCommander Kit", new String[]{"§rCosmetic Armor", "", "§rPrice: §62250"})
		.setOption(18, new ItemStack(Material.STONE_HOE, 1), "§cBFG", new String[]{"§rReload time: 1.4s", "§cRequires default railgun", "§rPrice: §61800" })
		.setOption(19, new ItemStack(Material.IRON_HOE, 1), "§cSuperior Railgun", new String[]{"§rReload time: 1.3s", "", "§rPrice: §63500" })
		.setOption(20, new ItemStack(Material.GOLD_HOE, 1), "§cHyper Beam Railgun", new String[]{"§rReload time: 1.2s", "§cRequires previous weapon", "§rPrice: §63500" })
		.setOption(21, new ItemStack(Material.DIAMOND_HOE, 1), "§cCreeper Railgun", new String[]{"§rReload time: 1.1s", "§cRequires previous weapon", "§rPrice: §68500" })
		.setOption(22, new ItemStack(Material.DIAMOND_AXE, 1), "§cDiamond Railgun", new String[]{"§rReload time: 0.9s", "§cRequires previous weapon", "§rPrice: §617000" })
		.setOption(36, new ItemStack(Material.EMERALD, 1), "§aShop",  new String[] { "§7Buy cool items!", "", "§6See sidebar for points." })
		;
		//f
		
		for (Player p : getServer().getOnlinePlayers())
		{
			if (p.getWorld().equals(getConfig().getString("quake_world")))
				scores.addPlayer(p);
			else
				scores.toggleHideBoard(p);
		}
		init_stats();
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public void onDisable()
	{
		init_saveData();
		instance = null;
	}
	
	
	
	/*
	 * Load all folders
	 */
	private void init_folders()
	{
		rootDir = new File(getDataFolder() + "");
		if (!rootDir.exists())
		{
			rootDir.mkdir();
			getLogger().info("Creating root directory!");
		}
		playersDir = new File(getDataFolder() + "/players");
		if (!playersDir.exists())
		{
			playersDir.mkdir();
			getLogger().info("Creating players directory!");
		}
		signsDir = new File(getDataFolder() + "/signs");
		if (!signsDir.exists())
			signsDir.mkdir();
		mapsDir = new File(getDataFolder() + "/maps");
		if (!mapsDir.exists())
			mapsDir.mkdir();
	}
	
	
	
	/* 
	 * Load all stored data
	 */
	private void init_loadData()
	{
		getLogger().info("Loading all data!");
		
		/* Load all players */
		File[] pfiles = playersDir.listFiles();
		for (int i = 0; i < pfiles.length; i++)
		{
			File f = pfiles[i];
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				QPlayer player = (QPlayer) ois.readObject();
				players.put(player.getName(), player);
				ois.close();
			}
			catch (IOException | ClassNotFoundException ioe)
			{
				ioe.printStackTrace();
			}
		}
		
		/* Load all maps */
		File[] mFiles = mapsDir.listFiles();
		for (int i = 0; i < mFiles.length; i++)
		{
			File f = mFiles[i];
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				GameMap map = (GameMap) ois.readObject();
				maps.put(map.getName(), map);
				map.setPlayers(new HashMap<String, QPlayer>());
				map.setInProgress(false);
				ois.close();
			}
			catch (IOException | ClassNotFoundException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	
	
	
	/*
	 * Save all stored data
	 */
	private void init_saveData()
	{
		// maps
		for (GameMap gm : maps.values())
			try
			{
				File f = new File(mapsDir + "/" + gm.getName());
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(gm);
				oos.close();
			}
			catch (IOException ioe)
			{
				getLogger().severe("There was a critical error when saving data for SIGN: " + gm.getName() + "! Data either does not exist or is corrupt!");
			}
		
		// players
		for (QPlayer player : players.values())
			try
			{
				File f = new File(playersDir + "/" + player.getName());
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(player);
				oos.close();
			}
			catch (IOException ioe)
			{
				getLogger().severe("There was a critical error when saving data for " + player.getName() + "! Data either does not exist or is corrupt!");
			}
	}
	
	
	
	private void init_commands()
	{
		getCommand("quake").setExecutor(new CMD_Quake());
		getCommand("map").setExecutor(new CMD_Map());
	}
	
	
	
	private void init_events()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EVT_PlayerInteract(), this);
		pm.registerEvents(new EVT_PlayerJoin(), this);
		pm.registerEvents(new EVT_SignUpdate(), this);
		pm.registerEvents(new EVT_Railgun(), this);
		pm.registerEvents(new EVT_GameStart(), this);
		pm.registerEvents(new EVT_Respawn(), this);
		pm.registerEvents(new EVT_RailgunShoot(), this);
		pm.registerEvents(new EVT_Command(), this);
		pm.registerEvents(new EVT_GameLeave(), this);
		pm.registerEvents(new EVT_Quit(), this);
	}
	
	
	
	private void init_config()
	{
		config = new File(rootDir + "/config.yml");
		if (!config.exists())
			saveDefaultConfig();
		String world = getConfig().getString("lobby-location.world");
		int x = getConfig().getInt("lobby-location.x");
		int y = getConfig().getInt("lobby-location.x");
		int z = getConfig().getInt("lobby-location.x");
		float pitch = Float.parseFloat(getConfig().getString("lobby-location.pitch"));
		float yaw = Float.parseFloat(getConfig().getString("lobby-location.yaw"));
		
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setPitch(pitch);
		loc.setYaw(yaw);
		
		getGameHandler().lobby = loc;
	}
	
	
	
	private void init_stats()
	{
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
		{
			
			
			@Override
			public void run()
			{
				for (Player p : getServer().getOnlinePlayers())
				{
					if (!QPlayer.get(p.getName()).inGame())
					{
						scores.updateBalance(p.getName());
					}
				}
			}
		}, 20L * 5, 20L * 3);
	}
	
	
	
	//===================================================================================================
	
	/**
	 * 
	 * @return returns the plugin instance
	 */
	public static Quakecraft getInstance()
	{
		return instance;
	}
	
	
	
	/**
	 * 
	 * @return returns a map of all game players
	 */
	public Map<String, QPlayer> getPlayers()
	{
		return players;
	}
	
	
	
	/**
	 * 
	 * @return returns gamehandler instance
	 */
	public GameHandler getGameHandler()
	{
		return gameHandler;
	}
	
	
	
	/**
	 * 
	 * @return returns a map of all players in lobbies
	 */
	public Map<String, GameMap> getMaps()
	{
		return maps;
	}
	
	
	
	/**
	 * 
	 * @return grab shop menu
	 */
	public IconMenu getMenu()
	{
		return menu;
	}
	
	
	
	private ItemStack colorize(Color color)
	{
		ItemStack is = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
		meta.setColor(color);
		is.setItemMeta(meta);
		return is;
	}
	
	
	
	/**
	 * 
	 * @return return scoreboard holder instance
	 */
	public Scores getScores()
	{
		return scores;
	}
}
