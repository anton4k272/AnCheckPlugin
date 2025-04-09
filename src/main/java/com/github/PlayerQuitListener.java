package com.github;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final AnCheckPlugin plugin;

    public PlayerQuitListener(AnCheckPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            // Выполнить команду от имени консоли
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "kick " + playerName + " Вы вышли во время проверки!"
            );

            // Уведомить модераторов
            Bukkit.getOnlinePlayers().stream()
                    .filter(player -> player.hasPermission("ancheckplugin.moderator"))
                    .forEach(player -> player.sendMessage(
                            plugin.getMessages().playerLeftDuringCheck.replace("{player}", playerName)
                    ));

            // Снять игрока с проверки
            plugin.getCommandExecutor().removePlayerFromCheck(playerName);
        }
    }
}