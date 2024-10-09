package com.ipdaneip.ipdnaeipenchantments.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AnglersArmEnchantment extends Enchantment {

    public static final float VELOCITY_MULTIPLIER = 0.30f;

    public AnglersArmEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

}
