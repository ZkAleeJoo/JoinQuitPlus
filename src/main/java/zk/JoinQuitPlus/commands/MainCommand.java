package zk.JoinQuitPlus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import zk.JoinQuitPlus.JoinQuitPlus;
import zk.JoinQuitPlus.utils.MessageUtils;

public class MainCommand implements CommandExecutor {

    private JoinQuitPlus plugin;

    public MainCommand(JoinQuitPlus plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        if (!(sender instanceof Player)) {
            //MENSAJE QUE SE MANDA CUANDO LOS COMANDOS SON EJECUTADOS DESDE LA CONSOLA
            sender.sendMessage(MessageUtils.getColoredMessage(
                    plugin.getMainConfigManager().getPrefix()+plugin.getMainConfigManager().getMessagenoconsole()));
            return true;

        }

        //VARIABLE DEL JUGADOR
        Player player = (Player) sender;

        //COMANDO -JOINQUITPLUS
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                subCommandReload(sender);

            }else if(args[0].equalsIgnoreCase("get")){
                subCommandGet(sender, args);

            }else {
                help(sender);
            }
        } else {
            //ESTO SE EJECUTA CUANDO SOLO SE PONE -JOINQUITPLUS
            help(sender);
        }

        return false;

    }

    //UN VOID PUBLIC PARA ALMACENAR LOS MENSAJES DE AYUDA
    public void help (CommandSender sender){
        sender.sendMessage(MessageUtils.getColoredMessage("&7► &eJoinQuitPlus &7Help"));
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /joinquitplus reload"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /joinquitplus get version"));
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"));
    }

    //SUBCOMMANDO PARA EL GET
    public void subCommandGet(CommandSender sender, String[] args){
        if(args.length == 1){
            //-JOINQUITPLUS GET
            sender.sendMessage(MessageUtils.getColoredMessage(JoinQuitPlus.prefix+plugin.getMainConfigManager().getMessagversion()));
            return;
        }
        if(args[1].equalsIgnoreCase("version")){
            //-JOINQUITPLUS GET VERSION
            sender.sendMessage(MessageUtils.getColoredMessage(JoinQuitPlus.prefix+"&aLa versión del plugin es "+plugin.getDescription().getVersion()));
        }else{
            sender.sendMessage(MessageUtils.getColoredMessage(JoinQuitPlus.prefix+plugin.getMainConfigManager().getMessagversion()));
        }
    }

    //SUBCOMANDO PARA EL HELP
    public void subCommandReload(CommandSender sender){
        if(!sender.hasPermission("joinquitplus.reload")){
            sender.sendMessage(MessageUtils.getColoredMessage(JoinQuitPlus.prefix+plugin.getMainConfigManager().getNopermissionmessage()));
            return;
        }
        plugin.getMainConfigManager().reloadConfig();
        sender.sendMessage(MessageUtils.getColoredMessage(JoinQuitPlus.prefix+plugin.getMainConfigManager().getReloadmessage()));
    }


}
