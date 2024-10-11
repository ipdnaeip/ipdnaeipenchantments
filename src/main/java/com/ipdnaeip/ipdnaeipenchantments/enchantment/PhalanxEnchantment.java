package com.ipdnaeip.ipdnaeipenchantments.enchantment;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class PhalanxEnchantment extends Enchantment {

    public static final float SPEED_MULTIPLIER = 1f;

    public PhalanxEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantments.SHIELD, new EquipmentSlot[]{EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

}
