package com.ipdnaeip.ipdnaeipenchantments.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class SturdyEnchantment extends Enchantment {

    public static float KNOCKBACK_NUM_DENOM = 4f;

    public SturdyEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SWIFT_SNEAK;
    }

    public static float getKnockbackMultiplier(int level) {
        return KNOCKBACK_NUM_DENOM / (KNOCKBACK_NUM_DENOM + (float)level);
    }
}
