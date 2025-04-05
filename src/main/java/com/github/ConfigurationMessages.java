package com.github;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationMessages {

    public final String playerNotFound;
    public final String playerTeleported;
    public final String playerReleased;
    public final String playerNotUnderCheck;
    public final String invalidCommandUsage;
    public final String commandNotAllowed;
    public final String moveNotAllowed;
    public final String dropNotAllowed;
    public final String interactNotAllowed;
    public final String configReloaded;
    public final String noPermission;
    public final String checkTitle;
    public final String checkSubtitle;
    public final String checkMessage;
    public final String messageSentToModerator;
    public final String messageFromPlayer;

    public ConfigurationMessages(FileConfiguration config) {
        playerNotFound = config.getString("messages.player-not-found", "§6Проверка §7» §cИгрок не найден§7.");
        playerTeleported = config.getString("messages.player-teleported", "§6Проверка §7» §fИгрок §6{player} §fтелепортирован в место проверки§7.");
        playerReleased = config.getString("messages.player-released", "§6Проверка §7» §fИгрок §6{player} §fосвобожден от проверки§7.");
        playerNotUnderCheck = config.getString("messages.player-not-under-check", "§6Проверка §7» §cИгрок §6{player} §cне находится на проверки§7.");
        invalidCommandUsage = config.getString("messages.invalid-command-usage", "§6Проверка §7» §cНеверное использование команды§7.");
        commandNotAllowed = config.getString("messages.command-not-allowed", "§cВам не разрешено использовать эту команду во время проверки!");
        moveNotAllowed = config.getString("messages.move-not-allowed", "§cВы не можете двигаться§7, §cнаходясь на проверки!");
        dropNotAllowed = config.getString("messages.drop-not-allowed", "§cВы не можете выбрасывать предметы§7, §cнаходясь на проверки!");
        interactNotAllowed = config.getString("messages.interact-not-allowed", "§cВы не можете взаимодействовать с блоками§7, §cнаходясь на проверки!");
        configReloaded = config.getString("messages.config-reloaded", "§6Проверка §7» §aКонфигурация перезагружена!");
        noPermission = config.getString("messages.no-permission", "§6Проверка §7» §cУ вас нет разрешения на использование этой команды§7.");
        checkTitle = config.getString("messages.check-title", "§cПРОВЕРКА ЧЕРЕЗ ANYDESK");
        checkSubtitle = config.getString("messages.check-subtitle", "§fВсе инструкции находятсяв чате.");
        checkMessage = config.getString("messages.check-message", "§4§lВНИМАНИЕ §7Вы были вызваны на проверку через §4§lAnyDesk\\n§7\\n§7Инструкция: §chttps://reallyworld.me/anydesk\\n§7\\n§cПишите свой код доступа AnyDesk в чат §7У вас есть 5 минут\\n§7Если у вас мут используйте §6/mod сообщение\\n§7\\n§7Признание в использовании читов - §c46 дней бана\\n§7За выход с сервера во время проверки - §c50 дней бана\\n§7\\n§7Важно: Проверяющему запрещено вводить различные\\n§7 команды для ПК (powershell, cmd), а так же совершать\\n§7 действия, которые не касаются проверки (отвязка аккаунта\\n§7 и тому подобное).\\n§7\\n§7Увидели нарушение со стороны модератора, фиксируйте это\\n§7 и сообщайте нам об этом на форум: §chttps://f.reallyworld.me/");
        messageSentToModerator = config.getString("messages.message-sent-to-moderator", "§7Ваше сообщение отправлено модератору.");
        messageFromPlayer = config.getString("messages.message-from-player", "§fСообщение от §6{player}§7: §c{message}");
    }
}