package com.github;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class PlayerCommandPreprocessListener implements Listener {

    private AnCheckPlugin plugin;

    public PlayerCommandPreprocessListener(AnCheckPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String playerName = event.getPlayer().getName();
        String command = event.getMessage().split(" ")[0].substring(1);

        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            List<String> allowedCommands = plugin.getAllowedCommands();
            if (!allowedCommands.contains(command)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(plugin.getMessages().commandNotAllowed);
            }
        }
    }
}