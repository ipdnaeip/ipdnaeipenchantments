package com.ipdnaeip.ipdnaeipenchantments.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;

public class IceAspectEnchantment extends Enchantment {

    public static int BASE_DURATION = 40;
    public static int DURATION_MULTIPLIER = 40;

    public IceAspectEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        pTarget.setTicksFrozen(Math.max(pTarget.getTicksFrozen(), Math.min(getMaxDuration(pTarget, pLevel), pTarget.getTicksFrozen() + BASE_DURATION + (pLevel * DURATION_MULTIPLIER))));
    }

    public static int getMaxDuration(Entity entity, int level) {
        return entity.getTicksRequiredToFreeze() + BASE_DURATION + (DURATION_MULTIPLIER * level);
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && !(pOther instanceof FireAspectEnchantment);
    }
}
