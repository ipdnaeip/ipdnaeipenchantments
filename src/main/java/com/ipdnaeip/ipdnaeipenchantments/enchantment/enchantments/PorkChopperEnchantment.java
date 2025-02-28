package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PorkChopperEnchantment extends Enchantment {

    public static final float BOOK_DROP_CHANCE = 0.08f;
    public static final float BOOK_DROP_CHANCE_PER_LOOTING_LEVEL = 0.02f;

    public PorkChopperEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int level) {
        return 40;
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 50;
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
