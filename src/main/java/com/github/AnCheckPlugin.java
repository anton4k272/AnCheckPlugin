package com.github;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AnCheckPlugin extends JavaPlugin {

    private CheckCommandExecutor commandExecutor;
    private Location checkLocation;
    private List<String> allowedCommands;
    private String releaseCommand;
    private ConfigurationMessages messages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigValues();
        getLogger().info("AnCheckPlugin has been enabled.");
        commandExecutor = new CheckCommandExecutor(this);
        this.getCommand("check").setExecutor(commandExecutor);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerCheckListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("AnCheckPlugin has been disabled.");
    }

    public Location getCheckLocation() {
        return checkLocation;
    }

    public List<String> getAllowedCommands() {
        return allowedCommands;
    }

    public String getReleaseCommand() {
        return releaseCommand;
    }

    public ConfigurationMessages getMessages() {
        return messages;
    }

    public CheckCommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public void reloadConfigValues() {
        reloadConfig();
        loadConfigValues();
    }

    private void loadConfigValues() {
        FileConfiguration config = getConfig();
        double x = config.getDouble("check-location.x");
        double y = config.getDouble("check-location.y");
        double z = config.getDouble("check-location.z");
        String worldName = config.getString("check-location.world");
        if (worldName != null && getServer().getWorld(worldName) != null) {
            checkLocation = new Location(getServer().getWorld(worldName), x, y, z);
        } else {
            checkLocation = null;
            getLogger().warning("Проверьте, что местоположение указано правильно в config.yml");
        }
        allowedCommands = config.getStringList("allowed-commands");
        releaseCommand = config.getString("release-command");
        messages = new ConfigurationMessages(config);
    }

    public void startCheckMessageTask(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.isOnline() && getCommandExecutor().isPlayerUnderCheck(player.getName())) {
                    player.sendTitle(messages.checkTitle, messages.checkSubtitle, 10, 70, 20);
                    player.sendMessage(messages.checkMessage);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(this, 0, 100); // 100 ticks = 5 seconds
    }
}