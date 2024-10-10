package com.ipdaneip.ipdnaeipenchantments.registry;

import com.ipdaneip.ipdnaeipenchantments.IpdnaeipEnchantments;
import com.ipdaneip.ipdnaeipenchantments.enchantment.*;
import com.ipdaneip.ipdnaeipenchantments.util.IEUtils;
import net.minecraft.world.item.*;
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

    //Head
    public static final RegistryObject<Enchantment> GLUTTONY = DEFERRED_REGISTER.register("gluttony", GluttonyEnchantment::new);

    //Legs
    public static final RegistryObject<Enchantment> STURDY = DEFERRED_REGISTER.register("sturdy", SturdyEnchantment::new);

    //Melee
    public static final RegistryObject<Enchantment> CHARGING = DEFERRED_REGISTER.register("charging", ChargingEnchantment::new);
    public static final RegistryObject<Enchantment> LIGHTWEIGHT = DEFERRED_REGISTER.register("lightweight", LightweightEnchantment::new);

    //Shield
    public static final RegistryObject<Enchantment> PHALANX = DEFERRED_REGISTER.register("phalanx", PhalanxEnchantment::new);

    //Sword
    public static final RegistryObject<Enchantment> ICE_ASPECT = DEFERRED_REGISTER.register("ice_aspect", IceAspectEnchantment::new);
    public static final RegistryObject<Enchantment> LIFESTEAL = DEFERRED_REGISTER.register("lifesteal", LifestealEnchantment::new);

    //EnchantmentCategories

    public static final EnchantmentCategory MELEE = EnchantmentCategory.create("melee", item -> item instanceof TieredItem || item instanceof TridentItem);
    public static final EnchantmentCategory SHIELD = EnchantmentCategory.create("shield", item -> item instanceof ShieldItem);

    //EnchantmentCategoryRegistryObjects (necessary for projectile based enchantments)

    static {
        IEUtils.addEnchantmentROs(EnchantmentCategory.BOW, DRAW, HUNTER);
        IEUtils.addEnchantmentROs(EnchantmentCategory.FISHING_ROD, ANGLERS_ARM, HARPOONING);
    }

}
