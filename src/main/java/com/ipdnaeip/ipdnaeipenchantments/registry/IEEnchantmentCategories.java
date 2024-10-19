package com.ipdnaeip.ipdnaeipenchantments.registry;

import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class IEEnchantmentCategories {

    public static final EnchantmentCategory MELEE = EnchantmentCategory.create("melee", item -> item instanceof TieredItem || item instanceof TridentItem);
    //public static final EnchantmentCategory POINTED_WEAPON = EnchantmentCategory.create("pointed_weapon", item -> item instanceof SwordItem || item instanceof TridentItem);
    public static final EnchantmentCategory SHIELD = EnchantmentCategory.create("shield", item -> item instanceof ShieldItem);

}
