package com.ipdaneip.ipdnaeipenchantments.registry;

import com.ipdaneip.ipdnaeipenchantments.IpdnaeipEnchantments;
import com.ipdaneip.ipdnaeipenchantments.enchantment.*;
import com.ipdaneip.ipdnaeipenchantments.util.IEUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IEEnchantments {

    //Enchantments

    public static final DeferredRegister<Enchantment> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, IpdnaeipEnchantments.MOD_ID);

    //Bow
    public static final RegistryObject<Enchantment> DRAW = DEFERRED_REGISTER.register("draw", DrawEnchantment::new);
    public static final RegistryObject<Enchantment> HUNTER = DEFERRED_REGISTER.register("hunter", HunterEnchantment::new);

    //Fishing Rod
    public static final RegistryObject<Enchantment> ANGLERS_ARM = DEFERRED_REGISTER.register("anglers_arm", AnglersArmEnchantment::new);
    public static final RegistryObject<Enchantment> HARPOONING = DEFERRED_REGISTER.register("harpooning", HarpooningEnchantment::new);

    //Sword
    public static final RegistryObject<Enchantment> ICE_ASPECT = DEFERRED_REGISTER.register("ice_aspect", IceAspectEnchantment::new);

    //EnchantmentCategories

    //public static final EnchantmentCategory MELEE = EnchantmentCategory.create("melee", item -> item )
    public static final EnchantmentCategory SHIELD = EnchantmentCategory.create("shield", item -> item == Items.SHIELD);

    //EnchantmentCategoryRegistryObjects (necessary for projectile based enchantments)

    static {
        IEUtils.addEnchantmentROs(EnchantmentCategory.BOW, DRAW, HUNTER);
        IEUtils.addEnchantmentROs(EnchantmentCategory.FISHING_ROD, ANGLERS_ARM, HARPOONING);
    }

}
