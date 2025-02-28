package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {


    @Inject(method = "canApplyAtEnchantingTable(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true, remap = false)
    private void injectCanApplyAtEnchantingTable(ItemStack itemStack, CallbackInfoReturnable<Boolean> info) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (ForgeRegistries.ENCHANTMENTS.getKey(enchantment).getNamespace().equals(IpdnaeipEnchantments.MOD_ID)) {
            //Allows for crossbows to accept bow enchantments at enchanting tables
            if (enchantment.category == EnchantmentCategory.BOW && IpdnaeipEnchantments.IE_CONFIG.bowEnchantmentsOnCrossbows.get() && EnchantmentCategory.CROSSBOW.canEnchant(itemStack.getItem())) {
                info.setReturnValue(true);
            }
            //Allows for bows to accept crossbow enchantments at enchanting tables
            if (enchantment.category == EnchantmentCategory.CROSSBOW && IpdnaeipEnchantments.IE_CONFIG.crossbowEnchantmentsOnBows.get() && EnchantmentCategory.BOW.canEnchant(itemStack.getItem())) {
                info.setReturnValue(true);
            }
        }
        if (EnchantmentCategory.CROSSBOW.canEnchant(itemStack.getItem()) && IpdnaeipEnchantments.IE_CONFIG.powerPunchFlameCrossbow.get()) {
            //Allows for power, punch, and flame to be applied to crossbows, ignore IntelliJ recommendation
            if (enchantment == Enchantments.POWER_ARROWS || enchantment == Enchantments.PUNCH_ARROWS || enchantment == Enchantments.FLAMING_ARROWS) {
                info.setReturnValue(true);
            }
        }
        if (EnchantmentCategory.BOW.canEnchant(itemStack.getItem()) && IpdnaeipEnchantments.IE_CONFIG.piercingBow.get()) {
            //Allows for piercing to be applied to bows, ignore IntelliJ recommendation
            if (enchantment == Enchantments.PIERCING) {
                info.setReturnValue(true);
            }
        }
    }

}
