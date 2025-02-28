package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.accessor.AbstractArrowAccessor;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ArrowItem.class)
public abstract class ArrowItemMixin {

    //Applies mod enchantment levels to arrow entities
    //The stack is the arrow from which it is applying effects, not the bow
    @Inject(method = "createArrow(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/entity/projectile/AbstractArrow;", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectCreateArrow(Level level, ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<AbstractArrow> info, Arrow arrow) {
        ((AbstractArrowAccessor)arrow).setAerodynamicsLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.AERODYNAMICS.get(), shooter));
        ((AbstractArrowAccessor)arrow).setAquadynamicsLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.AQUADYNAMICS.get(), shooter));
        ((AbstractArrowAccessor)arrow).setDrawLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.DRAW.get(), shooter));
        ((AbstractArrowAccessor)arrow).setHunterLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.HUNTER.get(), shooter));
        ((AbstractArrowAccessor)arrow).setMarksmanLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.MARKSMAN.get(), shooter));
    }

}
