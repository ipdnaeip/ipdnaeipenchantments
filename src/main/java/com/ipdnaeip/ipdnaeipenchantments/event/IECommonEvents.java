package com.ipdnaeip.ipdnaeipenchantments.event;

import com.ipdnaeip.ipdnaeipenchantments.accessor.AbstractArrowAccessor;
import com.ipdnaeip.ipdnaeipenchantments.accessor.FireworkRocketEntityAccessor;
import com.ipdnaeip.ipdnaeipenchantments.accessor.FishingHookAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.*;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class IECommonEvents {

    @SubscribeEvent
    public static void onAnvilUpdateEvent(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        Map<Enchantment, Integer> leftEnchantments = EnchantmentHelper.getEnchantments(left);
        Map<Enchantment, Integer> rightEnchantments = EnchantmentHelper.getEnchantments(right);
        //Obtaining tightening via an anvil
        if (left.getItem() == Items.ENCHANTED_BOOK && !leftEnchantments.isEmpty() && right.getItem() == Items.ENCHANTED_BOOK &&  !rightEnchantments.isEmpty()) {
            if (leftEnchantments.size() == 1 && rightEnchantments.size() == 1) {
                int level = 0;
                //multishot in left slot and marksman in right slot
                if (leftEnchantments.containsKey(Enchantments.MULTISHOT) && rightEnchantments.containsKey(IEEnchantments.MARKSMAN.get())) {
                    level = rightEnchantments.get(IEEnchantments.MARKSMAN.get());
                //marksman in left slot and multishot in right slot
                } else if (leftEnchantments.containsKey(IEEnchantments.MARKSMAN.get()) && rightEnchantments.containsKey(Enchantments.MULTISHOT)) {
                    level = leftEnchantments.get(IEEnchantments.MARKSMAN.get());
                }
                if (level > 0) {
                    ItemStack output = new ItemStack(Items.ENCHANTED_BOOK);
                    EnchantmentInstance enchantmentInstance = new EnchantmentInstance(IEEnchantments.PRECISION.get(), level);
                    EnchantedBookItem.addEnchantment(output, enchantmentInstance);
                    event.setCost(level * PrecisionEnchantment.ANVIL_COST_PER_LEVEL);
                    event.setOutput(output);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEnderManAngerEvent(EnderManAngerEvent event) {
        Player player = event.getPlayer();
        //Obscuring
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
            //Draw
            if (projectile instanceof AbstractArrow arrow) {
                level = ((AbstractArrowAccessor)arrow).getDrawLevelIE();
                if (level > 0) {
                    float velocity_multiplier = 1 + (DrawEnchantment.VELOCITY_MULTIPLIER * level);
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(velocity_multiplier));
                }
            }
            //Accelerant
            if (projectile instanceof FireworkRocketEntity fireworkRocketEntity) {
                level = ((FireworkRocketEntityAccessor)fireworkRocketEntity).getAccelerantLevelIE();
                if (level > 0) {
                    float velocity_multiplier = 1 + (AccelerantEnchantment.VELOCITY_MULTIPLIER * level);
                    fireworkRocketEntity.setDeltaMovement(fireworkRocketEntity.getDeltaMovement().scale(velocity_multiplier));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDropsEvent(LivingDropsEvent event) {
        Collection<ItemEntity> itemEntities = event.getDrops();
        Entity attacker = event.getSource().getEntity();
        LivingEntity target = event.getEntity();
        Level level = target.level();
        int lootLevel = event.getLootingLevel();
        if (attacker instanceof LivingEntity livingAttacker) {
            //Pork chopper
            if (EnchantmentHelper.getEnchantmentLevel(IEEnchantments.PORK_CHOPPER.get(), livingAttacker) > 0) {
                for (ItemEntity itemEntity : itemEntities) {
                    if (itemEntity.getItem().getItem() == Items.ROTTEN_FLESH) {
                        Item drop = Items.PORKCHOP;
                        if (target.isOnFire()) {
                            drop = Items.COOKED_PORKCHOP;
                        }
                        itemEntity.setItem(new ItemStack(drop, itemEntity.getItem().getCount()));
                    }
                }
            }
            //Injects obscuring enchantment book drop
            if (target.getType() == EntityType.ENDERMAN) {
                if (livingAttacker.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items.CARVED_PUMPKIN) {
                    if (target.getRandom().nextFloat() < ObscuringEnchantment.BOOK_DROP_CHANCE + (ObscuringEnchantment.BOOK_DROP_CHANCE_PER_LOOTING_LEVEL * lootLevel)) {
                        ItemStack obscuringBook = new ItemStack(Items.ENCHANTED_BOOK);
                        //Enchantment level has to be > 0 or the text will be ugly
                        EnchantmentInstance obscuringInstance = new EnchantmentInstance(IEEnchantments.OBSCURING.get(), 1);
                        EnchantedBookItem.addEnchantment(obscuringBook, obscuringInstance);
                        ItemEntity obscuringBookEntity = new ItemEntity(level, target.getX(), target.getY(), target.getZ(), obscuringBook);
                        level.addFreshEntity(obscuringBookEntity);
                    }
                }
            }
            //Injects pork chopper enchantment book drop
            if (target.getType() == EntityType.PIGLIN_BRUTE) {
                if (target.getRandom().nextFloat() < PorkChopperEnchantment.BOOK_DROP_CHANCE + (PorkChopperEnchantment.BOOK_DROP_CHANCE_PER_LOOTING_LEVEL * lootLevel)) {
                    ItemStack porkChopperBook = new ItemStack(Items.ENCHANTED_BOOK);
                    //Enchantment level has to be > 0 or the text will be ugly
                    EnchantmentInstance porkChopperInstance = new EnchantmentInstance(IEEnchantments.PORK_CHOPPER.get(), 1);
                    EnchantedBookItem.addEnchantment(porkChopperBook, porkChopperInstance);
                    ItemEntity porkChopperBookEntity = new ItemEntity(level, target.getX(), target.getY(), target.getZ(), porkChopperBook);
                    level.addFreshEntity(porkChopperBookEntity);
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
                event.setDuration(Math.max(1, (int)((float)event.getDuration() * (1 - (GluttonyEnchantment.DURATION_REDUCTION * ((float) level))))));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();
        LivingEntity target = event.getEntity();
        float damage = event.getAmount();
        if (damage > 0 && attacker instanceof LivingEntity livingAttacker) {
            ItemStack itemStack = livingAttacker.getMainHandItem();
            if (!source.isIndirect()) {
                //Lifesteal
                int level = EnchantmentHelper.getTagEnchantmentLevel(IEEnchantments.LIFESTEAL.get(), itemStack);
                if (level > 0) {
                    livingAttacker.heal(level * LifestealEnchantment.LIFESTEAL_MULTIPLIER * Math.min(target.getHealth(), damage));
                }
                //Swatting
                level = EnchantmentHelper.getTagEnchantmentLevel(IEEnchantments.SWATTING.get(), itemStack);
                if (level > 0) {
                    if (!target.onGround()) {
                        event.setAmount(SwattingEnchantment.DAMAGE_MULTIPLIER * event.getAmount());
                        target.addDeltaMovement(new Vec3(0, SwattingEnchantment.PULL_DOWN, 0));
                        target.hasImpulse = true;
                        livingAttacker.level().playSound(null, target.blockPosition(), SoundEvents.ANVIL_LAND, SoundSource.NEUTRAL, 0.5f, 1f);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingJumpEvent(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        //Adds velocity to a entity's movement when jumping, depending on forward and strafe movement
        int level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.ACROBATICS.get(), entity);
        if (level > 0) {
            if (entity.xxa != 0 || entity.zza != 0) {
                double increase = AcrobaticsEnchantment.JUMP_INCREASE * level;
                float yaw = entity.getYRot();
                double strafeX = entity.xxa * Mth.cos((yaw) * (float) Math.PI / 180.0F);
                double strafeZ = entity.xxa * Mth.sin((yaw) * (float) Math.PI / 180.0F);
                double forwardX = -entity.zza * Mth.sin((yaw) * (float) Math.PI / 180.0F);
                double forwardZ = entity.zza * Mth.cos((yaw) * (float) Math.PI / 180.0F);
                Vec3 movement = new Vec3(strafeX + forwardX, 0, strafeZ + forwardZ).normalize().scale(increase);
                entity.setDeltaMovement(entity.getDeltaMovement().add(movement));
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
    public static void onLootTableLoadEvent(LootTableLoadEvent event) {
        //Accumulating fletcher gift loot injection
        if (event.getName().equals(BuiltInLootTables.FLETCHER_GIFT)) {
            LootPool lootPool = LootPool.lootPool().add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder().withEnchantment(IEEnchantments.ACCUMULATING.get(), UniformGenerator.between(IEEnchantments.PRECISION.get().getMinLevel(), IEEnchantments.PRECISION.get().getMaxLevel())))).when(LootItemRandomChanceCondition.randomChance(AccumulatingEnchantment.REWARD_CHANCE)).build();
            event.getTable().addPool(lootPool);
        }
    }

    @SubscribeEvent
    public static void onProjectileImpactEvent(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Entity owner = projectile.getOwner();
        int level;
        //Accumulating
        if (owner instanceof Player player) {
            if (projectile instanceof AbstractArrow arrow) {
                level = EnchantmentHelper.getEnchantmentLevel(IEEnchantments.ACCUMULATING.get(), player);
                if (level > 0) {
                    ItemStack itemStack = ((AbstractArrowAccessor)arrow).getPickupItem_IE();
                    if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY) {
                        if (player.getRandom().nextFloat() <= level / (level + 1f)) {
                            player.addItem(itemStack);
                            arrow.discard();
                        }
                    }
                    else {
                        player.addItem(itemStack);
                        arrow.discard();
                    }
                }
            }
        }
        if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult)(event.getRayTraceResult())).getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                //Harpooning
                if (projectile instanceof FishingHook fishingHook && owner instanceof LivingEntity livingOwner) {
                    level = ((FishingHookAccessor)fishingHook).getHarpooningLevelIE();
                    if (level > 0) {
                        float damage = 1f + (HarpooningEnchantment.DAMAGE_MULTIPLIER * level);
                        livingEntity.hurt(livingEntity.damageSources().indirectMagic(fishingHook, livingOwner), damage);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onShieldBlockEvent(ShieldBlockEvent event) {
        LivingEntity defender = event.getEntity();
        DamageSource source = event.getDamageSource();
        if (source.getDirectEntity() instanceof LivingEntity attacker) {
            int level = defender.getUseItem().getEnchantmentLevel(IEEnchantments.REBOUNDING.get());
            if (level > 0) {
                attacker.knockback(ReboundingEnchantment.KNOCKBACK_BASE + level * ReboundingEnchantment.KNOCKBACK_INCREASE, defender.getX() - attacker.getX(), defender.getZ() - attacker.getZ());
            }
        }
    }

}