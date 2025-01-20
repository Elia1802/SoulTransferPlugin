package de.elia.commands.hub;

import de.elia.Main;
import net.minecraft.network.protocol.common.ClientboundTransferPacket;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static de.elia.api.messages.builder.MessageBuilder.aqua;
import static de.elia.api.messages.builder.MessageBuilder.gray;

public class HubCommand extends Command {

  public HubCommand() {
    this("hub", "Teleport to the lobby", "/hub", List.of("lobby"));
  }

  protected HubCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
    if (sender instanceof Player player) {
      //convert the bukkit player to a craft player
      CraftPlayer craftPlayer = (CraftPlayer) player;
      //Send to the client packets to change the server
      craftPlayer.getHandle().connection.sendPacket(new ClientboundTransferPacket("162.55.253.235", 25565));
      //Message send
      craftPlayer.sendMessage(gray("You are moved to the ").append(aqua("Lobby")).append(gray("!")));
      Main.instance.logger.logInfo(craftPlayer.getName() + "moved to the Lobby (IP: 162.55.253.235 | Port: 25565)");
      return true;
    }else {
      sender.sendMessage("You must be a player to use this command!");
      return false;
    }
  }
}
