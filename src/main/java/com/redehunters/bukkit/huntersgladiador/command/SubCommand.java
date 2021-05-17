package com.redehunters.bukkit.huntersgladiador.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {

	private String name;
	private String description;
	private String usage;
	private String permission;
	private List<String> alias;

	public SubCommand(String name, String description, String usage, String permission, String... alias)
	{
		this.name = name;
		this.description = description;
		this.usage = usage;
		this.permission = permission;
		this.alias = Arrays.asList(alias);
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUsage()
	{
		return this.usage;
	}

	public void setUsage(String usage)
	{
		this.usage = usage;
	}

	public String getPermission()
	{
		return this.permission;
	}

	public void setPermission(String permission)
	{
		this.permission = permission;
	}

	public List<String> getAlias()
	{
		return this.alias;
	}

	public void setAlias(List<String> alias)
	{
		this.alias = alias;
	}

	public void sendUsage(CommandSender sender)
	{
		if (sender != null) {
			sender.sendMessage("§cUse: " + getUsage());
		}
	}

	public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
	{
		return null;
	}

}
