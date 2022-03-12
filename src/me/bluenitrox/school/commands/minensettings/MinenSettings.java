package me.bluenitrox.school.commands.minensettings;

import me.bluenitrox.school.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MinenSettings implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return true;
        ((Player) sender).openInventory(inventory());

        return false;
    }

    public void onClick(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;
        if(!event.getClickedInventory().getName().equalsIgnoreCase("§8» §6§lMineneinstellungen")) return;
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        if(event.getCurrentItem().getType() == Material.WATCH) {
            player.closeInventory();
            player.performCommand("getoptions");
        } else if(event.getCurrentItem().getType() == Material.CHEST) {
            player.closeInventory();
            player.performCommand("minebackpack");
        } else if(event.getCurrentItem().getType() == Material.EMERALD) {
            player.closeInventory();
            player.performCommand("selloptions");
        }
    }

    private Inventory inventory() {
        String guiname = "§8» §6§lMineneinstellungen";
        Inventory inventory = Bukkit.createInventory(null, 9*3, guiname);
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayname(" ").build();

        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, glass);
        }

        inventory.setItem(8+3, new ItemBuilder(Material.WATCH).setDisplayname("§8» §6§lMinenitems").build());
        inventory.setItem(8+5, new ItemBuilder(Material.CHEST).setDisplayname("§8» §6§lLageroptionen").build());
        inventory.setItem(8+7, new ItemBuilder(Material.EMERALD).setDisplayname("§8» §6§lVerkaufsoptionen").build());

        return inventory;
    }
}
