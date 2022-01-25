package cz.minelife.commands.givelive;

import cz.minelife.dtb.Yaml;
import cz.minelife.teams.ETeams;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

import static cz.minelife.teams.TeamUtil.getPlayerTeam;
import static cz.minelife.teams.TeamUtil.getTeamByLiveCount;
import static cz.minelife.utils.Items.createItem;

public class GiveLiveGUI implements Listener {
    protected final Inventory inv;
    protected Yaml yaml;
    protected Player santa;
    protected Player receiver;
    protected int livesToGive;
    protected HashMap<Integer, ItemStack> items = new HashMap<>();

    public GiveLiveGUI(Player santa, Player receiver, Yaml yaml) {
        this.santa = santa;
        this.receiver = receiver;
        this.yaml = yaml;

        inv = Bukkit.createInventory(null, 9, "§cGiveLive - Vyber možnost");

        this.addItems();
        this.openInventory();
    }

    public void addItems() {
        items.put(0, createItem(Material.RED_WOOL, 1, "§cZrušit", "§7Kliknutím na toto zrušíš dávání životu"));
        items.put(3, createItem(Material.GRAY_WOOL, 1, "§l1", "§7Kliknutím na toto dáš danému hráči jeden život"));
        items.put(5, createItem(Material.GRAY_WOOL, 2, "§l2", "§7Kliknutím na toto dáš danému hráči dva životy"));
        items.put(8, createItem(Material.GREEN_WOOL, 1, "§aPotvrdit", "§7Kliknutím na toto potvrdíš předání životu"));

        inv.setItem(0, items.get(8));
        inv.setItem(3, items.get(8));
        inv.setItem(5, items.get(8));
        inv.setItem(8, items.get(8));
    }

    public void openInventory() {
        receiver.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();

        if (event.getInventory() != inv || item == null || item.getType().isAir())
            return;

        event.setCancelled(true);

        int slot = event.getSlot();
        Player player = (Player) event.getWhoClicked();
        ItemMeta meta = item.getItemMeta();

        switch (slot) {
            case 0:
                player.closeInventory();
                break;
            case 3:
                meta.addEnchant(Enchantment.WATER_WORKER, 100, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                ItemStack item2 = items.get(5);
                ItemMeta itemMeta2 = item2.getItemMeta();

                itemMeta2.removeEnchant(Enchantment.WATER_WORKER);

                item2.setItemMeta(itemMeta2);
                item.setItemMeta(meta);

                livesToGive = 1;
                break;
            case 5:
                meta.addEnchant(Enchantment.WATER_WORKER, 100, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                ItemStack item3 = items.get(3);
                ItemMeta itemMeta3 = item3.getItemMeta();

                itemMeta3.removeEnchant(Enchantment.WATER_WORKER);

                item3.setItemMeta(itemMeta3);
                item.setItemMeta(meta);

                livesToGive = 1;
                break;
            case 8:
                this.giveLive();
                player.closeInventory();
                break;
        }
    }

    public void giveLive() {
        ETeams receiverTeam = getPlayerTeam(receiver);
        ETeams santaTeam = getPlayerTeam(santa);

        int receiverLives = receiverTeam.getIntLives() + livesToGive;
        int santaLives = santaTeam.getIntLives() - livesToGive;

        if (receiverLives >= 7)
            santa.sendMessage("§cPříjemce nemůže mít více jak 6 životů!");
        if (santaLives <= 1)
            santa.sendMessage("§cNemůže ti zůstat méně jak 2 životy!");

        yaml.setPlayerLives(receiver, receiverLives);
        yaml.setPlayerLives(santa, santaLives);

        receiver.playEffect(EntityEffect.TOTEM_RESURRECT);
        santa.playSound(receiver.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);

        getTeamByLiveCount(santaLives).setTeam(santa);
        getTeamByLiveCount(receiverLives).setTeam(receiver);
    }
}
