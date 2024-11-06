package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class PhalanxEnchantment extends Enchantment {

    public static final float SPEED_MULTIPLIER = 1f;

    public PhalanxEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantmentCategories.SHIELD, new EquipmentSlot[]{EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getMinCost(int level) {
        return 10 + 8 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 10;
    }

}
