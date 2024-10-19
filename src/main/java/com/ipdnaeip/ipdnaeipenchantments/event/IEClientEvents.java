package com.ipdnaeip.ipdnaeipenchantments.event;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.PhalanxEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.RangerEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.SniperEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class IEClientEvents {

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        ItemStack itemstack = player.getUseItem();
        if (player.isUsingItem()) {
            if (itemstack.is(Items.BOW)) {
                if (Minecraft.getInstance().options.keySprint.isDown()) {
                    int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.SNIPER.get(), player);
                    if (level > 0) {
                        float modifier = IpdnaeipEnchantments.IE_CONFIG.sniperRelativeZoom.get() ? event.getNewFovModifier() : 1f;
                        event.setNewFovModifier(modifier * (1 - (level * SniperEnchantment.ZOOM_INCREASE)));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onMovementInputUpdateEvent(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        Input input = event.getInput();
        ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
        int level;
        level = itemStack.getEnchantmentLevel(IEEnchantments.PHALANX.get());
        if (level > 0 && player.isUsingItem()) {
            float multiplier = 1 + (PhalanxEnchantment.SPEED_MULTIPLIER * level);
            input.forwardImpulse *= multiplier;
            input.leftImpulse *= multiplier;
            player.setSprinting(false);
        }
        level = itemStack.getEnchantmentLevel(IEEnchantments.RANGER.get());
        if (level > 0 && player.isUsingItem()) {
            float multiplier = 1 + (RangerEnchantment.SPEED_MULTIPLIER * level);
            input.forwardImpulse *= multiplier;
            input.leftImpulse *= multiplier;
            player.setSprinting(false);
        }
    }
}
