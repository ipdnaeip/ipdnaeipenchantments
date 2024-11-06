package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class SwattingEnchantment extends Enchantment {

    public static final float DAMAGE_MULTIPLIER = 2f;
    public static final double PULL_DOWN = -2D;

    public SwattingEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantmentCategories.SHOVEL, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int level) {
        return 40;
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 20;
    }

}
