package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.UUID;

public class ChargingEnchantment extends Enchantment {

    public static final UUID DAMAGE_MULTIPLIER_MODIFIER_UUID = UUID.fromString("5ec8fd8e-2ab0-4f03-8b78-afc9a6bf1aef");
    public static final float DAMAGE_MULTIPLIER = 0.1f;

    public ChargingEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantmentCategories.MELEE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinCost(int level) {
        return 20 + 6 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 10;
    }

}
