package me.bluenitrox.school.listener;

import me.bluenitrox.school.ah.AhListener;
import me.bluenitrox.school.boost.BoostInv;
import me.bluenitrox.school.commands.Mine;
import me.bluenitrox.school.commands.Prestige;
import me.bluenitrox.school.commands.getBooks;
import me.bluenitrox.school.crafting.Enchanter;
import me.bluenitrox.school.crafting.WerkbankGUIRegister;
import me.bluenitrox.school.dungeon.command.DungeonInventory;
import me.bluenitrox.school.enchants.CraftAPI;
import me.bluenitrox.school.enchants.Enchant;
import me.bluenitrox.school.features.*;
import me.bluenitrox.school.haendler.HändlerAPI;
import me.bluenitrox.school.haendler.commands.Schmied;
import me.bluenitrox.school.haendler.commands.Taxi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void onClick(final org.bukkit.event.inventory.InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        WerkbankGUIRegister wgr = new WerkbankGUIRegister();
        CraftAPI craft = new CraftAPI();
        Pet pet = new Pet();
        HändlerAPI api = new HändlerAPI();
        DailyReward dr = new DailyReward();

        if(CaseAPI.caseöffnen != null) {
            if (CaseAPI.caseöffnen.contains(p)) {
                e.setCancelled(true);
                return;
            }
        }
        if(e.getClickedInventory() != null) {
            if (e.getClickedInventory().getName() != null) {
                Prestige.onClick(e);
                craft.onClick(e);
                SkillSystem.onClick(e);
                caseClick(e);
                BoostInv.inventoryClick(e, p);
                Mine.onMinenClick(e);
                KitAPI.kitApiClickEvent(e);
                Schmied.onClickSchmied(e);
                wgr.onClick(e);
                pet.petClickEventInventory(e);
                api.onClickHändler(e);
                dr.dailyRewardClick(e);
                AhListener.onClickAuctionhouse(e);
                Enchanter.inventoryClick(e);
                getBooks.onClick(e);
                Taxi.onClick(e);
                DungeonInventory.onClick(e);
            }
        }
    }

    private void caseClick(org.bukkit.event.inventory.InventoryClickEvent e){
        if(e.getCurrentItem() != null) {
            if ((e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.daily)
                    || e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.gewöhnlich)
                    || e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.selten)
                    || e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.episch)
                    || e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.legendär)
                    || e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.mysthische)
                    || e.getClickedInventory().getName().equalsIgnoreCase(CaseAPI.tier)) && e.getCurrentItem() != null) {
                e.setCancelled(true);
            } else if (e.getClickedInventory().getName().equalsIgnoreCase("§e§lCase Gewinn")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ") || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lDein Gewinn")) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
