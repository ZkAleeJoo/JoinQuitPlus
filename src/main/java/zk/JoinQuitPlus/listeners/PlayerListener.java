package zk.JoinQuitPlus.listeners;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import zk.JoinQuitPlus.JoinQuitPlus;
import zk.JoinQuitPlus.config.MainConfigManager;
import zk.JoinQuitPlus.utils.MessageUtils;

import java.util.List;

public class PlayerListener implements Listener {


    private JoinQuitPlus plugin;

    public PlayerListener(JoinQuitPlus plugin){
        this.plugin = plugin;
    }

    public void launchFirework(Location loc) {

        Firework firework = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();

        String colorString = plugin.getMainConfigManager().getColorfirework();
        String typeString = plugin.getMainConfigManager().getTypefirework();
        int power = plugin.getMainConfigManager().getPowerfirework();

        Color color = getColorFromString(colorString);
        FireworkEffect.Type type = FireworkEffect.Type.valueOf(typeString.toUpperCase());

        FireworkEffect effect = FireworkEffect.builder()
                .withColor(color)
                .with(type)
                .trail(true)
                .build();

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
            default:
                return Color.WHITE;
        }
    }

    //EVENTO CUANDO EL JUGADOR ENTRA
    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        event.setJoinMessage(null);

        MainConfigManager mainConfigManager = plugin.getMainConfigManager();
        //MENSAJE QUE SE MANDA AL JUGADOR
        if(mainConfigManager.isEneablejoinmessages()){
            List<String> message = mainConfigManager.getWelcomesmessages();
            for(String m : message){

                if(mainConfigManager.isBroadcasteneable()){
                    Bukkit.broadcastMessage(MessageUtils.getColoredMessage(m.replace("%player%",player.getName())));
                }else{
                    player.sendMessage(MessageUtils.getColoredMessage(m.replace("%player%",player.getName())));
                }

            }
        }

        //EL JUGADOR SE HACE TP A UN LUGAR EN ESPECIFICO
        if(mainConfigManager.isEnabledlocation()) {
            Location location = new Location(Bukkit.getWorld(
                    plugin.getMainConfigManager().getNameworld()),
                    plugin.getMainConfigManager().getLocationx(),
                    plugin.getMainConfigManager().getLocationy(),
                    plugin.getMainConfigManager().getLocationz(),
                    plugin.getMainConfigManager().getLocationyaw(),
                    plugin.getMainConfigManager().getLocationpitch());

            player.teleport(location);
        }

        //SE REPRODUCE UN SONIDO AL JUGADOR CUANDO ENTRA
        if(mainConfigManager.isEnabledplaysound()){
            String soundName = plugin.getMainConfigManager().getNamesound();
            Sound sound = Sound.valueOf(soundName);

            player.playSound(player.getLocation(), sound, 1,1);
        }

        //SE ENVIA UN FIREWORK AL JUGADOR CUANDO ENTRA AL SERVIDOR
        if(mainConfigManager.isEnabledfirework()){
            launchFirework(player.getLocation());
        }

    }

    //EVENTO CUANDO EL JUGADOR SE VA
    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();
        event.setQuitMessage(null);

        MainConfigManager mainConfigManager = plugin.getMainConfigManager();
        if(mainConfigManager.isEneablequits()){
            List<String> message = mainConfigManager.getMessagesquits();
            for(String m : message){

                if(mainConfigManager.isBroadcasteneable()){
                    Bukkit.broadcastMessage(MessageUtils.getColoredMessage(m.replace("%player%",player.getName())));
                }else{
                    player.sendMessage(MessageUtils.getColoredMessage(m.replace("%player%",player.getName())));
                }

            }
        }

    }


}
