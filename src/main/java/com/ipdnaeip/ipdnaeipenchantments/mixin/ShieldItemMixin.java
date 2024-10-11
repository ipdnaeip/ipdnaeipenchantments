package com.ipdnaeip.ipdnaeipenchantments.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tiers;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShieldItem.class)
public abstract class ShieldItemMixin extends Item {

    public ShieldItemMixin(Properties pProperties) {
        super(pProperties);
    }

    public int getEnchantmentValue() {
        return Tiers.WOOD.getEnchantmentValue();
    }

}
