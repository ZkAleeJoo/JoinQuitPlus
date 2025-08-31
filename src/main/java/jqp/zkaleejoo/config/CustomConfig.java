package jqp.zkaleejoo.config;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import jqp.zkaleejoo.JoinQuitPlus;

public class CustomConfig {
    private JoinQuitPlus plugin;
    private String fileName;
    private FileConfiguration fileConfiguration = null;
    private File file = null;
    private String folderName;
    private boolean newFile;

    public CustomConfig(String fileName, String folderName, JoinQuitPlus plugin) {
        this.fileName = fileName;
        this.folderName = folderName;
        this.plugin = plugin;
        this.newFile = false;
    }

    public String getPath() {
        return this.fileName;
    }

    public void registerConfig() {
        this.file = this.folderName != null ? new File((this.plugin.getDataFolder()) + File.separator + this.folderName, this.fileName) : new File(this.plugin.getDataFolder(), this.fileName);
        if (!this.file.exists()) {
            if (this.newFile) {
                try {
                    this.file.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (this.folderName != null) {
                this.plugin.saveResource(this.folderName + File.separator + this.fileName, false);
            } else {
                this.plugin.saveResource(this.fileName, false);
            }
        }
        this.fileConfiguration = new YamlConfiguration();
        try {
            this.fileConfiguration.load(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            this.fileConfiguration.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        if (this.fileConfiguration == null) {
            this.reloadConfig();
        }
        return this.fileConfiguration;
    }

    public boolean reloadConfig() {
        if (this.fileConfiguration == null) {
            this.file = this.folderName != null ? new File((this.plugin.getDataFolder()) + File.separator + this.folderName, this.fileName) : new File(this.plugin.getDataFolder(), this.fileName);
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        if (this.file != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(this.file);
            this.fileConfiguration.setDefaults(defConfig);
        }
        return true;
    }
}

