package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.accessor.FishingHookAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.ReelingEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin implements FishingHookAccessor {

    private int anglersArmLevelIE = 0;
    private int harpooningLevelIE = 0;
    private int reelingLevelIE = 0;

    @Override
    public int getAnglersArmLevelIE() {
        return this.anglersArmLevelIE;
    }

    @Override
    public int getHarpooningLevelIE() {
        return this.harpooningLevelIE;
    }

    @Override
    public int getReelingLevelIE() {
        return this.reelingLevelIE;
    }

    @Override
    public void setAnglersArmLevelIE(int level) {
        this.anglersArmLevelIE = level;
    }

    @Override
    public void setHarpooningLevelIE(int level) {
        this.harpooningLevelIE = level;
    }

    @Override
    public void setReelingLevelIE(int level) {
        this.reelingLevelIE = level;
    }

    //Applies fishing rod enchantments to fishing hook entity
    @Inject(method = "Lnet/minecraft/world/entity/projectile/FishingHook;<init>(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;II)V", at = @At("TAIL"))
    public void injectFishingHook(Player player, Level level, int luck, int lureSpeed, CallbackInfo info) {
        this.setAnglersArmLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.ANGLER.get(), player));
        this.setHarpooningLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.HARPOONING.get(), player));
        this.setReelingLevelIE(EnchantmentHelper.getEnchantmentLevel(IEEnchantments.REELING.get(), player));
    }

    //IGNORE THE ERRORS
    //Increases pull velocity of fishing hooks with reeling
    @ModifyVariable(method = "Lnet/minecraft/world/entity/projectile/FishingHook;pullEntity(Lnet/minecraft/world/entity/Entity;)V", at = @At("STORE"))
    public Vec3 modifyPullEntity(Vec3 vec3) {
        if (this.reelingLevelIE > 0) {
            double scale = this.reelingLevelIE * ReelingEnchantment.PULL_INCREASE;
            double xzScale = 1 + scale;
            double yScale = 1 + (scale / 3D);
            return vec3.multiply(new Vec3(xzScale, yScale, xzScale));
        } else {
            return vec3;
        }
    }

}
