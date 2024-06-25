package br.com.aguiarltda.economybooster.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class PlayerUtils {
  public static Player getPlayerByUniqueId(UUID uniqueId) {
    Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

    for (Player player : onlinePlayers) {
      if (player.getUniqueId().equals(uniqueId)) {
        return player;
      }
    }

    throw new IllegalArgumentException("[EconomyBooster] No online player was found with this identifier");
  }
}
