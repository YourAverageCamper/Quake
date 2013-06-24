
package me.zeus.Quakecraft.Enumeration;


import org.bukkit.inventory.ItemStack;



public enum RailgunType
{
	NULL, WOOD, STONE, IRON, GOLD, DIAMOND, DAXE;
	
	
	public static RailgunType grabType(ItemStack itemInHand)
	{
		if (itemInHand == null)
			return RailgunType.NULL;
		
		switch (itemInHand.getType())
		{
			case WOOD_HOE:
				return WOOD;
			case STONE_HOE:
				return STONE;
			case IRON_HOE:
				return IRON;
			case GOLD_HOE:
				return GOLD;
			case DIAMOND_HOE:
				return DIAMOND;
			case DIAMOND_AXE:
				return DAXE;
			default:
				return NULL;
		}
	}
}
