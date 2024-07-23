package br.com.aguiarltda.economybooster.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtils {
  public static Player getPlayerByUniqueId(UUID uniqueId) {
    return Bukkit.getServer().getOnlinePlayers().stream()
            .filter((player) -> player.getUniqueId().equals(uniqueId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[EconomyBooster] No online player was found with this identifier"));
  }
}
