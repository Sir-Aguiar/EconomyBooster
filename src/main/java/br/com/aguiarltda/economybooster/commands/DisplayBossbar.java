package br.com.aguiarltda.economybooster.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisplayBossbar implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(String.format("%sThis command is for players only", ChatColor.RED));
      return false;
    }

    if (args.length < 1) {
      sender.sendMessage(String.format("%s Give the title of the boss bar", ChatColor.RED));
      return false;
    }

    Player player = (Player) sender;
    BossBar bossBar = Bukkit.createBossBar(
            String.format("%s%s", ChatColor.DARK_AQUA, args[0]),
            BarColor.YELLOW,
            BarStyle.SEGMENTED_6
    );
    bossBar.addPlayer(player);

    return true;
  }
}
