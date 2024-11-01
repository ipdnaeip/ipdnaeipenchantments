package com.ipdnaeip.ipdnaeipenchantments.registry;

import com.ipdnaeip.ipdnaeipenchantments.IpdnaeipEnchantments;
import com.ipdnaeip.ipdnaeipenchantments.enchantment.enchantments.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IEEnchantments {

    //Enchantments

    public static final DeferredRegister<Enchantment> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, IpdnaeipEnchantments.MOD_ID);

    //Bow
    public static final RegistryObject<Enchantment> AERODYNAMICS = DEFERRED_REGISTER.register("aerodynamics", AerodynamicsEnchantment::new);
    public static final RegistryObject<Enchantment> AQUADYNAMICS = DEFERRED_REGISTER.register("aquadynamics", AquadynamicsEnchantment::new);
    public static final RegistryObject<Enchantment> DRAW = DEFERRED_REGISTER.register("draw", DrawEnchantment::new);
    public static final RegistryObject<Enchantment> HUNTER = DEFERRED_REGISTER.register("hunter", HunterEnchantment::new);
    public static final RegistryObject<Enchantment> MARKSMAN = DEFERRED_REGISTER.register("marksman", MarksmanEnchantment::new);
    public static final RegistryObject<Enchantment> RANGER = DEFERRED_REGISTER.register("ranger", RangerEnchantment::new);
    public static final RegistryObject<Enchantment> SNIPER = DEFERRED_REGISTER.register("sniper", SniperEnchantment::new);

    //Chest
    public static final RegistryObject<Enchantment> ACCUMULATING = DEFERRED_REGISTER.register("accumulating", AccumulatingEnchantment::new);

    //Crossbow
    public static final RegistryObject<Enchantment> PRECISION = DEFERRED_REGISTER.register("precision", PrecisionEnchantment::new);

    //Digger
    public static final RegistryObject<Enchantment> REACH = DEFERRED_REGISTER.register("reach", ReachEnchantment::new);

    //Feet
    public static final RegistryObject<Enchantment> BOOSTING = DEFERRED_REGISTER.register("boosting", BoostingEnchantment::new);
    public static final RegistryObject<Enchantment> LIGHTFOOT = DEFERRED_REGISTER.register("lightfoot", LightfootEnchantment::new);

    //Fishing Rod
    public static final RegistryObject<Enchantment> ANGLER = DEFERRED_REGISTER.register("angler", AnglerEnchantment::new);
    public static final RegistryObject<Enchantment> HARPOONING = DEFERRED_REGISTER.register("harpooning", HarpooningEnchantment::new);
    public static final RegistryObject<Enchantment> REELING = DEFERRED_REGISTER.register("reeling", ReelingEnchantment::new);

    //Pickaxe
    public static final RegistryObject<Enchantment> SMASHING = DEFERRED_REGISTER.register("smashing", SmashingEnchantment::new);

    //Shovel
    public static final RegistryObject<Enchantment> SWATTING = DEFERRED_REGISTER.register("swatting", SwattingEnchantment::new);

    //Head
    public static final RegistryObject<Enchantment> GLUTTONY = DEFERRED_REGISTER.register("gluttony", GluttonyEnchantment::new);
    public static final RegistryObject<Enchantment> METABOLIZE = DEFERRED_REGISTER.register("metabolize", MetabolizeEnchantment::new);
    public static final RegistryObject<Enchantment> OBSCURING = DEFERRED_REGISTER.register("obscuring", ObscuringEnchantment::new);

    //Legs
    public static final RegistryObject<Enchantment> ACROBATICS = DEFERRED_REGISTER.register("acrobatics", AcrobaticsEnchantment::new);
    public static final RegistryObject<Enchantment> STURDY = DEFERRED_REGISTER.register("sturdy", SturdyEnchantment::new);

    //Melee
    public static final RegistryObject<Enchantment> CHARGING = DEFERRED_REGISTER.register("charging", ChargingEnchantment::new);
    public static final RegistryObject<Enchantment> LIGHTWEIGHT = DEFERRED_REGISTER.register("lightweight", LightweightEnchantment::new);
    public static final RegistryObject<Enchantment> QUICKDRAW = DEFERRED_REGISTER.register("quickdraw", QuickdrawEnchantment::new);

    //Shield
    public static final RegistryObject<Enchantment> PHALANX = DEFERRED_REGISTER.register("phalanx", PhalanxEnchantment::new);
    public static final RegistryObject<Enchantment> REBOUNDING = DEFERRED_REGISTER.register("rebounding", ReboundingEnchantment::new);

    //Sword
    public static final RegistryObject<Enchantment> ICE_ASPECT = DEFERRED_REGISTER.register("ice_aspect", IceAspectEnchantment::new);
    public static final RegistryObject<Enchantment> LIFESTEAL = DEFERRED_REGISTER.register("lifesteal", LifestealEnchantment::new);
    public static final RegistryObject<Enchantment> PORK_CHOPPER = DEFERRED_REGISTER.register("pork_chopper", PorkChopperEnchantment::new);

    //Trident
    public static final RegistryObject<Enchantment> LANCING = DEFERRED_REGISTER.register("lancing", LancingEnchantment::new);

}
