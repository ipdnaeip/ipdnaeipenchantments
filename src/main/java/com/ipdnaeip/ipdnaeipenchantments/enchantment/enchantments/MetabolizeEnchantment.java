package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MetabolizeEnchantment extends Enchantment {

    public static final int SATURATED_MULTIPLIER = 1;
    public static final int UNSATURATED_MULTIPLIER = 4;

    public MetabolizeEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getMinCost(int level) {
        return 30 + 5 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 20;
    }
}
