package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.QuickdrawEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Shadow public abstract void resetAttackStrengthTicker();

    private int attackStrengthTick;

    //Increases attack strength percentage after switching weapons
    @Redirect(method = "Lnet/minecraft/world/entity/player/Player;tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;resetAttackStrengthTicker()V"))
    public void redirectAttackStrengthTicker(Player player) {
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.QUICKDRAW.get(), player);
        if (level > 0) {
            this.attackStrengthTick = (int)(player.getCurrentItemAttackStrengthDelay() * (level * QuickdrawEnchantment.RECOVERY_MULTIPLIER));
        } else {
            this.resetAttackStrengthTicker();
        }
    }

}
