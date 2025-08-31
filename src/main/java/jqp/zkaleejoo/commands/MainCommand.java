package jqp.zkaleejoo.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import jqp.zkaleejoo.JoinQuitPlus;
import jqp.zkaleejoo.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    private JoinQuitPlus plugin;

    public MainCommand(JoinQuitPlus plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getMainConfigManager().getPrefix() + plugin.getMainConfigManager().getReloadmessage()));
            plugin.getMainConfigManager().reloadConfig();
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getMainConfigManager().getPrefix() + plugin.getMainConfigManager().getMessagenoconsole()));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("joinquitplus.admin")) {
            player.sendMessage(MessageUtils.getColoredMessage(plugin.getMainConfigManager().getPrefix() + plugin.getMainConfigManager().getNopermissionmessage()));
            return true;
        }

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getMainConfigManager().getPrefix() + plugin.getMainConfigManager().getReloadmessage()));
                plugin.getMainConfigManager().reloadConfig();
                return true;

            } else if (args[0].equalsIgnoreCase("setjoin")) {
                Location loc = player.getLocation();
                plugin.getMainConfigManager().getConfig().set("config.welcome_message.location_tp.x", loc.getX());
                plugin.getMainConfigManager().getConfig().set("config.welcome_message.location_tp.y", loc.getY());
                plugin.getMainConfigManager().getConfig().set("config.welcome_message.location_tp.z", loc.getZ());
                plugin.getMainConfigManager().getConfig().set("config.welcome_message.location_tp.yaw", (float) loc.getYaw());
                plugin.getMainConfigManager().getConfig().set("config.welcome_message.location_tp.pitch", (float) loc.getPitch());

                plugin.getMainConfigManager().getConfig().set("config.welcome_message.name_world", loc.getWorld().getName());
                plugin.getMainConfigManager().saveConfig();
                plugin.getMainConfigManager().reloadConfig();
                sender.sendMessage(MessageUtils.getColoredMessage(JoinQuitPlus.prefix + "&aSpawn point set correctly"));
                return true;

            } else if (args[0].equalsIgnoreCase("get")) {
                subCommandGet(sender, args);
                return true;
            } else {
                help(sender);
                return true;
            }
        } else {
            help(sender);
            return true;
        }
    }

    public void help(CommandSender sender) {
        sender.sendMessage(MessageUtils.getColoredMessage("&8&m-----------------------------"));
        sender.sendMessage(MessageUtils.getColoredMessage("&6&l JoinQuitPlus &f&l» &a&lHelp &7- &f&l(v" + plugin.getDescription().getVersion() + ")"));;
        sender.sendMessage(MessageUtils.getColoredMessage("&8&m-----------------------------"));
        sender.sendMessage(MessageUtils.getColoredMessage("&e✦ &f/joinquitplus reload &7- &fReload the plugin configuration"));
        sender.sendMessage(MessageUtils.getColoredMessage("&e✦ &f/joinquitplus get version &7- &fShow plugin version"));
        sender.sendMessage(MessageUtils.getColoredMessage("&e✦ &f/joinquitplus setjoin &7- &fConfigure the join message"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7&l» &fSupport: &bhttps://discord.gg/hERGW8eVRM &7| &fPermission: &cjoinquitplus.admin"));
        sender.sendMessage(MessageUtils.getColoredMessage("&8&m-----------------------------"));
    }

    public void subCommandGet(CommandSender sender, String[] args) {
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("author")) {
                sender.sendMessage(MessageUtils.getColoredMessage("&fPlugin made by&a" + plugin.getDescription().getAuthors()));

            } else if (args[1].equalsIgnoreCase("version")) {
                sender.sendMessage(MessageUtils.getColoredMessage("&fCurrent version: &a" + plugin.getDescription().getVersion()));

            } else {
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.getMainConfigManager().getPrefix() + plugin.getMainConfigManager().getSubcommandInvalid()));
            }
        } else {
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.getMainConfigManager().getPrefix() + plugin.getMainConfigManager().getSubcommandSpecified()));
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (!sender.hasPermission("joinquitplus.admin")) {
            return completions;
        }

        if (args.length == 1) {
            completions.add("reload");
            completions.add("setjoin");
            completions.add("get");
            return filterCompletions(completions, args[0]);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
            completions.add("author");
            completions.add("version");
            return filterCompletions(completions, args[1]);
        }

        return completions;
    }

    private List<String> filterCompletions(List<String> completions, String input) {
        List<String> filtered = new ArrayList<>();
        for (String completion : completions) {
            if (completion.toLowerCase().startsWith(input.toLowerCase())) {
                filtered.add(completion);
            }
        }
        return filtered;
    }
}