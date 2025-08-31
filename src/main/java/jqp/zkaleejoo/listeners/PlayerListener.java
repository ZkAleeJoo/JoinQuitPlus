package jqp.zkaleejoo.listeners;

import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import jqp.zkaleejoo.JoinQuitPlus;
import jqp.zkaleejoo.config.MainConfigManager;
import jqp.zkaleejoo.utils.MessageUtils;

public class PlayerListener implements Listener {

    private JoinQuitPlus plugin;

    public PlayerListener(JoinQuitPlus plugin) {
        this.plugin = plugin;
    }

    public void launchFirework(Location loc) {
        Firework firework = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        String colorString = plugin.getMainConfigManager().getColorfirework();
        String typeString = plugin.getMainConfigManager().getTypefirework();
        Integer power = plugin.getMainConfigManager().getPowerfirework();
        Color color = getColorFromString(colorString);
        FireworkEffect.Type type = FireworkEffect.Type.valueOf(typeString.toUpperCase());
        FireworkEffect effect = FireworkEffect.builder().withColor(color).with(type).trail(true).build();
        meta.addEffect(effect);
        meta.setPower(power);
        firework.setFireworkMeta(meta);
    }

    private Color getColorFromString(String colorString) {
        switch (colorString.toUpperCase()) {
            case "AQUA": return Color.AQUA;
            case "BLACK": return Color.BLACK;
            case "BLUE": return Color.BLUE;
            case "FUCHSIA": return Color.FUCHSIA;
            case "GRAY": return Color.GRAY;
            case "GREEN": return Color.GREEN;
            case "LIME": return Color.LIME;
            case "MAROON": return Color.MAROON;
            case "NAVY": return Color.NAVY;
            case "OLIVE": return Color.OLIVE;
            case "ORANGE": return Color.ORANGE;
            case "PURPLE": return Color.PURPLE;
            case "RED": return Color.RED;
            case "SILVER": return Color.SILVER;
            case "TEAL": return Color.TEAL;
            case "WHITE": return Color.WHITE;
            case "YELLOW": return Color.YELLOW;
            default: return Color.WHITE;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        MainConfigManager mainConfigManager = plugin.getMainConfigManager();

        // Mensajes de bienvenida
        if (mainConfigManager.isEneablejoinmessages()) {
            List<String> message = mainConfigManager.getWelcomesmessages();
            for (String m : message) {
                String finalMessage = m.replace("%player%", player.getName());
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    finalMessage = PlaceholderAPI.setPlaceholders(player, finalMessage);
                }
                finalMessage = MessageUtils.getColoredMessage(finalMessage);
                if (mainConfigManager.isBroadcasteneable()) {
                    Bukkit.broadcastMessage(finalMessage);
                } else {
                    player.sendMessage(finalMessage);
                }
            }
        }

        // Teleportación
        if (mainConfigManager.isEnabledlocation()) {
            try {
                World world = Bukkit.getWorld(mainConfigManager.getNameworld());
                if (world == null) {
                    plugin.getLogger().warning("World '" + mainConfigManager.getNameworld() + "' Not found. Using player world.");
                    world = player.getWorld();
                }

                Location location = new Location(
                        world,
                        mainConfigManager.getLocationx(),
                        mainConfigManager.getLocationy(),
                        mainConfigManager.getLocationz(),
                        mainConfigManager.getLocationyaw(),
                        mainConfigManager.getLocationpitch()
                );

                player.teleport(location);
                plugin.getLogger().info("Teleporting to " + player.getName() + " a: " + location.toString());
            } catch (Exception e) {
                plugin.getLogger().severe("Error teleporting player: " + e.getMessage());
            }
        }

        // Sonido
        if (mainConfigManager.isEnabledplaysound()) {
            try {
                String soundName = plugin.getMainConfigManager().getNamesound();
                Sound sound = Sound.valueOf(soundName);
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            } catch (Exception e) {
                plugin.getLogger().warning("Error playing sound: " + e.getMessage());
            }
        }

        // Fuegos artificiales
        if (mainConfigManager.isEnabledfirework()) {
            try {
                launchFirework(player.getLocation());
            } catch (Exception e) {
                plugin.getLogger().warning("Error launching fireworks: " + e.getMessage());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        MainConfigManager mainConfigManager = plugin.getMainConfigManager();

        if (mainConfigManager.isEneablequits()) {
            List<String> message = mainConfigManager.getMessagesquits();
            for (String m : message) {
                String finalMessage = m.replace("%player%", player.getName());
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    finalMessage = PlaceholderAPI.setPlaceholders(player, finalMessage);
                }
                finalMessage = MessageUtils.getColoredMessage(finalMessage);
                if (mainConfigManager.isBroadcasteneable()) {
                    Bukkit.broadcastMessage(finalMessage);
                } else {
                    player.sendMessage(finalMessage);
                }
            }
        }
    }
}