package de.elia.commands.connect;

import de.elia.Main;
import net.minecraft.network.protocol.common.ClientboundTransferPacket;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static de.elia.api.messages.builder.MessageBuilder.aqua;
import static de.elia.api.messages.builder.MessageBuilder.gray;
import static de.elia.api.messages.builder.MessageBuilder.red;

public class ConnectCommand extends Command {

  public ConnectCommand() {
    this("connect", "Connect a player to an server", "/connect [PLAYER] [SERVER)", List.of("con", "server"));
  }

  protected ConnectCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
    if (sender instanceof Player player) {
      if (args.length == 2) {
        var target = Bukkit.getPlayer(args[0]);
        if (target == null){
          player.sendMessage(red("This Player does not exist!"));
          return false;
        }
        if (args[1].equalsIgnoreCase("event")) {
          if (target.hasPermission("soultransfer.connect.event")){
            //convert the bukkit target player to a craft player
            CraftPlayer craftTarget = (CraftPlayer) target;
            //Send to the client packets to change the server
            craftTarget.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25563));
            //Message send
            craftTarget.sendMessage(gray("You are moved to the ").append(aqua("Event Server")).append(gray("!")));
            Main.instance.logger.logInfo(craftTarget.getName() + "moved to the Event Server (IP: 162.55.253.235 | Port: 25563)");
            return true;
          }else {
            player.sendMessage(red("You don't have the permission to connect to this server!"));
            return false;
          }
        }else if (args[1].equalsIgnoreCase("soul")) {
          //convert the bukkit target player to a craft player
          CraftPlayer craftTarget = (CraftPlayer) target;
          //Send to the client packets to change the server
          craftTarget.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25564));
          //Message send
          craftTarget.sendMessage(gray("You are moved to the ").append(aqua("Soul Server")).append(gray("!")));
          Main.instance.logger.logInfo(craftTarget.getName() + "moved to the Soul Server (IP: 162.55.253.235 | Port: 25564)");
          return true;
        }else if (args[1].equalsIgnoreCase("build")) {
          if (target.hasPermission("soultransfer.connect.build")){
            //convert the bukkit target player to a craft player
            CraftPlayer craftTarget = (CraftPlayer) target;
            //Send to the client packets to change the server
            craftTarget.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25562));
            //Message send
            craftTarget.sendMessage(gray("You are moved to the ").append(aqua("Build Server")).append(gray("!")));
            Main.instance.logger.logInfo(craftTarget.getName() + "moved to the Build Server (IP: 162.55.253.235 | Port: 25562)");
            return true;
          }else {
            player.sendMessage(red("You don't have the permission to connect to this server!"));
            return false;
          }
        }else if (args[1].equalsIgnoreCase("test")) {
          if (target.hasPermission("soultransfer.connect.test")){
            //convert the bukkit target player to a craft player
            CraftPlayer craftTarget = (CraftPlayer) target;
            //Send to the client packets to change the server
            craftTarget.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25561));
            //Message send
            craftTarget.sendMessage(gray("You are moved to the ").append(aqua("Test Server")).append(gray("!")));
            Main.instance.logger.logInfo(craftTarget.getName() + "moved to the Test Server (IP: 162.55.253.235 | Port: 25561)");
            return true;
          }else {
            player.sendMessage(red("You don't have the permission to connect to this server!"));
            return false;
          }
        }else if (args[1].equalsIgnoreCase("lobby")) {
          //convert the bukkit target player to a craft player
          CraftPlayer craftTarget = (CraftPlayer) target;
          //Send to the client packets to change the server
          craftTarget.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25565));
          //Message send
          craftTarget.sendMessage(gray("You are moved to the ").append(aqua("Lobby")).append(gray("!")));
          Main.instance.logger.logInfo(craftTarget.getName() + "moved to the Lobby (IP: 162.55.253.235 | Port: 25565)");
          return true;
        }else {
          player.sendMessage(red("This Server does not exist!"));
          return false;
        }
      }else {
        player.sendMessage(red("Please use /connect [PLAYER] [SERVER)"));
        return false;
      }
    }else {
      sender.sendMessage(red("You must be a player to use this command!"));
      return false;
    }
  }

  @Override
  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
    ArrayList<String> tab1 = new ArrayList<>();
    ArrayList<String> tab2 = new ArrayList<>();
    if (args.length == 1) {
      Bukkit.getOnlinePlayers().forEach(player -> tab1.add(player.getName()));
      return tab1;
    }
    if (args.length == 2) {
      tab2.add("event");
      tab2.add("soul");
      tab2.add("build");
      tab2.add("test");
      tab2.add("lobby");
      return tab2;
    }
    return null;
  }
}


