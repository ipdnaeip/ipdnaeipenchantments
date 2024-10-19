package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {

    @Inject(method = "Lnet/minecraft/world/level/block/PowderSnowBlock;canEntityWalkOnPowderSnow(Lnet/minecraft/world/entity/Entity;)Z", at = @At("RETURN"), cancellable = true)
    private static void injectCanEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (entity instanceof LivingEntity livingEntity && EnchantmentHelper.getEnchantmentLevel(IEEnchantments.LIGHTFOOT.get(), livingEntity) > 0) {
            info.setReturnValue(true);
        }
    }

}
