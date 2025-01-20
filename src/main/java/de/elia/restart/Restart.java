package de.elia.restart;

import de.elia.Main;
import de.elia.PluginInfo;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.jetbrains.annotations.NotNull;

public class Restart {

  public static void cancelRestartCommand(@NotNull Server s) {
    CraftServer server = (CraftServer) s;
    server.getCommandMap().getKnownCommands().remove("restart");
    Main.instance.logger.logError("RestartCommand cancelled of " + PluginInfo.NAME);
  }

}
