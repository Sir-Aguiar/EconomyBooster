package br.com.aguiarltda.economybooster.events;

import br.com.aguiarltda.economybooster.EconomyBooster;
import br.com.aguiarltda.economybooster.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

public class EntityTame implements Listener {

  @EventHandler
  public void onEntityTame(EntityTameEvent event) {
    Player entityOwner = PlayerUtils.getPlayerByUniqueId(event.getOwner().getUniqueId());
    LivingEntity tamedEntity = event.getEntity();

    double awardAmount = Math.random() * 30;
    String awardMessage = String.format("%sYou've been awarded %s $%.2f %s by taming %s", ChatColor.GOLD, ChatColor.GREEN, awardAmount, ChatColor.GOLD, tamedEntity.getName());
    EconomyBooster.getEconomy().depositPlayer(entityOwner, awardAmount);
    Bukkit.broadcastMessage(awardMessage);

    tamedEntity.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, tamedEntity.getLocation().add(0.0, 4.0, 0.0), 50);
    tamedEntity.getWorld().playSound(tamedEntity.getLocation().add(0.0, 0.0, 0.0), Sound.ITEM_TOTEM_USE, 1.0f, 2.0f);
  }
}
