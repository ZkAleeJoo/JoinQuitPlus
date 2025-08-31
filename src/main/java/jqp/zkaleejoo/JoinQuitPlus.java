package jqp.zkaleejoo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import jqp.zkaleejoo.commands.MainCommand;
import jqp.zkaleejoo.config.MainConfigManager;
import jqp.zkaleejoo.listeners.PlayerListener;
import jqp.zkaleejoo.utils.MessageUtils;

public class JoinQuitPlus extends JavaPlugin {
    public static String prefix = "&8[&9JoinQuitPlus&8] ";
    private String version = getDescription().getVersion();
    private MainConfigManager mainConfigManager;

    public void onEnable() {
        registerCommands();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);


        Bukkit.getConsoleSender().sendMessage("     ____.      .__        ________        .__  __    __________.__                \n"+
                "    |    | ____ |__| ____  \\_____  \\  __ __|__|/  |_  \\______   \\  |  __ __  ______\n"+
                "    |    |/  _ \\|  |/    \\  /  / \\  \\|  |  \\  \\   __\\  |     ___/  | |  |  \\/  ___/\n"+
                "/\\__|    (  <_> )  |   |  \\/   \\_/.  \\  |  /  ||  |    |    |   |  |_|  |  /\\___ \\ \n"+
                "\\________|\\____/|__|___|  /\\_____\\ \\_/____/|__||__|    |____|   |____/____//____  >\n"+
                "                        \\/        \\__>                                          \\/ \n");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fThe plugin was activated successfully, Version: " + this.version));


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&8[&9JoinQuitPlus&8] &aPlaceholderAPI found, placeholders enabled!"));
        } else {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&8[&9JoinQuitPlus&8] PlaceholderAPI NOT found, no placeholders will be replaced!"));
        }
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fThe plugin was successfully deactivated, Version: " + this.version));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fThank you for using my plugin!"));
    }

    public void registerCommands() {
        MainCommand mainCommand = new MainCommand(this);
        this.getCommand("joinquitplus").setExecutor(new MainCommand(this));
        this.getCommand("joinquitplus").setTabCompleter(mainCommand);
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }
}

