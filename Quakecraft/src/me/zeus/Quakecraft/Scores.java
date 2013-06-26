
package me.zeus.Quakecraft;


import java.util.HashMap;

import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;



public class Scores
{
	
	
	private HashMap<String, Scoreboard> playerboards;
	
	
	
	public Scores()
	{
		
		playerboards = new HashMap<String, Scoreboard>();
		
		// Schedule it to load all players directly on the next tick to allow the server to get ready.
		// This is here in case the plugin was reloaded.
		Bukkit.getServer().getScheduler().runTask(Quakecraft.getInstance(), new Runnable()
		{
			
			
			@Override
			public void run()
			{
				for (Player player : Bukkit.getServer().getOnlinePlayers())
					addPlayer(player);
			}
		});
	}
	
	
	
	public void addPlayer(final Player player)
	{
		final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = board.registerNewObjective("stats", "dummy");
		
		o.setDisplayName("§6§lStats");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score s = o.getScore(Bukkit.getOfflinePlayer("§eCoins:"));
		s.setScore(QPlayer.get(player.getName()).getPoints());
		Score s2 = o.getScore(Bukkit.getOfflinePlayer("§eKills:"));
		s2.setScore(QPlayer.get(player.getName()).getKills());
		playerboards.put(player.getName(), board);
		
		Bukkit.getServer().getScheduler().runTask(Quakecraft.getInstance(), new Runnable()
		{
			
			
			@Override
			public void run()
			{
				player.setScoreboard(board);
			}
		});
	}
	
	
	
	public void removePlayer(Player player)
	{
		if (playerboards.containsKey(player.getName()))
		{
			playerboards.remove(player.getName());
			
			// Just in case this method was called by toggleHideBoard()
			player.setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
		}
	}
	
	
	
	public void updateBalance(String playername)
	{
		if (playerboards.containsKey(playername))
		{
			Scoreboard board = playerboards.get(playername);
			Objective o = board.getObjective(DisplaySlot.SIDEBAR);
			
			Score s = o.getScore(Bukkit.getOfflinePlayer("§eCoins:"));
			s.setScore(QPlayer.get(playername).getPoints());
			Score s2 = o.getScore(Bukkit.getOfflinePlayer("§eKills:"));
			s2.setScore(QPlayer.get(playername).getKills());
		}
	}
	
	
	
	public void toggleHideBoard(Player player)
	{
		if (playerboards.containsKey(player.getName()))
			removePlayer(player);
		else
			addPlayer(player);
	}
}
