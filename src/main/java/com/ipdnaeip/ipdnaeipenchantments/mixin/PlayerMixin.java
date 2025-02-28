package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.accessor.LivingEntityAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.AcrobaticsEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.QuickdrawEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntityMixin {

    //Increases attack strength percentage after switching weapons
    @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;resetAttackStrengthTicker()V"))
    private void redirectAttackStrengthTicker(Player player) {
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.QUICKDRAW.get(), player);
        if (level > 0) {
            //Has to be server side otherwise the attack bar glitches
            if (!player.level().isClientSide()) {
                ((LivingEntityAccessor) player).setAttackStrengthTicker((int) (player.getCurrentItemAttackStrengthDelay() * (level * QuickdrawEnchantment.RECOVERY_MULTIPLIER)));
            }
        } else {
            player.resetAttackStrengthTicker();
        }
    }

/*    @Inject(method = "getFlyingSpeed()F", at = @At("RETURN"), cancellable = true)
    public void injectGetFlyingSpeed(CallbackInfoReturnable<Float> info) {
        Player player = (Player)(Object)this;
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.ACROBATICS.get(), player);
        if (level > 0) {
            info.setReturnValue(info.getReturnValue() + AcrobaticsEnchantment.FLY_INCREASE * level);
        }
    }*/

}
