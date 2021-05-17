package com.redehunters.bukkit.huntersgladiador.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import lombok.Data;

@Data
public abstract class SubCommand {

	private final String name, description, usage, permission;
	private final List<String> alias;

	public SubCommand(String name, String description, String usage, String permission, String... alias)
	{
		this.name = name;
		this.description = description;
		this.usage = usage;
		this.permission = permission;
		this.alias = Arrays.asList(alias);
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
