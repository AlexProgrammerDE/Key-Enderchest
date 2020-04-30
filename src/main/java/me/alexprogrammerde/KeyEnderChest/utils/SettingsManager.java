package me.alexprogrammerde.KeyEnderChest.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SettingsManager {
    static FileConfiguration fileConfig;
    static File file;
    static String fileName;

    public static void setup(JavaPlugin plugin, String ymlName) {
        fileName = ymlName;

        file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {}
        }

        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig() {
        return fileConfig;
    }

    public static void saveConfig() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + String.format("Could not save %s!", fileName));
        }
    }

    public static void reloadConfig() {
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }
}