package com.github;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private final AnCheckPlugin plugin;

    public PlayerChatListener(AnCheckPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation") // Подавляет предупреждение об устаревшем API
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();

        // Проверяем, находится ли игрок под проверкой
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true); // Отменяем сообщение
            event.getPlayer().sendMessage(plugin.getMessages().messageSentToModerator); // Уведомляем игрока

            // Уведомляем модераторов о сообщении
            Bukkit.getOnlinePlayers().stream()
                    .filter(player -> player.hasPermission("ancheckplugin.moderator"))
                    .forEach(player -> player.sendMessage(
                            plugin.getMessages().messageFromPlayer
                                    .replace("{player}", playerName)
                                    .replace("{message}", event.getMessage())
                    ));
        }
    }
}