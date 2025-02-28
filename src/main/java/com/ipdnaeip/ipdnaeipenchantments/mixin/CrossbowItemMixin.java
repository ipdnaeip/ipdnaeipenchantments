package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import com.ipdnaeip.ipdnaeipenchantments.accessor.FireworkRocketEntityAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.AccelerantEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.PrecisionEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {

    //Precision
    @ModifyVariable(method = "shootProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;FZFFF)V", at = @At("HEAD"), ordinal = 3)
    private static float modifyShootProjectile(float f, Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pCrossbowStack, ItemStack pAmmoStack, float pSoundPitch, boolean pIsCreativeMode, float pVelocity, float pInaccuracy, float pProjectileAngle) {
        int level = EnchantmentHelper.getTagEnchantmentLevel(IEEnchantments.PRECISION.get(), pCrossbowStack);
        if (level > 0) {
            f *= 1 - (level * PrecisionEnchantment.ANGLE_REDUCTION);
        }
        return f;
    }

    //Punch Punch Flame Config
    @ModifyVariable(method = "getArrow(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/AbstractArrow;", at = @At("RETURN"))
    private static AbstractArrow modifyGetArrow(AbstractArrow arrow, Level pLevel, LivingEntity pLivingEntity, ItemStack pCrossbowStack, ItemStack pAmmoStack) {
        if (IpdnaeipEnchantments.IE_CONFIG.powerPunchFlameCrossbow.get()) {
            int level = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, pCrossbowStack);
            if (level > 0) {
                arrow.setBaseDamage(arrow.getBaseDamage() + (double) level * 0.5D + 0.5D);
            }
            level = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, pCrossbowStack);
            if (level > 0) {
                arrow.setKnockback(level);
            }
            level = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, pCrossbowStack);
            if (level > 0) {
                arrow.setSecondsOnFire(100);
            }

        }
        return arrow;
    }

    //Accelerant
    @ModifyVariable(method = "shootProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;FZFFF)V", at = @At(value = "STORE", ordinal = 0))
    private static Projectile modifyShootProjectile2(Projectile projectile, Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pCrossbowStack, ItemStack pAmmoStack, float pSoundPitch, boolean pIsCreativeMode, float pVelocity, float pInaccuracy, float pProjectileAngle) {
        int level = EnchantmentHelper.getTagEnchantmentLevel(IEEnchantments.ACCELERANT.get(), pCrossbowStack);
        if (level > 0) {
            ((FireworkRocketEntityAccessor)projectile).setAccelerantLevelIE(level);
        }
        return projectile;
    }

}
