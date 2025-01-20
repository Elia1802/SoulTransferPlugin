package de.elia;

import de.elia.commands.connect.ConnectCommand;
import de.elia.commands.hub.HubCommand;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommandRegister {

  private static final Map<String, Command> COMMANDS = new HashMap<>();

  static {
    COMMANDS.put("connect", new ConnectCommand());
    COMMANDS.put("hub", new HubCommand());
  }

  /**
   * Register all Commands of the Map
   * @param server Required the Server to register the commands
   */
  public static void registerCommands(@NotNull Server server) {
    COMMANDS.forEach((s, command) -> server.getCommandMap().register(s, command));
  }

}
