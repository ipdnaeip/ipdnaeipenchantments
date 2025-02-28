package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.MetabolizeEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyConstant(method = "tick(Lnet/minecraft/world/entity/player/Player;)V", constant = @Constant(intValue = 10))
    private int modifyTick1(int i, Player player) {
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.METABOLIZE.get(), player);
        if (level > 0) {
            i -= level * MetabolizeEnchantment.SATURATED_MULTIPLIER;
        }
        return i;
    }

    @ModifyConstant(method = "tick(Lnet/minecraft/world/entity/player/Player;)V", constant = @Constant(intValue = 80, ordinal = 0))
    private int modifyTick2(int i, Player player) {
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.METABOLIZE.get(), player);
        if (level > 0) {
            i -= level * MetabolizeEnchantment.UNSATURATED_MULTIPLIER;
        }
        return i;
    }

}
