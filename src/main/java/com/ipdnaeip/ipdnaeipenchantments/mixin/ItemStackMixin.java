package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.LightweightEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IForgeItemStack {

    //IGNORE THE ERRORS
    //Displays the increased attack speed of lightweight on the item tooltip
    @ModifyVariable(method = "getTooltipLines(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/TooltipFlag;)Ljava/util/List;", at = @At(value = "STORE", ordinal = 3))
    private double modifyAttackSpeedTooltip(double d) {
        return d + (this.getEnchantmentLevel(IEEnchantments.LIGHTWEIGHT.get()) * LightweightEnchantment.ATTACK_SPEED_INCREASE);
    }
}
