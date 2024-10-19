package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ObscuringEnchantment extends Enchantment {

    public ObscuringEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

/*    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }*/

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}

