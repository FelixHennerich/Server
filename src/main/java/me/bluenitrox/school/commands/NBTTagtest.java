package me.bluenitrox.school.commands;

import me.bluenitrox.school.managers.EnchantManager;
import me.bluenitrox.school.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NBTTagtest implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        Player p = (Player)cs;
        ItemStack tie1 = new ItemBuilder(Material.MONSTER_EGG, (short) 67).setDisplayname("§6Haustier : Benjamin").setLore("§8» §7Rechtsklicke, um dieses Tier §aeinzulösen§7.").build();

        p.getInventory().addItem(tie1);

        return false;
    }
}
