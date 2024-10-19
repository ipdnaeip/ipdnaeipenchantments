package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    /** @noinspection ConstantConditions*/ //Allows for shields to be enchanted at enchanting tables
    @Inject(method = "Lnet/minecraft/world/item/Item;getEnchantmentValue()I", at = @At("RETURN"), cancellable = true)
    public void injectGetEnchantmentValue(CallbackInfoReturnable<Integer> info) {
        //Ignore the IntelliJ recommendation
        if ((Item)(Object)this instanceof ShieldItem) {
            if (IpdnaeipEnchantments.IE_CONFIG.sniperRelativeZoom.get()) {
                info.setReturnValue(Tiers.WOOD.getEnchantmentValue());
            }
        }
    }

}
