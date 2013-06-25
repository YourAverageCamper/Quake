
package me.zeus.Quakecraft.Enumeration;


import java.io.Serializable;



public enum Upgrade implements Serializable
{
	NULL(""),
	
	HAT_SPACEMAN("quakecraft.upgrades.hat.spaceman"),
	HAT_LANTERN("quakecraft.upgrades.hat.lantern"),
	HAT_ICE("quakecraft.upgrades.hat.ice"),
	HAT_REDSTONE("quakecraft.upgrades.hat.redstone"),
	HAT_DIAMOND("quakecraft.upgrades.hat.diamond"),
	HAT_MELON("quakecraft.upgrades.hat.melon"),
	HAT_DISPENSER("quakecraft.upgrades.hat.dispenser"),
	HAT_TNT("quakecraft.upgrades.hat.tnt"),
	HAT_MAJESTIC("quakecraft.upgrades.hat.majestic"),
	
	KIT_SOLDIER("quakecraft.upgrades.kit.soldier"),
	KIT_ELITE("quakecraft.upgrades.kit.elite"),
	KIT_MAJESTIC("quakecraft.upgrades.kit.majestic"),
	KIT_COMMANDER("quakecraft.upgrades.kit.commander"),
	
	GUN_STONE("quakecraft.upgrades.guns.stone"),
	GUN_IRON("quakecraft.upgrades.guns.iron"),
	GUN_GOLD("quakecraft.upgrades.guns.gold"),
	GUN_DIAMOND("quakecraft.upgrades.guns.diamond"),
	GUN_DIAMOND_AXE("quakecraft.upgrades.guns.diamondaxe"),
	TRACKING_DEVICE("quakecraft.upgrades.misc.tracking"),
	
	
	;
	
	
	String perm;
	
	
	
	Upgrade(String perm)
	{
		this.perm = perm;
	}
	
	
	
	public String getPermission()
	{
		return perm;
	}
	
	
	
	public static String getPermission(Upgrade upgrade)
	{
		return upgrade.perm;
	}
	
}
