package br.com.aguiarltda.economybooster.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class Punish implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("This command is for players only");
      return false;
    }

    if (args.length != 2) {
      sender.sendMessage("This command needs two arguments");
      return false;
    }

    Player player = (Player) sender;
    Player targetPlayer = Bukkit.getPlayer(args[0]);

    if (targetPlayer == null) {
      player.sendMessage("This player is not online");
      return false;
    }

    String banString;

    switch (args[1].toLowerCase()) {
      case "kick":
        targetPlayer.kickPlayer(String.format(
                "%s%sYou been kicked by %s%s",
                ChatColor.BOLD, ChatColor.RED, ChatColor.YELLOW, player.getName()
        ));
        break;
      case "ban":
        banString = String.format(
                "%s%sYou been banned by %s%s",
                ChatColor.BOLD, ChatColor.RED, ChatColor.YELLOW, player.getName()
        );
        Bukkit.getBanList(BanList.Type.NAME).addBan(targetPlayer.getName(), banString, null, null);
        targetPlayer.kickPlayer(banString);
        break;
      case "tempban":
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 2);

        banString = String.format(
                "%s%sYou been banned by %s%s",
                ChatColor.BOLD, ChatColor.RED, ChatColor.YELLOW, player.getName()
        );

        Bukkit.getBanList(BanList.Type.NAME).addBan(targetPlayer.getName(), banString, calendar.getTime(), null);
        targetPlayer.kickPlayer(banString);
        break;
      default:
        player.sendMessage("You didn't specify the punishment");
        break;
    }

    return true;
  }
}
