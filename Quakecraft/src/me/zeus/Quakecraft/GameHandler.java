
package me.zeus.Quakecraft;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zeus.Quakecraft.Enumeration.RailgunType;
import me.zeus.Quakecraft.Objects.GameMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class GameHandler
{
	
	
	ItemStack railgun;
	ItemStack diamond;
	ItemStack iron;
	ItemStack gold;
	ItemStack stone;
	ItemStack daxe;
	
	ItemStack shop;
	
	public Location lobby;
	
	Map<GameMap, Sign> signs;
	
	List<ItemStack> items;
	
	
	
	public GameHandler()
	{
		signs = new HashMap<GameMap, Sign>();
		
		railgun = new ItemStack(Material.WOOD_HOE, 1);
		ItemMeta meta = railgun.getItemMeta();
		meta.setDisplayName("§aRailgun");
		meta.setLore(Arrays.asList("§5Pew pew!"));
		railgun.setItemMeta(meta);
		
		diamond = new ItemStack(Material.DIAMOND_HOE, 1);
		diamond.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
		ItemMeta meta2 = diamond.getItemMeta();
		meta2.setDisplayName("§aRailgun");
		meta2.setLore(Arrays.asList("§5Pew pew!"));
		diamond.setItemMeta(meta2);
		
		iron = new ItemStack(Material.IRON_HOE, 1);
		ItemMeta meta3 = railgun.getItemMeta();
		meta3.setDisplayName("§aRailgun");
		meta3.setLore(Arrays.asList("§5Pew pew!"));
		railgun.setItemMeta(meta3);
		
		gold = new ItemStack(Material.GOLD_HOE, 1);
		ItemMeta meta4 = railgun.getItemMeta();
		meta4.setDisplayName("§aRailgun");
		meta4.setLore(Arrays.asList("§5Pew pew!"));
		railgun.setItemMeta(meta4);
		
		stone = new ItemStack(Material.STONE_HOE, 1);
		ItemMeta meta5 = stone.getItemMeta();
		meta5.setDisplayName("§aRailgun");
		meta5.setLore(Arrays.asList("§5Pew pew!"));
		stone.setItemMeta(meta5);
		
		daxe = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta meta6 = daxe.getItemMeta();
		meta6.setDisplayName("§aRailgun");
		meta6.setLore(Arrays.asList("§5Pew Pew!"));
		daxe.setItemMeta(meta6);
		
		shop = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta7 = shop.getItemMeta();
		meta7.setDisplayName("§6Shop");
		meta7.setLore(Arrays.asList("§aPurchase new items!", "", "§6See balance on right side."));
		shop.setItemMeta(meta7);
		
		items = new ArrayList<ItemStack>();
		
		ItemStack opSword = new ItemStack(Material.DIAMOND_SWORD, 1);
		opSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 100);
		opSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 100);
		opSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 100);
		opSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 100);
		opSword.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
		items.add(opSword);
		
		ItemStack[] armors = new ItemStack[] { new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_BOOTS),
		        new ItemStack(Material.DIAMOND_LEGGINGS) };
		for (ItemStack i : armors)
		{
			i.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 100);
			i.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 100);
			i.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 100);
			i.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 100);
			i.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 100);
		}
	}
	
	
	
	public ItemStack getGun()
	{
		return railgun;
	}
	
	
	
	public ItemStack getShop()
	{
		return shop;
	}
	
	
	
	public ItemStack getGun(RailgunType type)
	{
		switch (type)
		{
			case DAXE:
				return daxe;
			case DIAMOND:
				return diamond;
			case GOLD:
				return gold;
			case IRON:
				return iron;
			case NULL:
				return railgun;
			case STONE:
				return stone;
			case WOOD:
				return railgun;
			default:
				break;
		}
		return null;
	}
	
	
	
	public List<ItemStack> getOpItems()
	{
		return items;
	}
	
	
	
}
