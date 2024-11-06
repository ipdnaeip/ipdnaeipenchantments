package com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class SmashingEnchantment extends Enchantment {

    public static final float IRON_NUGGET_CHANCE = 0.007f;
    public static final float GOLD_NUGGET_CHANCE = 0.005f;
    public static final float QUARTZ_CHANCE = 0.003f;

    //for testing
//    public static final float IRON_NUGGET_CHANCE = 1f;
//    public static final float GOLD_NUGGET_CHANCE = 1f;
//    public static final float QUARTZ_CHANCE = 1f;

    public SmashingEnchantment() {
        super(Rarity.VERY_RARE, IEEnchantmentCategories.PICKAXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int level) {
        return 15 + 9 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 50;
    }
}
