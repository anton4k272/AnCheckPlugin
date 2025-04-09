package com.github;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {

    private final FileConfiguration config;

    public PlayerEventListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Проверка, находится ли игрок на проверке
        if (isUnderCheck(player)) {
            // Снимаем игрока с проверки
            removeFromCheck(player);

            // Выполняем команду из config.yml
            String command = config.getString("on-check-leave-command");
            if (command != null && !command.isEmpty()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
            }
        }
    }

    private boolean isUnderCheck(Player player) {
        // Ваша логика для проверки, находится ли игрок на проверке
        return false; // Пример: заменить на реальную проверку
    }

    private void removeFromCheck(Player player) {
        // Ваша логика для снятия игрока с проверки
    }
}