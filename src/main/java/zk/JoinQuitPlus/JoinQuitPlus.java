package zk.JoinQuitPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import zk.JoinQuitPlus.commands.MainCommand;
import zk.JoinQuitPlus.config.MainConfigManager;
import zk.JoinQuitPlus.listeners.PlayerListener;

public class JoinQuitPlus extends JavaPlugin {


    public static String prefix = "&8[&9JoinQuitPlus&8] ";

    private String version = getDescription().getVersion();
    private MainConfigManager mainConfigManager;

    @Override
    public void onEnable() {

        registerCommands();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);


        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &8=============================="));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fJoinQuitPlus"));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fHecho por ZkAleeJoo"));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &8=============================="));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fEl plugin cargo correctamente, Versión: "+version));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fGracias por usar mi plugin"));

    }


    //SERVER SE APAGA
    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fEl plugin se desactivo correctamente, Versión: "+version));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&8[&9JoinQuitPlus&8] &fGracias por usar mi plugin!"));
    }


    // ----------------------------------------------------------------------
    //   REGISTROS DE COMANDOS Y EVENTOS
    // ----------------------------------------------------------------------
    public void registerCommands(){
        this.getCommand("joinquitplus").setExecutor(new MainCommand(this));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

}
