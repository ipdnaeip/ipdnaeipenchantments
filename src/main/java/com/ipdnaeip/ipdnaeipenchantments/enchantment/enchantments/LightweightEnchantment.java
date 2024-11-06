package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.UUID;

public class LightweightEnchantment extends Enchantment {

    public static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("e9c5b6c6-39f7-4e17-a965-d291a6de5e11");
    public static final double ATTACK_SPEED_INCREASE = 0.10d;

    public LightweightEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantmentCategories.MELEE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
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
