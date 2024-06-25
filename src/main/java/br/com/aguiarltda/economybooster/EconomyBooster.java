package br.com.aguiarltda.economybooster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

public final class EconomyBooster extends JavaPlugin {

    private static Economy economy = null;
    private static Permission permissions = null;
    private static Chat chat = null;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            String description = getDescription().getName();
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", description));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        setupPermissions();
        setupChat();

        System.out.printf("\n[%s] Loaded successfully\n", getDescription().getName());
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

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
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

        Player player = (Player) sender;

        if (command.getLabel().equals("test-economy")) {
            // Lets give the player 1.05 currency (note that SOME economic plugins require
            // rounding!)
            sender.sendMessage(String.format("You have %s", economy.format(economy.getBalance(player.getName()))));
            EconomyResponse r = economy.depositPlayer(player, 1.05);
            if (r.transactionSuccess()) {
                sender.sendMessage(String.format("You were given %s and now have %s", economy.format(r.amount),
                        economy.format(r.balance)));
            } else {
                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
            }
            return true;
        } else if (command.getLabel().equals("test-permissions")) {
            // Lets test if user has the node "example.plugin.awesome" to determine if they
            // are awesome or just suck
            if (permissions.has(player, "example.plugin.awesome")) {
                sender.sendMessage("You are awesome!");
            } else {
                sender.sendMessage("You suck!");
            }
            return true;
        } else {
            return false;
        }
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static Permission getPermissions() {
        return permissions;
    }

    public static Chat getChat() {
        return chat;
    }
}
