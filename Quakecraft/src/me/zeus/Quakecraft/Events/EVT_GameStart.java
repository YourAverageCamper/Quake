
package me.zeus.Quakecraft.Events;


import java.util.Collection;

import me.zeus.Quakecraft.Quakecraft;
import me.zeus.Quakecraft.Objects.QPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;



public class EVT_GameStart implements Listener
{
	
	
	@EventHandler
	public void onStart(final GameStartEvent e)
	{
		ScoreboardManager sb = Bukkit.getServer().getScoreboardManager();
		final Scoreboard s = sb.getNewScoreboard();
		final Objective stats = s.registerNewObjective("quake", "dummy");
		stats.setDisplaySlot(DisplaySlot.SIDEBAR);
		stats.setDisplayName("§6§lLeaderboard");
		for (final QPlayer qp : e.getQPlayers())
		{
			final Player p = qp.getPlayer();
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Quakecraft.getInstance(), new Runnable()
			{
				
				
				@Override
				public void run()
				{
					Score score = stats.getScore(Bukkit.getOfflinePlayer(p.getName()));
					score.setScore(qp.getGameKills());
					p.setScoreboard(s);
				}
			}, 20L);
		}
		startLeaderboard(e.getQPlayers());
	}
	
	
	
	private void startLeaderboard(final Collection<QPlayer> players)
	{
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Quakecraft.getInstance(), new Runnable()
		{
			
			
			@Override
			public void run()
			{
				for (QPlayer player : players)
				{
					ScoreboardManager sb = Bukkit.getServer().getScoreboardManager();
					final Scoreboard s = sb.getNewScoreboard();
					final Objective stats = s.registerNewObjective("quake", "dummy");
					stats.setDisplaySlot(DisplaySlot.SIDEBAR);
					stats.setDisplayName("§6§lLeaderboard");
					Score score = stats.getScore(Bukkit.getOfflinePlayer(player.getPlayer().getName()));
					score.setScore(player.getGameKills());
					for (QPlayer aaa : players)
					{
						Score score2 = stats.getScore(Bukkit.getOfflinePlayer(aaa.getPlayer().getName()));
						score2.setScore(aaa.getGameKills());
					}
					player.getPlayer().setScoreboard(s);
				}
			}
		}, 20L * 2, 20L);
	}
}
