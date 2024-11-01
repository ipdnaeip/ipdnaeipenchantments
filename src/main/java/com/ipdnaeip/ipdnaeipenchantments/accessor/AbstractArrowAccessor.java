package com.ipdnaeip.ipdnaeipenchantments.accessor;

import net.minecraft.world.item.ItemStack;

public interface AbstractArrowAccessor {

    //Getters

    int getAerodynamicsLevelIE();

    int getAquadynamicsLevelIE();

    int getDrawLevelIE();

    int getHunterLevelIE();

    int getMarksmanLevelIE();

    ItemStack getPickupItem_IE();

    //Setters

    void setAerodynamicsLevelIE(int level);

    void setAquadynamicsLevelIE(int level);

    void setDrawLevelIE(int level);

    void setHunterLevelIE(int level);

    void setMarksmanLevelIE(int level);

}
