package br.com.aguiarltda.economybooster.commands;

import br.com.aguiarltda.economybooster.EconomyBooster;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
  private final double HEAL_COST = 10;

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command is for players only");
      return false;
    }

    Player player = (Player) sender;
    Location playerLocation = player.getLocation();
    double playerBalance = EconomyBooster.getEconomy().getBalance(player);
    double playerHealth = player.getHealth();
    double playerMaxHealing = playerBalance / this.HEAL_COST;
    double playerNeedToHeal = 20 - playerHealth;
    double healingQuantity;
    double healingCost;

    if (playerBalance < this.HEAL_COST) {
      player.sendMessage(String.format("%sYou have not enough money to heal %s($%.1f per HP)", ChatColor.RED, ChatColor.GREEN, this.HEAL_COST));
      return false;
    }

    if (playerMaxHealing >= 20) {
      healingQuantity = playerNeedToHeal;
    } else if (playerNeedToHeal > playerMaxHealing) {
      healingQuantity = (int) playerMaxHealing;
    } else {
      healingQuantity = playerNeedToHeal;
    }

    healingCost = this.HEAL_COST * healingQuantity;

    player.setHealth(playerHealth + healingQuantity);
    player.getWorld().spawnParticle(Particle.HEART, playerLocation.add(0, 1, 0), (int) healingQuantity);
    player.getWorld().playSound(playerLocation, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.0f);
    EconomyBooster.getEconomy().withdrawPlayer(player, healingCost);
    player.sendMessage(String.format("%sYou healed %.1f HP %s(-$%.1f)", ChatColor.GREEN, healingQuantity, ChatColor.RED, healingCost));

    return true;
  }
}
