package com.redehunters.bukkit.huntersgladiador.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

public class GladCommand implements CommandExecutor, TabCompleter {

	private List<SubCommand> subCommands;

	public GladCommand()
	{
		this.subCommands = new ArrayList<>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length >= 1) {
			String arg = args[0].toLowerCase();
			if (!this.subCommands.isEmpty()) {
				for (SubCommand subCommand : this.subCommands)
					if (arg.equalsIgnoreCase(subCommand.getName().toLowerCase())
							|| subCommand.getAlias().contains(arg)) {
						if (sender.hasPermission(subCommand.getPermission()) || subCommand.getPermission().isEmpty())
							return subCommand.onCommand(sender, command, label, args);
						else {
							sender.sendMessage("§cVocê não tem permissão para executar este comando.");
						}
						return false;
					}
			}
		} else {
			sendHelp(sender);
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
	{
		List<String> tabComplete = new ArrayList<>();

		if (args.length <= 0) {

			for (SubCommand subCommand : this.subCommands)
				if (sender.hasPermission(subCommand.getPermission())) {
					tabComplete.add(subCommand.getName());
				}

		} else if (args.length == 1) {

			String arg = args[0].toLowerCase();
			if (!this.subCommands.isEmpty()) {
				for (SubCommand subCommand : this.subCommands)
					if (sender.hasPermission(subCommand.getPermission())
							&& subCommand.getName().toLowerCase().startsWith(arg)) {
						tabComplete.add(subCommand.getName().toLowerCase());
					}
			}

		} else if (args.length > 1) {

			String arg = args[0].toLowerCase();
			if (!this.subCommands.isEmpty()) {
				for (SubCommand subCommand : this.subCommands)
					if ((arg.equalsIgnoreCase(subCommand.getName().toLowerCase())
							|| subCommand.getAlias().contains(arg))
							&& (sender.hasPermission(subCommand.getPermission())
									|| subCommand.getPermission().isEmpty())) {
						tabComplete = subCommand.onTabComplete(sender, command, label, args);
					}
			}

		}

		return ((tabComplete != null) ? tabComplete : new ArrayList<>());
	}

	public List<SubCommand> getSubCommands()
	{
		return this.subCommands;
	}

	public void setup(PluginCommand command)
	{
		if (command != null) {
			command.setExecutor(this);
			command.setTabCompleter(this);
		}
	}

	public void sendHelp(CommandSender sender)
	{
		if (sender != null) {

			List<String> lines = new ArrayList<>();

			for (SubCommand subcommand : getSubCommands()) {
				if (sender.hasPermission(subcommand.getPermission())) {
					lines.add("§a" + subcommand.getUsage() + " §8- §7" + subcommand.getDescription());
				}
			}

			if (!lines.isEmpty()) {
				sender.sendMessage(" ");
				sender.sendMessage(lines.toArray(new String[lines.size()]));
				sender.sendMessage(" ");
			}
		}
	}
}
