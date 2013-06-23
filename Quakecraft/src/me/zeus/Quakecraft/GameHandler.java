
package me.zeus.Quakecraft;


import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class GameHandler
{
	
	
	ItemStack railgun;
	
	
	
	public GameHandler()
	{
		railgun = new ItemStack(Material.WOOD_HOE, 1);
		ItemMeta meta = railgun.getItemMeta();
		meta.setDisplayName("§aRailgun");
		meta.setLore(Arrays.asList("§5Pew pew!"));
		railgun.setItemMeta(meta);
	}
	
	
	
	public ItemStack getGun()
	{
		return railgun;
	}
	
	
	
}
