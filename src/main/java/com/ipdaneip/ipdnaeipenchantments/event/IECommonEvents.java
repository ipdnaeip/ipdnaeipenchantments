package com.ipdaneip.ipdnaeipenchantments.event;

import com.ipdaneip.ipdnaeipenchantments.enchantment.*;
import com.ipdaneip.ipdnaeipenchantments.registry.IEEnchantments;
import com.ipdaneip.ipdnaeipenchantments.util.IEUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IECommonEvents {

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
    public static void onLivingDamageEvent(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        Entity attackingEntity = source.getEntity();
        LivingEntity targetLivingEntity = event.getEntity();
        float damage = event.getAmount();
        if (damage > 0 && attackingEntity instanceof LivingEntity attackingLivingEntity) {
            //Charging
            if (!source.isIndirect()) {
                int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.CHARGING.get(), attackingLivingEntity);
                if (level > 0 && attackingLivingEntity.isSprinting()) {
                    event.setAmount(event.getAmount() * (1 + (level * ChargingEnchantment.DAMAGE_MULTIPLIER)));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingEntityUseItemEvent(LivingEntityUseItemEvent.Start event) {
        LivingEntity livingEntity = event.getEntity();
        ItemStack itemStack = event.getItem();
        UseAnim useAnim = itemStack.getUseAnimation();
        //Gluttony
        if (useAnim == UseAnim.EAT || useAnim == UseAnim.DRINK) {
            int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.GLUTTONY.get(), livingEntity);
            if (level > 0) {
                event.setDuration(Math.max(0, (int)((float)event.getDuration() * (1 - (GluttonyEnchantment.DURATION_REDUCTION * ((float) level))))));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        Entity attackingEntity = source.getEntity();
        LivingEntity targetLivingEntity = event.getEntity();
        float damage = event.getAmount();
        if (damage > 0 && attackingEntity instanceof LivingEntity attackingLivingEntity) {
            //Lifesteal
            if (!source.isIndirect()) {
                int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.LIFESTEAL.get(), attackingLivingEntity);
                if (level > 0) {
                    attackingLivingEntity.heal(level * LifestealEnchantment.LIFESTEAL_MULTIPLIER * Math.min(targetLivingEntity.getHealth(), damage));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKnockBackEvent(LivingKnockBackEvent event) {
        LivingEntity livingEntity = event.getEntity();
        //Sturdy
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.STURDY.get(), livingEntity);
        if (level > 0) {
            event.setStrength(event.getOriginalStrength() * SturdyEnchantment.getKnockbackMultiplier(level));
        }
    }

    @SubscribeEvent
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        //Lightweight
        if (livingEntity instanceof Player player) {
            player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(LightweightEnchantment.ATTACK_SPEED_MODIFIER_UUID);
            int level = player.getMainHandItem().getEnchantmentLevel(IEEnchantments.LIGHTWEIGHT.get());
            if (level > 0) {
                if (player.getAttribute(Attributes.ATTACK_SPEED) != null) {
                    AttributeModifier modifier = new AttributeModifier(LightweightEnchantment.ATTACK_SPEED_MODIFIER_UUID, "Attack speed boost", LightweightEnchantment.ATTACK_SPEED_INCREASE * level, AttributeModifier.Operation.ADDITION);
                    player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(modifier);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootLevelEvent(LootingLevelEvent event) {
        //Hunter
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