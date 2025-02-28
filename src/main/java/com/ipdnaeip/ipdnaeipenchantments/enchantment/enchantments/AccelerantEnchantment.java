package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AccelerantEnchantment extends Enchantment{

    public static float VELOCITY_MULTIPLIER = 0.30f;

    public AccelerantEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int level) {
        return 25 + 5 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 10;
    }

}
