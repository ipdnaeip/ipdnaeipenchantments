package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.accessor.LivingEntityAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.AcrobaticsEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.BoostingEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.PuncturingEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityAccessor {

    @Shadow
    protected int attackStrengthTicker;

    @Shadow public abstract int getArmorValue();

    @Override
    public int getAttackStrengthTicker() {
        return this.attackStrengthTicker;
    }

    @Override
    public void setAttackStrengthTicker(int i) {
        this.attackStrengthTicker = i;
    }

    //Increases jump height with boosting
    @Inject(method = "getJumpPower()F", at = @At("RETURN"), cancellable = true)
    public void injectGetJumpPower(CallbackInfoReturnable<Float> info) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.BOOSTING.get(), livingEntity);
        if (level > 0) {
            info.setReturnValue(info.getReturnValue() + BoostingEnchantment.JUMP_BOOST * level);
        }
    }

/*    @Inject(method = "getFlyingSpeed()F", at = @At("RETURN"), cancellable = true, remap = false)
    private void injectGetFlyingSpeed(CallbackInfoReturnable<Float> info) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.ACROBATICS.get(), livingEntity);
        if (level > 0) {
            info.setReturnValue(info.getReturnValue() + AcrobaticsEnchantment.FLY_INCREASE * level);
        }
    }*/

    @Redirect(method = "getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getArmorValue()I"))
    private int redirectGetArmorValue(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        int i = entity.getArmorValue();
        if (damageSource.getDirectEntity() instanceof LivingEntity attacker) {
            int level = EnchantmentHelper.getTagEnchantmentLevel(IEEnchantments.PUNCTURING.get(), attacker.getMainHandItem());
            if (level > 0) {
                i -= level * PuncturingEnchantment.ARMOR_REDUCTION;
            }
        }
        return i;
    }

}
