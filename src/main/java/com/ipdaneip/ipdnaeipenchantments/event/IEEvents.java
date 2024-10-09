package com.ipdaneip.ipdnaeipenchantments.event;

import com.ipdaneip.ipdnaeipenchantments.enchantment.AnglersArmEnchantment;
import com.ipdaneip.ipdnaeipenchantments.enchantment.DrawEnchantment;
import com.ipdaneip.ipdnaeipenchantments.enchantment.HarpooningEnchantment;
import com.ipdaneip.ipdnaeipenchantments.registry.IEEnchantments;
import com.ipdaneip.ipdnaeipenchantments.util.IEUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IEEvents {

    @SubscribeEvent
    public static void onEntityJoinLevelEvent(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Projectile projectile && projectile.getOwner() instanceof LivingEntity owner) {
            //Fishing Rod
            if (projectile instanceof FishingHook fishingHook) {
                IEUtils.applyProjectileEnchantments(fishingHook, owner, EnchantmentCategory.FISHING_ROD);
                //Angler's Arm
                if (IEUtils.hasEnchantment(fishingHook, IEEnchantments.ANGLERS_ARM)) {
                    int anglersArmEnchantmentLevel = IEUtils.getEnchantmentLevel(fishingHook, IEEnchantments.ANGLERS_ARM);
                    float velocity_multiplier = 1 + (AnglersArmEnchantment.VELOCITY_MULTIPLIER * anglersArmEnchantmentLevel);
                    fishingHook.setDeltaMovement(fishingHook.getDeltaMovement().scale(velocity_multiplier));
                }
            }
            //Bow
            if (projectile instanceof AbstractArrow arrow) {
                IEUtils.applyProjectileEnchantments(arrow, owner, EnchantmentCategory.BOW);
                //Draw
                if (IEUtils.hasEnchantment(arrow, IEEnchantments.DRAW)) {
                    int level = IEUtils.getEnchantmentLevel(arrow, IEEnchantments.DRAW);
                    float velocity_multiplier = 1 + (DrawEnchantment.VELOCITY_MULTIPLIER * level);
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(velocity_multiplier));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootLevelEvent(LootingLevelEvent event) {
        if (event.getDamageSource().getDirectEntity() != null && event.getDamageSource().getDirectEntity() instanceof AbstractArrow arrow) {
            if (IEUtils.hasEnchantment(arrow, IEEnchantments.HUNTER)) {
                int level = IEUtils.getEnchantmentLevel(arrow, IEEnchantments.HUNTER);
                event.setLootingLevel(Math.max(event.getLootingLevel(), level));
            }
        }
    }

    @SubscribeEvent
    public static void onProjectileImpactEvent(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult)(event.getRayTraceResult())).getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                //Harpooning
                if (projectile instanceof FishingHook fishingHook && fishingHook.getOwner() instanceof LivingEntity owner) {
                    if (IEUtils.hasEnchantment(fishingHook, IEEnchantments.HARPOONING)) {
                        float damage = 1f + (HarpooningEnchantment.DAMAGE_MULTIPLIER * (IEUtils.getEnchantmentLevel(fishingHook, IEEnchantments.HARPOONING)));
                        //Vec3 deltaMovement = livingEntity.getDeltaMovement();
                        livingEntity.hurt(livingEntity.damageSources().indirectMagic(fishingHook, owner), damage);
                        //livingEntity.setDeltaMovement(deltaMovement);
                    }
                }
            }
        }
    }

}
