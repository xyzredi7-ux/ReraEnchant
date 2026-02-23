package me.dev.reraenchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RECommand implements CommandExecutor {

    private final EnchantManager manager;

    public RECommand(EnchantManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            manager.reload();
            sender.sendMessage("§aReraEnchant reloaded!");
            return true;
        }

        sender.sendMessage("§cUsage: /re reload");
        return true;
    }
}
