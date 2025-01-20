package de.elia;

import de.elia.api.logging.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  public final PluginLogger logger = new PluginLogger("SoulTransferPlugin-Logger");
  public static Main instance;

  @Override
  public void onEnable() {
    instance = this;
    this.logger.logInfo("Registering commands");
    CommandRegister.registerCommands(this.getServer());
    this.logger.logInfo("Commands registered");
    this.logger.logInfo("Main loaded");
  }

  @Override
  public void onDisable() {
    this.logger.logInfo("Main unloaded");
  }

}
