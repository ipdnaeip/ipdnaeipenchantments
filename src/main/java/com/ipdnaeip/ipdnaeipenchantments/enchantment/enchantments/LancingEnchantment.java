package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.TridentImpalerEnchantment;

import java.util.UUID;

public class LancingEnchantment extends Enchantment {

    public static final UUID ENTITY_REACH_MODIFIER_UUID = UUID.fromString("4d9c7cd2-209f-4f61-a062-7adcebd98f85");
    public static final UUID DAMAGE_MULTIPLIER_MODIFIER_UUID = UUID.fromString("dc2e96a6-4e4c-4c66-a09e-2fd08a625ec2");
    public static final float RANGE_INCREASE = 0.2f;
    public static final float DAMAGE_MULTIPLIER = 0.1f;

    public LancingEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.TRIDENT, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinCost(int level) {
        return 10 + 6 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 10;
    }

}
