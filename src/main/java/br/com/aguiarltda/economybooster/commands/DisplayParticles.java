package br.com.aguiarltda.economybooster.commands;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisplayParticles implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String keyword, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command is for players only");
      return false;
    }

    Player player = (Player) sender;
    Location playerLocation = player.getLocation();

    if (args.length < 1) {
      return false;
    }

    Location particleLocation = playerLocation.add(0, 2, 0);
    player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, particleLocation, Integer.parseInt(args[0]));

    return true;
  }
}
