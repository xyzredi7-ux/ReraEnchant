package me.dev.reraenchant;

import org.bukkit.plugin.java.JavaPlugin;

public class ReraEnchant extends JavaPlugin {

    private static ReraEnchant instance;
    private EnchantManager enchantManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        enchantManager = new EnchantManager(this);
        enchantManager.loadEnchants();

        getServer().getPluginManager().registerEvents(
                new EnchantListener(enchantManager),
                this
        );

        getCommand("re").setExecutor(new RECommand(enchantManager));

        getLogger().info("ReraEnchant Enabled!");
    }

    public static ReraEnchant getInstance() {
        return instance;
    }
}
