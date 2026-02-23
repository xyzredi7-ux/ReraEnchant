package me.dev.reraenchant;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EnchantListener implements Listener {

    private final EnchantManager manager;

    public EnchantListener(EnchantManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player player)) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(ReraEnchant.getInstance(), "lifesteal");

        if (!meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;

        String level = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        double heal = manager.getLifesteal(level);

        player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + heal));
    }
}
