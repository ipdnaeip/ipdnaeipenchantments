package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BowItem.class)
public class BowItemMixin {

    //Gives arrows fired from bows piercing
    @ModifyVariable(method = "releaseUsing(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private AbstractArrow injectReleaseUsing(AbstractArrow arrow, ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (IpdnaeipEnchantments.IE_CONFIG.piercingBow.get()) {
            int level = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PIERCING, pStack);
            if (level > 0) {
                arrow.setPierceLevel((byte) level);
            }
        }
        return arrow;
    }

}
