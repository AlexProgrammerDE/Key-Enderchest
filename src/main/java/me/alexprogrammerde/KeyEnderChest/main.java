package me.alexprogrammerde.KeyEnderChest;

import me.alexprogrammerde.KeyEnderChest.commands.KeyCommand;
import me.alexprogrammerde.KeyEnderChest.listeners.ChestInvListener;
import me.alexprogrammerde.KeyEnderChest.storage.KeyUUIDStorage;
import me.alexprogrammerde.KeyEnderChest.utils.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private int TaskID;
    JavaPlugin plugin = this;
    private ConsoleCommandSender console = plugin.getServer().getConsoleSender();
    FileConfiguration configFile;

    @Override
    public void onEnable() {
        console.sendMessage("§1§l[KeyEnderChest] Registering Listeners");
        getServer().getPluginManager().registerEvents(new ChestInvListener(), this);

        //////////////////////////////////////////////////////////////////

        console.sendMessage("§1§l[KeyEnderChest] Loading Config");
        SettingsManager.setup(this, "KeyData.yml" );
        SettingsManager.getConfig().addDefault("Default", KeyUUIDStorage.getList());
        SettingsManager.getConfig().options().copyDefaults(true);
        SettingsManager.saveConfig();

        configFile = SettingsManager.getConfig();

        //////////////////////////////////////////////////////////////////

        console.sendMessage("§1§l[KeyEnderChest] Registering Commands");
        this.getCommand("key").setExecutor(new KeyCommand());

        //////////////////////////////////////////////////////////////////

        /* console.sendMessage("§1§l[KeyEnderChest] Sheduling saving");
        TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

                    public void run() {
                        configFile.set("Default", KeyUUIDStorage.getList());
                    }

                },
        0, 20); */
    }

    @Override
    public void onDisable() {
        console.sendMessage("§1§l[KeyEnderChest] Disabling the plugin");
    }

}
