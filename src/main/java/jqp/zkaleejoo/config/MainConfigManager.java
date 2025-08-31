package jqp.zkaleejoo.config;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import jqp.zkaleejoo.JoinQuitPlus;

public class MainConfigManager {
    private CustomConfig configFile;
    private JoinQuitPlus plugin;
    private String prefix;
    private List<String> welcomesmessages;
    private Boolean eneablejoinmessages;
    private Double locationx;
    private Double locationy;
    private Double locationz;
    private Float locationyaw;
    private Float locationpitch;
    private Boolean enabledlocation;
    private String nameworld;
    private String messagenoconsole;
    private String messagversion;
    private String nopermissionmessage;
    private String reloadmessage;
    private Boolean eneablequits;
    private List<String> messagesquits;
    private Boolean enabledplaysound;
    private String namesound;
    private Boolean broadcasteneable;
    private Boolean enabledfirework;
    private String colorfirework;
    private String typefirework;
    private Integer powerfirework;
    private String subcommandInvalid;
    private String subcommandSpecified;

    public MainConfigManager(JoinQuitPlus plugin) {
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml", null, plugin);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();
        prefix = config.getString("prefix");
        welcomesmessages = config.getStringList("config.welcome_message.messages");
        eneablejoinmessages = config.getBoolean("config.welcome_message.enabled");
        locationx = config.getDouble("config.welcome_message.location_tp.x");
        locationy = config.getDouble("config.welcome_message.location_tp.y");
        locationz = config.getDouble("config.welcome_message.location_tp.z");
        locationyaw = (float) config.getDouble("config.welcome_message.location_tp.yaw");
        locationpitch = (float) config.getDouble("config.welcome_message.location_tp.pitch");
        enabledlocation = config.getBoolean("config.welcome_message.location_tp.enabled");
        nameworld = config.getString("config.welcome_message.name_world");
        messagenoconsole = config.getString("console-message");
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
        subcommandInvalid = config.getString("messages.subcommand-invalid");
        subcommandSpecified = config.getString("messages.subcommand-specified");
    }

    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getWelcomesmessages() {
        return welcomesmessages;
    }

    public boolean isEneablejoinmessages() {
        return eneablejoinmessages;
    }

    public Double getLocationx() {
        return locationx;
    }

    public Double getLocationy() {
        return locationy;
    }

    public Double getLocationz() {
        return locationz;
    }

    public Float getLocationyaw() {
        return locationyaw;
    }

    public Float getLocationpitch() {
        return locationpitch;
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

    public FileConfiguration getConfig() {
        return configFile.getConfig();
    }

    public void saveConfig() {
        configFile.saveConfig();
    }

    public String getSubcommandInvalid() {
        return subcommandInvalid;
    }

    public String getSubcommandSpecified() {
        return subcommandSpecified;
    }
}