package br.com.aguiarltda.economybooster;

import br.com.aguiarltda.economybooster.commands.Config;
import br.com.aguiarltda.economybooster.commands.DisplayBossbar;
import br.com.aguiarltda.economybooster.commands.DisplayParticles;
import br.com.aguiarltda.economybooster.commands.Heal;
import br.com.aguiarltda.economybooster.events.EntityTame;
import br.com.aguiarltda.economybooster.events.FireworkSneak;
import br.com.aguiarltda.economybooster.events.JoinServer;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBooster extends JavaPlugin {

  private static Economy economy = null;
  private static Permission permissions = null;
  private final String DESCRIPTION = getDescription().getName();

  public static Economy getEconomy() {
    return economy;
  }

  public static Permission getPermissions() {
    return permissions;
  }

  @Override
  public void onEnable() {
    if (!setupConfigFile()) {
      disablePlugin(String.format("[%s] - Disabled due to not having a 'config.yml' setted", DESCRIPTION));
      return;
    }

    if (!setupEconomy()) {
      disablePlugin(String.format("[%s] - Disabled due to no Vault dependency found!", DESCRIPTION));
      return;
    }

    setupPermissions();

    getCommand("display").setExecutor(new DisplayParticles());
    getCommand("heal").setExecutor(new Heal());
    getCommand("config").setExecutor(new Config(this));
    getCommand("bossbar").setExecutor(new DisplayBossbar());

    Bukkit.getPluginManager().registerEvents(new EntityTame(), this);
    Bukkit.getPluginManager().registerEvents(new FireworkSneak(), this);
    Bukkit.getPluginManager().registerEvents(new JoinServer(), this);
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

  private boolean setupCommands() {
    getCommand("display").setExecutor(new DisplayParticles());
    getCommand("heal").setExecutor(new Heal());
    getCommand("config").setExecutor(new Config(this));

    return true;
  }

  private boolean setupConfigFile() {
    getConfig().options().copyDefaults();
    saveDefaultConfig();

    return true;
  }

  private void disablePlugin(String message) {
    getLogger().severe(message);
    getServer().getPluginManager().disablePlugin(this);
  }


}
