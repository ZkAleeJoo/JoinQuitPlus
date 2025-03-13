package zk.JoinQuitPlus.config;

import org.bukkit.configuration.file.FileConfiguration;
import zk.JoinQuitPlus.JoinQuitPlus;

import java.util.List;

public class MainConfigManager {

    private CustomConfig configFile;
    private JoinQuitPlus plugin;

    private String prefix;
    private List<String> welcomesmessages;
    private boolean eneablejoinmessages;

    private int locationx;
    private int locationy;
    private int locationz;
    private int locationyaw;
    private int locationpitch;

    private boolean enabledlocation;
    private String nameworld;


    private String messagenoconsole;
    private String messagversion;
    private String nopermissionmessage;
    private String reloadmessage;

    private boolean eneablequits;
    private List<String> messagesquits;

    private boolean enabledplaysound;
    private String namesound;

    private boolean broadcasteneable;

    private boolean enabledfirework;
    private String colorfirework;
    private String typefirework;
    private int powerfirework;


    public MainConfigManager(JoinQuitPlus plugin){
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml", null,plugin);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig(){
        FileConfiguration config = configFile.getConfig();
        prefix = config.getString("prefix");
        welcomesmessages = config.getStringList("config.welcome_message.messages");
        eneablejoinmessages = config.getBoolean("config.welcome_message.enabled");
        locationx = config.getInt("config.welcome_message.location_tp.x");
        locationy = config.getInt("config.welcome_message.location_tp.y");
        locationz = config.getInt("config.welcome_message.location_tp.z");
        locationyaw = config.getInt("config.welcome_message.location_tp.yaw");
        locationpitch = config.getInt("config.welcome_message.location_tp.pitch");
        enabledlocation = config.getBoolean("config.welcome_message.location_tp.enabled");
        nameworld = config.getString("config.welcome_message.name_world");
        messagenoconsole = config.getString("console-message");
        messagversion = config.getString("message-version");
        nopermissionmessage = config.getString("no-permission");
        reloadmessage = config.getString("reload-message");
        eneablequits = config.getBoolean("config.farewell_message.enabled");
        messagesquits = config.getStringList("config.farewell_message.messages");
        enabledplaysound = config.getBoolean("config.play_sound.enabled");
        namesound = config.getString("config.play_sound.sound");
        broadcasteneable = config.getBoolean("config.broadcast_message");
        enabledfirework = config.getBoolean("config.firework.enabled");
        colorfirework = config.getString("config.firework.color");
        typefirework = config.getString("config.firework.type");
        powerfirework = config.getInt("config.firework.power");
    }

    //RECARGAR LA CONFIG
    public void reloadConfig(){
        configFile.reloadConfig();
        loadConfig();
    }

    //CARGA DE CONFIG
    public String getPrefix() {
        return prefix;
    }

    public List<String> getWelcomesmessages() {
        return welcomesmessages;
    }

    public boolean isEneablejoinmessages() {
        return eneablejoinmessages;
    }

    public int getLocationpitch() {
        return locationpitch;
    }

    public int getLocationyaw() {
        return locationyaw;
    }

    public int getLocationz() {
        return locationz;
    }

    public int getLocationy() {
        return locationy;
    }

    public int getLocationx() {
        return locationx;
    }

    public String getNameworld() {
        return nameworld;
    }

    public boolean isEnabledlocation() {
        return enabledlocation;
    }

    public String getReloadmessage() {
        return reloadmessage;
    }

    public String getNopermissionmessage() {
        return nopermissionmessage;
    }

    public String getMessagversion() {
        return messagversion;
    }

    public String getMessagenoconsole() {
        return messagenoconsole;
    }

    public List<String> getMessagesquits() {
        return messagesquits;
    }

    public boolean isEneablequits() {
        return eneablequits;
    }

    public String getNamesound() {
        return namesound;
    }

    public boolean isEnabledplaysound() {
        return enabledplaysound;
    }

    public boolean isBroadcasteneable() {
        return broadcasteneable;
    }

    public int getPowerfirework() {
        return powerfirework;
    }

    public String getTypefirework() {
        return typefirework;
    }

    public String getColorfirework() {
        return colorfirework;
    }

    public boolean isEnabledfirework() {
        return enabledfirework;
    }
}
