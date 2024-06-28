package br.com.aguiarltda.economybooster.events;

import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProjectilesLauncher implements Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();

    if (!event.hasItem()) {
      return;
    }

    if (event.getItem().getType().equals(Material.DIAMOND_AXE)) {
      player.launchProjectile(Snowball.class);
    } else if (event.getItem().getType().equals(Material.IRON_AXE)) {
      player.launchProjectile(Egg.class);
    }

  }
}
