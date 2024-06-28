package br.com.aguiarltda.economybooster.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CustomBook implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(String.format("%sThis command is for players only", ChatColor.RED));
      return false;
    }

    Player player = (Player) sender;

    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
    BookMeta bookMeta = (BookMeta) book.getItemMeta();

    bookMeta.setTitle(ChatColor.DARK_PURPLE + "This is a custom book");
    bookMeta.setAuthor("sir_aguiar");
    bookMeta.addPage(
            ChatColor.BLUE +
                    "Very welcome to my server!" +
                    "\n\n" +
                    ChatColor.BOLD + ChatColor.RED + "This book is about the server!"
    );

    book.setItemMeta(bookMeta);
    player.getInventory().addItem(book);

    return true;
  }
}
