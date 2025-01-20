package de.elia;

import de.elia.PluginInfo.SoulLibrary;
import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.JarLibrary;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static de.elia.PluginInfo.NAME;
import static de.elia.PluginInfo.VERSION;

public class Loader implements PluginLoader {

  @Override
  public void classloader(@NotNull PluginClasspathBuilder pluginClasspathBuilder) {
    var logger = pluginClasspathBuilder.getContext().getLogger();
    logger.info("Loading libraries for " + NAME + " " + VERSION);
    String soulLibrary = SoulLibrary.NAME + "-" + SoulLibrary.VERSION + ".jar";
    logger.info("Loading library {}", soulLibrary);
    String projectPath = System.clearProperty("user.dir");
    String libraryPath = projectPath + "/plugins/";
    pluginClasspathBuilder.addLibrary(new JarLibrary(Path.of(libraryPath + soulLibrary)));
    logger.info("Loading Log4J-API 3.0.0-beta2 ...");
    MavenLibraryResolver log4jAPI = new MavenLibraryResolver();
    log4jAPI.addDependency(new Dependency(new DefaultArtifact("org.apache.logging.log4j:log4j-api:3.0.0-beta2"), null));
    log4jAPI.addRepository(new RemoteRepository.Builder("log4j-api", "default", "https://repo.papermc.io/repository/maven-public/").build());
    pluginClasspathBuilder.addLibrary(log4jAPI);
    logger.info("Log4J-API 3.0.0-beta2 loaded!");
    logger.info("Loading Log4J-Core 3.0.0-beta2 ...");
    MavenLibraryResolver log4jCore = new MavenLibraryResolver();
    log4jCore.addDependency(new Dependency(new DefaultArtifact("org.apache.logging.log4j:log4j-core:3.0.0-beta2"), null));
    log4jCore.addRepository(new RemoteRepository.Builder("log4j-core", "default", "https://repo.papermc.io/repository/maven-public/").build());
    pluginClasspathBuilder.addLibrary(log4jCore);
    logger.info("Log4J-Core 3.0.0-beta2 loaded!");
    logger.info("Loading libraries done!");
  }
}
