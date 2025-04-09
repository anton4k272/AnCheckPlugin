package com.github;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerCheckListener implements Listener {

    private final AnCheckPlugin plugin;

    public PlayerCheckListener(AnCheckPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String playerName = event.getPlayer().getName();

        // Проверка, находится ли игрок под проверкой
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true); // Отмена движения
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        String playerName = event.getPlayer().getName();

        // Проверка, находится ли игрок под проверкой
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true); // Отмена выбрасывания предметов
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();

        // Проверка, находится ли игрок под проверкой
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true); // Отмена взаимодействия
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();

        // Снимаем игрока с проверки при выходе
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            plugin.getCommandExecutor().removePlayerFromCheck(playerName);

            // Уведомляем модераторов о выходе игрока
            Bukkit.getOnlinePlayers().stream()
                    .filter(player -> player.hasPermission("ancheckplugin.moderator"))
                    .forEach(player -> player.sendMessage(
                            plugin.getMessages().playerLeftDuringCheck
                                    .replace("{player}", playerName)
                    ));

            // Выполняем команду, указанную в конфигурации
            String command = plugin.getConfig().getString("settings.command-on-check-leave")
                    .replace("{player}", playerName);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}