package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyConstant(method = "Lnet/minecraft/world/food/FoodData;tick(Lnet/minecraft/world/entity/player/Player;)V", constant = @Constant(intValue = 10))
    public int modifyTick1(int i, Player player) {
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.METABOLIZE.get(), player);
        if (level > 0) {
            i -= level;
        }
        return i;
    }

    @ModifyConstant(method = "Lnet/minecraft/world/food/FoodData;tick(Lnet/minecraft/world/entity/player/Player;)V", constant = @Constant(intValue = 18))
    public int modifyTick2(int i, Player player) {
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.METABOLIZE.get(), player);
        if (level > 0) {
            i -= level;
        }
        return i;
    }

}
