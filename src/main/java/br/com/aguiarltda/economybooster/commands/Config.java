package br.com.aguiarltda.economybooster.commands;

import br.com.aguiarltda.economybooster.EconomyBooster;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Config implements CommandExecutor {
  private final EconomyBooster economyBooster;

  public Config(EconomyBooster mainClass) {
    this.economyBooster = mainClass;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command is for players only");
      return false;
    }

    Player player = (Player) sender;

    economyBooster.getConfig().set("Word", "Another different word!");

    List<String> stringList = economyBooster.getConfig().getStringList("String-List");
    stringList.add("Another value!");
    economyBooster.getConfig().set("String-List", stringList);

    economyBooster.saveConfig();

    return true;
  }
}
