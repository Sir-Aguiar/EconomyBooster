package br.com.aguiarltda.economybooster.registry;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ListenersRegistry {
  private final static Set<Listener> listeners = new HashSet<>(Arrays.asList());

  public static void registerAllListeners(Plugin plugin) {
    listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
  }
}
