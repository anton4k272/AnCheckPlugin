package com.github;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationMessages {

    public final String playerNotFound;
    public final String playerTeleported;
    public final String playerReleased;
    public final String playerNotUnderCheck;
    public final String invalidCommandUsage;
    public final String commandNotAllowed;
    public final String configReloaded;
    public final String noPermission;
    public final String checkTitle;
    public final String checkSubtitle;
    public final String checkMessage;
    public final String messageSentToModerator;
    public final String messageFromPlayer;
    public final String playerLeftDuringCheck;

    public ConfigurationMessages(FileConfiguration config) {
        playerNotFound = config.getString("messages.player-not-found");
        playerTeleported = config.getString("messages.player-teleported");
        playerReleased = config.getString("messages.player-released");
        playerNotUnderCheck = config.getString("messages.player-not-under-check");
        invalidCommandUsage = config.getString("messages.invalid-command-usage");
        commandNotAllowed = config.getString("messages.command-not-allowed");
        configReloaded = config.getString("messages.config-reloaded");
        noPermission = config.getString("messages.no-permission");
        checkTitle = config.getString("messages.check-title");
        checkSubtitle = config.getString("messages.check-subtitle");
        checkMessage = config.getString("messages.check-message");
        messageSentToModerator = config.getString("messages.message-sent-to-moderator");
        messageFromPlayer = config.getString("messages.message-from-player");
        playerLeftDuringCheck = config.getString("messages.player-left-during-check");
    }
}