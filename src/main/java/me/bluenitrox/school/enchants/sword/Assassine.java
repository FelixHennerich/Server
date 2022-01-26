package me.bluenitrox.school.enchants.sword;

import me.bluenitrox.school.enchants.EnchantAPI;
import me.bluenitrox.school.managers.EnchantManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Assassine extends EnchantAPI {

    public static void assassineAuslösen(Player damager, Player player, EntityDamageByEntityEvent e) {
        if (hasEnchant(damager, EnchantManager.Assassine)) {
            if (makeOrNot80(stringToNumber(damager.getItemInHand(), EnchantManager.Assassine))) {
                damager.playSound(damager.getLocation(), Sound.LEVEL_UP, 1L, 1L);
                e.setDamage(e.getDamage()*2);
            }
        }
    }

}
