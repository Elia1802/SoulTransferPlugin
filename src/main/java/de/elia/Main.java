package de.elia;

import de.elia.api.logging.PluginLogger;
import de.elia.register.CommandRegister;
import de.elia.restart.Restart;
import net.minecraft.network.protocol.common.ClientboundTransferPacket;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  public final PluginLogger logger = new PluginLogger("SoulTransferPlugin-Logger");
  public static Main instance;

  @Override
  public void onEnable() {
    instance = this;
    this.logger.logInfo("Registering commands...");
    CommandRegister.registerCommands(this.getServer());
    this.logger.logInfo("Commands registered!");
    this.logger.logInfo("Unregistering commands...");
    Restart.cancelRestartCommand(this.getServer());
    this.logger.logInfo("Commands unregistered!");
    this.logger.logInfo("Main loaded!");
  }

  @Override
  public void onDisable() {
    this.logger.logInfo("Send all players to Lobby...");
    Bukkit.getOnlinePlayers().forEach(bukkitPlayer -> {
      CraftPlayer player = (CraftPlayer) bukkitPlayer;
      player.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25565));
      Main.instance.logger.logInfo(player.getName() + " moved to the Lobby (IP: 162.55.253.235 | Port: 25565)");
      Main.this.logger.logInfo(player.getName() + " moved!");
    });
    this.logger.logInfo("All players has been sent to the Lobby!");
    this.logger.logInfo("Main unloaded!");
  }

}
