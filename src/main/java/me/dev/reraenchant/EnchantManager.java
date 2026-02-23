package me.dev.reraenchant;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class EnchantManager {

    private final ReraEnchant plugin;
    private final Map<String, Double> lifestealValues = new HashMap<>();

    public EnchantManager(ReraEnchant plugin) {
        this.plugin = plugin;
    }

    public void loadEnchants() {
        lifestealValues.clear();

        FileConfiguration config = plugin.getConfig();

        if (config.contains("enchants.lifesteal.levels")) {
            for (String level : config.getConfigurationSection("enchants.lifesteal.levels").getKeys(false)) {
                double heal = config.getDouble("enchants.lifesteal.levels." + level);
                lifestealValues.put(level, heal);
            }
        }
    }

    public double getLifesteal(String level) {
        return lifestealValues.getOrDefault(level, 0.0);
    }

    public void reload() {
        plugin.reloadConfig();
        loadEnchants();
    }
}
