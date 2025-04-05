package com.github;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerCheckListener implements Listener {

    private AnCheckPlugin plugin;

    public PlayerCheckListener(AnCheckPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String playerName = event.getPlayer().getName();
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getMessages().moveNotAllowed);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        String playerName = event.getPlayer().getName();
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getMessages().dropNotAllowed);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();
        if (plugin.getCommandExecutor().isPlayerUnderCheck(playerName)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(plugin.getMessages().interactNotAllowed);
        }
    }
}