package br.com.aguiarltda.economybooster.events;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;


public class FireworkSneak implements Listener {
  @EventHandler
  public void onSneak(PlayerToggleSneakEvent event) {
    if (!event.isSneaking()) {
      return;
    }

    Player player = event.getPlayer();

    Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
    FireworkMeta fireworkMeta = firework.getFireworkMeta();

    fireworkMeta.addEffect(FireworkEffect.builder()
            .withColor(Color.RED).withColor(Color.ORANGE)
            .with(FireworkEffect.Type.BALL_LARGE)
            .withTrail()
            .build()
    );

    fireworkMeta.setPower(3);
    firework.setFireworkMeta(fireworkMeta);
  }
}
