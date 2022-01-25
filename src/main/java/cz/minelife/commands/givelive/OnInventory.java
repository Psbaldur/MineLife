package cz.minelife.commands.givelive;

import cz.minelife.Main;
import cz.minelife.dtb.Yaml;
import cz.minelife.teams.ETeams;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static cz.minelife.commands.givelive.GiveLive.currentSantas;
import static cz.minelife.teams.TeamUtil.getPlayerTeam;
import static cz.minelife.teams.TeamUtil.getTeamByLiveCount;

public class OnInventory implements Listener {
    private int livesToGive;
    private Yaml yaml;

    public OnInventory(Main plugin) {
        this.yaml = new Yaml(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();

        if (!event.getView().getTitle().equals("§cGiveLive - Vyber možnost") || item == null || item.getType().isAir())
            return;

        event.setCancelled(true);

        int slot = event.getSlot();
        Inventory inv = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        ItemMeta meta = item.getItemMeta();

        switch (slot) {
            case 0:
                player.closeInventory();
                break;
            case 3:
                meta.addEnchant(Enchantment.WATER_WORKER, 100, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                player.playSound(player.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1, 1);

                ItemStack item2 = inv.getItem(5);
                ItemMeta itemMeta2 = item2.getItemMeta();

                itemMeta2.removeEnchant(Enchantment.WATER_WORKER);

                item2.setItemMeta(itemMeta2);
                item.setItemMeta(meta);

                livesToGive = 1;
                break;
            case 5:
                meta.addEnchant(Enchantment.WATER_WORKER, 100, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                ItemStack item3 = inv.getItem(3);
                ItemMeta itemMeta3 = item3.getItemMeta();

                itemMeta3.removeEnchant(Enchantment.WATER_WORKER);

                item3.setItemMeta(itemMeta3);
                item.setItemMeta(meta);

                livesToGive = 2;
                break;
            case 8:
                this.giveLive(player);
                player.closeInventory();
                break;
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getView().getTitle().equals("§cGiveLive - Vyber možnost"))
            event.setCancelled(true);
    }

    public void giveLive(Player santa) {
        Player receiver = currentSantas.get(santa);

        ETeams receiverTeam = getPlayerTeam(receiver);
        ETeams santaTeam = getPlayerTeam(santa);

        int receiverLives = receiverTeam.getIntLives() + livesToGive;
        int santaLives = santaTeam.getIntLives() - livesToGive;

        if (receiverLives >= 7)
            santa.sendMessage("§cPříjemce nemůže mít více jak 6 životů!");
        if (santaLives <= 1)
            santa.sendMessage("§cNemůže ti zůstat méně jak 2 životy!");

        receiver.playEffect(EntityEffect.TOTEM_RESURRECT);
        santa.playSound(receiver.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);

        getTeamByLiveCount(santaLives).setTeam(santa);
        getTeamByLiveCount(receiverLives).setTeam(receiver);
    }
}
