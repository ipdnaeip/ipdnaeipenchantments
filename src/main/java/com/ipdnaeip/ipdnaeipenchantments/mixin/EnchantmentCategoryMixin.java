/*
package com.ipdnaeip.ipdnaeipenchantments.mixin;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentCategory.class)
public abstract class EnchantmentCategoryMixin {

    //Doesn't work
    @Inject(method = "Lnet/minecraft/world/item/enchantment/EnchantmentCategory;canEnchant(Lnet/minecraft/world/item/Item;)Z", at = @At("RETURN"), cancellable = true)
    public void injectCanEnchant(Item item, CallbackInfoReturnable<Boolean> info) {
        EnchantmentCategory enchantmentCategory = (EnchantmentCategory)(Object)this;
        if (enchantmentCategory == EnchantmentCategory.BOW) {
            info.setReturnValue(info.getReturnValue() || item instanceof CrossbowItem);
        }
        if (enchantmentCategory == EnchantmentCategory.CROSSBOW) {
            info.setReturnValue(info.getReturnValue() || item instanceof BowItem);
        }
    }

*/
/*    @Redirect(method = "Lnet/minecraft/world/item/enchantment/EnchantmentCategory;canEnchant(Lnet/minecraft/world/item/Item;)Z", at = @At("RETURN"))
    public boolean redirectCanEnchant(boolean z, Item item) {
        EnchantmentCategory enchantmentCategory = (EnchantmentCategory)(Object)this;
        if (enchantmentCategory == EnchantmentCategory.BOW) {
            return z || item instanceof CrossbowItem;
        }
        if (enchantmentCategory == EnchantmentCategory.CROSSBOW) {
            return z || item instanceof BowItem;
        }
        return z;
    }*//*


}
*/
