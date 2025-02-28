package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.SmashingEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public abstract class BlockMixin {

    //Changes block drops with the smashing enchantment
    @Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;", at = @At(value = "HEAD"), cancellable = true)
    private static void injectGetDrops(BlockState state, ServerLevel serverLevel, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack itemStack, CallbackInfoReturnable<List<ItemStack>> info) {
        int level = EnchantmentHelper.getTagEnchantmentLevel(IEEnchantments.SMASHING.get(), itemStack);
        if (level > 0) {
            List<ItemStack> list = new ArrayList<>();
            Block block = state.getBlock();
            if (entity instanceof Player player) {
                if (block == Blocks.STONE || block == Blocks.DEEPSLATE) {
                    if (serverLevel.getRandom().nextFloat() <= SmashingEnchantment.IRON_NUGGET_CHANCE * level) {
                        list.add(new ItemStack(Items.IRON_NUGGET));
                        block.popExperience(serverLevel, pos, 1);
                        player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 0.5f, 0.9f + player.getRandom().nextFloat() * 2f);
                        info.setReturnValue(list);
                    }
                }
                if (block == Blocks.NETHERRACK) {
                    if (serverLevel.getRandom().nextFloat() <= SmashingEnchantment.GOLD_NUGGET_CHANCE * level) {
                        list.add(new ItemStack(Items.GOLD_NUGGET));
                        block.popExperience(serverLevel, pos, 1);
                        player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 0.5f, 0.9f + player.getRandom().nextFloat() * 2f);
                        info.setReturnValue(list);
                    }
                }
                if (block == Blocks.ANDESITE || block == Blocks.DIORITE || block == Blocks.GRANITE) {
                    if (serverLevel.getRandom().nextFloat() <= SmashingEnchantment.QUARTZ_CHANCE * level) {
                        list.add(new ItemStack(Items.QUARTZ));
                        block.popExperience(serverLevel, pos, 1);
                        player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 0.5f, 0.9f + player.getRandom().nextFloat() * 2f);
                        info.setReturnValue(list);
                    }
                }
            }
        }
    }

}
