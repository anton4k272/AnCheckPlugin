package com.github;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private AnCheckPlugin plugin;

    public PlayerChatListener(AnCheckPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getMessages().messageSentToModerator);
            Bukkit.getOnlinePlayers().stream()
                    .filter(player -> player.hasPermission("ancheckplugin.moderator"))
                    .forEach(player -> player.sendMessage(plugin.getMessages().messageFromPlayer
                            .replace("{player}", playerName)
                            .replace("{message}", event.getMessage())));
        }
    }
}