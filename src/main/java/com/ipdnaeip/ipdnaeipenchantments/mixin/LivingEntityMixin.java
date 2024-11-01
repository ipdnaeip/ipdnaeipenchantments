package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.accessor.LivingEntityAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.AcrobaticsEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.BoostingEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityAccessor {

    @Shadow
    protected int attackStrengthTicker;

    @Override
    public int getAttackStrengthTicker() {
        return this.attackStrengthTicker;
    }

    @Override
    public void setAttackStrengthTicker(int i) {
        this.attackStrengthTicker = i;
    }

    //Increases jump height with boosting
    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;getJumpPower()F", at = @At("RETURN"), cancellable = true)
    public void injectGetJumpPower(CallbackInfoReturnable<Float> info) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.BOOSTING.get(), livingEntity);
        if (level > 0) {
            info.setReturnValue(info.getReturnValue() + BoostingEnchantment.JUMP_BOOST * level);
        }
    }

    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;getFlyingSpeed()F", at = @At("RETURN"), cancellable = true, remap = false)
    public void injectGetFlyingSpeed(CallbackInfoReturnable<Float> info) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.ACROBATICS.get(), livingEntity);
        if (level > 0) {
            info.setReturnValue(info.getReturnValue() + AcrobaticsEnchantment.FLY_INCREASE * level);
        }
    }
}
