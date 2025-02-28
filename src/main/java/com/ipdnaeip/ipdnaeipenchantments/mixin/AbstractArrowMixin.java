package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import com.ipdnaeip.ipdnaeipenchantments.accessor.AbstractArrowAccessor;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.AerodynamicsEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.AquadynamicsEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.DrawEnchantment;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.MarksmanEnchantment;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin implements AbstractArrowAccessor {

    private int aerodynamicsLevelIE = 0;
    private int aquadynamicsLevelIE = 0;
    private int drawLevelIE = 0;
    private int hunterLevelIE = 0;
    private int marksmanLevelIE = 0;

    @Shadow
    protected abstract ItemStack getPickupItem();

    //Getters

    @Override
    public int getAerodynamicsLevelIE() {
        return this.aerodynamicsLevelIE;
    }

    @Override
    public int getAquadynamicsLevelIE() {
        return this.aquadynamicsLevelIE;
    }

    @Override
    public int getDrawLevelIE() {
        return this.drawLevelIE;
    }

    @Override
    public int getHunterLevelIE() {
        return this.hunterLevelIE;
    }

    @Override
    public int getMarksmanLevelIE() {
        return this.marksmanLevelIE;
    }

    @Override
    public ItemStack getPickupItem_IE() {
        return this.getPickupItem();
    }

    //Setters

    @Override
    public void setAerodynamicsLevelIE(int level) {
        this.aerodynamicsLevelIE = level;
    }

    @Override
    public void setAquadynamicsLevelIE(int level) {
        this.aquadynamicsLevelIE = level;
    }

    @Override
    public void setDrawLevelIE(int level) {
        this.drawLevelIE = level;
    }

    @Override
    public void setHunterLevelIE(int level) {
        this.hunterLevelIE = level;
    }

    @Override
    public void setMarksmanLevelIE(int level) {
        this.marksmanLevelIE = level;
    }

    //Applies the damage reduction to draw arrows if disallowed in the config
    //Ignore the IntelliJ errors
    @ModifyVariable(method = "onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "STORE", ordinal = 0))
    private float modifyOnEntityHit(float f) {
        if (IpdnaeipEnchantments.IE_CONFIG.drawReduceDamage.get()) {
            int level = this.getDrawLevelIE();
            if (level > 0) {
                f /= 1 + (DrawEnchantment.VELOCITY_MULTIPLIER * level);
            }
        }
        return f;
    }

    //Reduces the effect of gravity on the arrow with aerodynamics
    //THANK YOU FABRIC DISCORD AND WARJORT FROM THE MINECRAFTFORGE FORUMS
    @ModifyConstant(method = "tick()V", constant = @Constant(doubleValue = 0.05000000074505806D))
    private double modifyTick(double f) {
        AbstractArrow arrow = (AbstractArrow)(Object)this;
        int level = ((AbstractArrowAccessor)arrow).getAerodynamicsLevelIE();
        if (level > 0) {
            f -= f * level / AerodynamicsEnchantment.GRAVITY_REDUCTION;
            arrow.hasImpulse = true;
        }
        return f;
    }

    //Increases arrow velocity under water with aquadynamics
    @Inject(method = "getWaterInertia()F", at = @At("RETURN"), cancellable = true)
    private void injectGetEnchantmentValue(CallbackInfoReturnable<Float> info) {
        int level = this.aquadynamicsLevelIE;
        if (level > 0) {
            info.setReturnValue(info.getReturnValue() + (level * AquadynamicsEnchantment.VELOCITY_INCREASE));
        }
    }

    //Reduces arrow inaccuracy with marksman
    @ModifyVariable(method = "shoot(DDDFF)V", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private float modifyShoot(float f) {
        int level = this.marksmanLevelIE;
        if (level > 0) {
            f *= Math.max(1 - (level * MarksmanEnchantment.INACCURACY_DECREASE), 0);
        }
        return f;
    }

}
