package com.ipdaneip.ipdnaeipenchantments.enchantment;

import com.ipdaneip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class ChargingEnchantment extends Enchantment {

    public static final float DAMAGE_MULTIPLIER = 0.1f;

    public ChargingEnchantment() {
        super(Rarity.UNCOMMON, IEEnchantments.MELEE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SWEEPING_EDGE;
    }
}
