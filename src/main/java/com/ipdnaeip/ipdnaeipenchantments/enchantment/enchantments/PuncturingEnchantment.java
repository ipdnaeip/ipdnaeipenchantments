package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class PuncturingEnchantment extends Enchantment {

    public static final int ARMOR_REDUCTION = 2;

    public PuncturingEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantmentCategories.MELEE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int level) {
        return 20 + 10 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 30;
    }

}
