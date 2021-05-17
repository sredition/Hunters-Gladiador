package com.redehunters.bukkit.huntersgladiador;

import org.bukkit.plugin.java.JavaPlugin;

public class HuntersGladiador extends JavaPlugin {

	private static HuntersGladiador instance;

	public HuntersGladiador()
	{
		instance = this;
	}

	@Override
	public void onEnable()
	{
	}

	@Override
	public void onDisable()
	{
	}

	public static HuntersGladiador getInstance()
	{
		return instance;
	}
}
