package br.com.aguiarltda.economybooster;

import br.com.aguiarltda.economybooster.registry.ListenersRegistry;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBooster extends JavaPlugin {

  private static Economy economy = null;
  private static Permission permissions = null;

  public static Economy getEconomy() {
    return economy;
  }

  public static Permission getPermissions() {
    return permissions;
  }


  @Override
  public void onEnable() {
    if (!setupEconomy()) {
      String description = getDescription().getName();
      getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", description));
      getServer().getPluginManager().disablePlugin(this);
      return;
    }

    setupPermissions();

    ListenersRegistry.registerAllListeners(this);

    Bukkit.getConsoleSender().sendMessage("[EconomyBooster] Plugin succesfully loaded");
  }

  @Override
  public void onDisable() {
    String description = getDescription().getName();
    String descriptionVersion = getDescription().getVersion();
    getLogger().info(String.format("[%s] Disabled Version %s", description, descriptionVersion));
  }

  private boolean setupEconomy() {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
      return false;
    }

    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

    if (rsp == null) {
      return false;
    }

    economy = rsp.getProvider();
    return economy != null;
  }

  private boolean setupPermissions() {
    RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
    permissions = rsp.getProvider();
    return permissions != null;
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    if (!(sender instanceof Player)) {
      getLogger().info("Only players are supported for this Example Plugin, but you should not do this!!!");
      return true;
    }

    return true;
  }
}
