package de.elia;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static de.elia.PluginInfo.API_NAME;
import static de.elia.PluginInfo.API_VERSION;
import static de.elia.PluginInfo.AUTHOR;
import static de.elia.PluginInfo.NAME;
import static de.elia.PluginInfo.VERSION;

public class Bootstrapper implements PluginBootstrap {

  @Override
  public void bootstrap(@NotNull BootstrapContext bootstrapContext) {
    var logger = bootstrapContext.getLogger();
    logger.info("Boot " + NAME + "...");
    //logger.warn("This is the alpha build of " + NAME + "!");
  }

  @Override
  @NotNull
  public JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
    var logger = context.getLogger();
    logger.info("Information about this Plugin");
    logger.info("Name: " + NAME);
    logger.info("API-Name: " + API_NAME);
    logger.info("API-Version: " + API_VERSION);
    logger.info("Version: " + VERSION);
    logger.info("Authors: " + AUTHOR);
    logger.info("Booting finished!");
    logger.info("Load Main...");
    return new Main();
  }
}
