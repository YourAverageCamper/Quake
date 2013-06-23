
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
import me.zeus.Quakecraft.Events.EVT_PlayerInteract;
import me.zeus.Quakecraft.Events.EVT_PlayerJoin;
import me.zeus.Quakecraft.Events.EVT_SignUpdate;
import me.zeus.Quakecraft.Objects.GameMap;
import me.zeus.Quakecraft.Objects.QPlayer;

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
	Map<String, QPlayer> players;
	Map<String, GameMap> maps;
	
	
	
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
		init_events();
		init_commands();
		init_folders();
		init_loadData();
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
	
	
	
}
