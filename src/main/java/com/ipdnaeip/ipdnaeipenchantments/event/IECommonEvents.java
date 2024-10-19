package com.ipdnaeip.ipdnaeipenchantments.event;

import com.ipdnaeip.ipdnaeipenchantments.accessor.AbstractArrowAccessor;
import com.ipdnaeip.ipdnaeipenchantments.accessor.FishingHookAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.*;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.UUID;

@Mod.EventBusSubscriber
public class IECommonEvents {

/*    @SubscribeEvent
    public static void onArrowLooseEvent(ArrowLooseEvent event) {
        Player player = event.getEntity();
        int charge = event.getCharge();
        int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE, player);
        if (level > 0) {
            if (level < 5) {
                charge *= 25.0 / (25 - (5 * level));
            } else {
                charge = Integer.MAX_VALUE;
            }
            event.setCharge(charge);
        }
    }*/

    @SubscribeEvent
    public static void onEnderManAngerEvent(EnderManAngerEvent event) {
        Player player = event.getPlayer();
        if (EnchantmentHelper.getEnchantmentLevel(IEEnchantments.OBSCURING.get(), player) > 0) {
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public static void onEntityJoinLevelEvent(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        int level;
        if (entity instanceof Projectile projectile) {
            //Fishing Rod
            if (projectile instanceof FishingHook fishingHook) {
                //Angler's Arm
                level = ((FishingHookAccessor)fishingHook).getAnglersArmLevelIE();
                if (level > 0) {
                    float velocity_multiplier = 1 + (AnglerEnchantment.VELOCITY_MULTIPLIER * level);
                    fishingHook.setDeltaMovement(fishingHook.getDeltaMovement().scale(velocity_multiplier));
                }
            }
            if (projectile instanceof AbstractArrow arrow) {
                //Draw
                level = ((AbstractArrowAccessor)arrow).getDrawLevelIE();
                if (level > 0) {
                    float velocity_multiplier = 1 + (DrawEnchantment.VELOCITY_MULTIPLIER * level);
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(velocity_multiplier));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDropsEvent(LivingDropsEvent event) {
        Collection<ItemEntity> itemEntities = event.getDrops();
        Entity attacker = event.getSource().getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            if (EnchantmentHelper.getEnchantmentLevel(IEEnchantments.PORK_CHOPPER.get(), livingAttacker) > 0) {
                for (ItemEntity itemEntity : itemEntities) {
                    if (itemEntity.getItem().getItem() == Items.ROTTEN_FLESH) {
                        itemEntity.setItem(new ItemStack(Items.PORKCHOP, itemEntity.getItem().getCount()));
                    }
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
        AttributeInstance attributeinstance;
        UUID uuid;
        int level;
        attributeinstance = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attributeinstance != null) {
            //Lancing
            uuid = LancingEnchantment.DAMAGE_MULTIPLIER_MODIFIER_UUID;
            attributeinstance.removeModifier(uuid);
            if (livingEntity.isSprinting()) {
                level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.LANCING.get(), livingEntity);
                if (level > 0) {
                    if (livingEntity.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                        AttributeModifier modifier = new AttributeModifier(uuid, "Attack damage boost", LancingEnchantment.DAMAGE_MULTIPLIER * level, AttributeModifier.Operation.MULTIPLY_TOTAL);
                        attributeinstance.addTransientModifier(modifier);
                    }
                }
            }
            //Charging
            uuid = ChargingEnchantment.DAMAGE_MULTIPLIER_MODIFIER_UUID;
            attributeinstance.removeModifier(uuid);
            if (livingEntity.isSprinting()) {
                level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.CHARGING.get(), livingEntity);
                if (level > 0) {
                    if (livingEntity.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                        AttributeModifier modifier = new AttributeModifier(uuid, "Attack damage boost", ChargingEnchantment.DAMAGE_MULTIPLIER * level, AttributeModifier.Operation.MULTIPLY_TOTAL);
                        attributeinstance.addTransientModifier(modifier);
                    }
                }
            }
        }
        if (livingEntity instanceof Player player) {
            //Lightweight
            uuid = LightweightEnchantment.ATTACK_SPEED_MODIFIER_UUID;
            attributeinstance = player.getAttribute(Attributes.ATTACK_SPEED);
            if (attributeinstance != null) {
                attributeinstance.removeModifier(uuid);
                level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.LIGHTWEIGHT.get(), player);
                if (level > 0) {
                    AttributeModifier modifier = new AttributeModifier(uuid, "Attack speed boost", LightweightEnchantment.ATTACK_SPEED_INCREASE * level, AttributeModifier.Operation.ADDITION);
                    attributeinstance.addTransientModifier(modifier);
                }
            }
            //Lancing
            uuid = LancingEnchantment.ENTITY_REACH_MODIFIER_UUID;
            attributeinstance = player.getAttribute(ForgeMod.ENTITY_REACH.get());
            if (attributeinstance != null) {
                attributeinstance.removeModifier(uuid);
                if (player.isSprinting()) {
                    level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.LANCING.get(), player);
                    if (level > 0) {
                        AttributeModifier modifier = new AttributeModifier(uuid, "Lancing range boost", LancingEnchantment.RANGE_INCREASE * level, AttributeModifier.Operation.ADDITION);
                        attributeinstance.addTransientModifier(modifier);
                    }
                }
            }
            //Reach
            uuid = ReachEnchantment.BLOCK_REACH_MODIFIER_UUID;
            attributeinstance = player.getAttribute(ForgeMod.BLOCK_REACH.get());
            if (attributeinstance != null) {
                attributeinstance.removeModifier(uuid);
                level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.REACH.get(), player);
                if (level > 0) {
                    AttributeModifier modifier = new AttributeModifier(uuid, "Reach range boost", ReachEnchantment.BLOCK_REACH_DISTANCE * level, AttributeModifier.Operation.ADDITION);
                    attributeinstance.addTransientModifier(modifier);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootLevelEvent(LootingLevelEvent event) {
        DamageSource damageSource = event.getDamageSource();
        if (damageSource != null) {
            Entity directEntity = event.getDamageSource().getDirectEntity();
            //Hunter
            if (directEntity instanceof AbstractArrow arrow) {
                int level = ((AbstractArrowAccessor)arrow).getHunterLevelIE();
                if (level > 0) {
                    event.setLootingLevel(Math.max(event.getLootingLevel(), level));
                }
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
                    int level = ((FishingHookAccessor)fishingHook).getHarpooningLevelIE();
                    if (level > 0) {
                        float damage = 1f + (HarpooningEnchantment.DAMAGE_MULTIPLIER * level);
                        livingEntity.hurt(livingEntity.damageSources().indirectMagic(fishingHook, owner), damage);
                    }
                }
            }
        }
    }

}