package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.UUID;

public class ReachEnchantment extends Enchantment {

    public static final UUID BLOCK_REACH_MODIFIER_UUID = UUID.fromString("1f13a843-876e-4003-8bc6-f61b6a670052");
    public static final float BLOCK_REACH_DISTANCE = 0.25f;

    public ReachEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
