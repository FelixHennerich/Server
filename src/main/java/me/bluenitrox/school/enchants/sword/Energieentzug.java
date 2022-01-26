package me.bluenitrox.school.enchants.sword;

import me.bluenitrox.school.enchants.EnchantAPI;
import me.bluenitrox.school.managers.EnchantManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Energieentzug extends EnchantAPI {

    public static void energieentzugAuslösen(Player damager, Player player) {
        if (hasEnchant(damager.getItemInHand(), EnchantManager.Energieentzug)) {
            if (makeOrNot80(stringToNumber(damager.getItemInHand(), EnchantManager.Energieentzug))) {
                damager.playSound(damager.getLocation(), Sound.LEVEL_UP, 1L, 1L);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 3, 0));
            }
        }
    }
}
