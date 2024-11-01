package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AcrobaticsEnchantment extends Enchantment {

    public static final float FLY_INCREASE = 0.005f;

    public AcrobaticsEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
