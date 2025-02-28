package com.ipdnaeip.ipdnaeipenchantments.mixin;

import com.ipdnaeip.ipdnaeipenchantments.accessor.FireworkRocketEntityAccessor;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin implements FireworkRocketEntityAccessor {

    private int accelerantLevelIE = 0;

    @Override
    public int getAccelerantLevelIE() {
        return accelerantLevelIE;
    }

    @Override
    public void setAccelerantLevelIE(int level) {
        accelerantLevelIE = level;
    }
}
