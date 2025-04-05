package com.github;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CheckCommandExecutor implements CommandExecutor {

    private AnCheckPlugin plugin;
    private Set<String> checkedPlayers;

    public CheckCommandExecutor(AnCheckPlugin plugin) {
        this.plugin = plugin;
        this.checkedPlayers = new HashSet<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationMessages messages = plugin.getMessages();

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("ancheckplugin.reload")) {
                plugin.reloadConfigValues();
                sender.sendMessage(messages.configReloaded);
            } else {
                sender.sendMessage(messages.noPermission);
            }
            return true;
        }

        if (args.length != 1 && args.length != 2) {
            sender.sendMessage(messages.invalidCommandUsage);
            return false;
        }

        if (!sender.hasPermission("ancheckplugin.check")) {
            sender.sendMessage(messages.noPermission);
            return false;
        }

        if (args.length == 1) {
            String playerName = args[0];
            Player player = sender.getServer().getPlayer(playerName);

            if (player == null) {
                sender.sendMessage(messages.playerNotFound);
                return false;
            }

            Location checkLocation = plugin.getCheckLocation();
            if (checkLocation == null) {
                sender.sendMessage("Check location is not set correctly in config.yml.");
                return false;
            }

            checkedPlayers.add(playerName);
            player.teleport(checkLocation);
            sender.sendMessage(messages.playerTeleported.replace("{player}", playerName));
            plugin.startCheckMessageTask(player);
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("off")) {
            String playerName = args[1];
            if (checkedPlayers.contains(playerName)) {
                checkedPlayers.remove(playerName);
                sender.sendMessage(messages.playerReleased.replace("{player}", playerName));
                if (plugin.getReleaseCommand() != null && !plugin.getReleaseCommand().isEmpty()) {
                    String commandToExecute = plugin.getReleaseCommand().replace("{player}", playerName);
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandToExecute);
                }
            } else {
                sender.sendMessage(messages.playerNotUnderCheck.replace("{player}", playerName));
            }
            return true;
        }

        sender.sendMessage(messages.invalidCommandUsage);
        return false;
    }

    public boolean isPlayerUnderCheck(String playerName) {
        return checkedPlayers.contains(playerName);
    }
}