package com.ipdaneip.ipdnaeipenchantments.event;

import com.ipdaneip.ipdnaeipenchantments.enchantment.PhalanxEnchantment;
import com.ipdaneip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.client.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
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
        int level = itemStack.getEnchantmentLevel(IEEnchantments.PHALANX.get());
        if (level > 0 && itemStack.getItem() instanceof ShieldItem) {
            float multiplier = 1 + (PhalanxEnchantment.SPEED_MULTIPLIER * level);
            input.forwardImpulse *= multiplier;
            input.leftImpulse *= multiplier;
        }
    }
}
