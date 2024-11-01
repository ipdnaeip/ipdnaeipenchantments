package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class ReboundingEnchantment extends Enchantment {

    public static final double KNOCKBACK_BASE = 0.10D;
    public static final double KNOCKBACK_INCREASE = 0.10D;

    public ReboundingEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantmentCategories.SHIELD, new EquipmentSlot[]{EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
