/*
package com.ipdnaeip.ipdnaeipenchantments.enchantment;

import com.ipdnaeip.ipdnaeipenchantments.util.IEEnchantmentProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.List;

public class IEEnchantment extends Enchantment {

    protected IEEnchantmentProperties properties = new IEEnchantmentProperties();

    public static final String MIN_LEVEL = "Min level";
    public static final String MAX_LEVEL = "Max level";
    public static final String TREASURE_ONLY = "Treasure only";
    public static final String TRADEABLE = "Tradeable";
    public static final String DISCOVERABLE = "Discoverable";
    public static final String CAN_APPLY_AT_ENCHANTING_TABLE = "Can apply at enchanting table";
    public static final String ALLOWED_ON_BOOKS = "Allowed on books";
    private int minLevel;
    private int maxLevel;
    private List<ResourceLocation> compatibilityList;
    private boolean treasureOnly;
    private boolean tradeable;
    private boolean discoverable;
    private boolean canApplyAtEnchantingTable;
    private boolean allowedOnBooks;

    public IEEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
        this.properties.createValue(MIN_LEVEL, 1).createValue(MAX_LEVEL, 3).createContext(TREASURE_ONLY, false).createContext(TRADEABLE, true).createContext(DISCOVERABLE, true).createContext(CAN_APPLY_AT_ENCHANTING_TABLE, true).createContext(ALLOWED_ON_BOOKS, true);
        this.minLevel = this.properties.getValue(MIN_LEVEL).intValue();
        this.maxLevel = this.properties.getValue(MAX_LEVEL).intValue();
        this.compatibilityList = this.properties.getCompatibility();
        this.treasureOnly = this.properties.getContext(TREASURE_ONLY);
    }

    @Override
    public int getMinLevel() {
        return this.properties.getValue(MIN_LEVEL).intValue();
    }

    @Override
    public int getMaxLevel() {
        return this.properties.getValue(MAX_LEVEL).intValue();
    }

*/
/*    @Override
    protected boolean checkCompatibility(Enchantment pOther) {

    }*//*


    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther);
    }

    @Override
    public boolean isTreasureOnly() {
        return this.properties.getContext(TREASURE_ONLY);
    }

    @Override
    public boolean isTradeable() {
        return this.properties.getContext(TRADEABLE);
    }

    @Override
    public boolean isDiscoverable() {
        return this.properties.getContext(DISCOVERABLE);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.properties.getContext(CAN_APPLY_AT_ENCHANTING_TABLE);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return this.properties.getContext(ALLOWED_ON_BOOKS);
    }

    public IEEnchantmentProperties getProperties() {
        return this.properties;
    }

}
*/
