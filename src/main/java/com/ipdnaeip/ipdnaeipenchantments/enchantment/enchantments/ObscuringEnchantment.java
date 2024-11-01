package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ObscuringEnchantment extends Enchantment {

    public static final float BOOK_DROP_CHANCE = 0.03f;
    public static final float BOOK_DROP_CHANCE_PER_LOOTING_LEVEL = 0.01f;

    public ObscuringEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }
}

