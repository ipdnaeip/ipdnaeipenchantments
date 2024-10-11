package com.ipdnaeip.ipdnaeipenchantments.event;

import com.ipdnaeip.ipdnaeipenchantments.enchantment.PhalanxEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.RangerEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.client.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class IEClientEvents {

    @SubscribeEvent
    public static void onMovementInputUpdateEvent(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        Input input = event.getInput();
        ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
        Item item = itemStack.getItem();
        int level;
        if (IEEnchantments.SHIELD.canEnchant(item)) {
            level = itemStack.getEnchantmentLevel(IEEnchantments.PHALANX.get());
            if (level > 0 && player.isUsingItem()) {
                float multiplier = 1 + (PhalanxEnchantment.SPEED_MULTIPLIER * level);
                input.forwardImpulse *= multiplier;
                input.leftImpulse *= multiplier;
                player.setSprinting(false);
            }
        }
        if (EnchantmentCategory.BOW.canEnchant(item)) {
            level = itemStack.getEnchantmentLevel(IEEnchantments.RANGER.get());
            if (level > 0 && player.isUsingItem()) {
                float multiplier = 1 + (RangerEnchantment.SPEED_MULTIPLIER * level);
                input.forwardImpulse *= multiplier;
                input.leftImpulse *= multiplier;
                player.setSprinting(false);
            }
        }
    }
}
