package com.ipdnaeip.ipdnaeipenchantments.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class IEConfig {

    public final ForgeConfigSpec.BooleanValue drawReduceDamage;
    public final ForgeConfigSpec.BooleanValue sniperRelativeZoom;
    public final ForgeConfigSpec.BooleanValue enchantableShields;
    public final ForgeConfigSpec.BooleanValue crossbowEnchantmentsOnBows;
    public final ForgeConfigSpec.BooleanValue bowEnchantmentsOnCrossbows;
//    public final ForgeConfigSpec.BooleanValue quickchargePiercingBow;
    public final ForgeConfigSpec.BooleanValue piercingBow;
    public final ForgeConfigSpec.BooleanValue powerPunchFlameCrossbow;

    public IEConfig(final ForgeConfigSpec.Builder builder) {

        this.drawReduceDamage = builder.comment("Should the damage of draw arrows be reduced to account for the increased damage due to increased velocity:").translation("draw_reduce_damage").define("draw_increase_damage", true);
        this.sniperRelativeZoom = builder.comment("Should the sniper enchantment zoom be relative to the player's FOV scale:").translation("sniper_relative_zoom").define("sniper_relative_zoom", true);
        this.enchantableShields = builder.comment("Should shields be able to be enchanted at enchanting tables:").translation("enchantable_shields").define("enchantable_shields", true);
        this.crossbowEnchantmentsOnBows = builder.comment("Should this mod's crossbow enchantments be allowed on bows at the enchanting table:").translation("crossbow_enchantments_on_bows").define("crossbow_enchantments_on_bows", false);
        this.bowEnchantmentsOnCrossbows = builder.comment("Should this mod's bow enchantments be allowed on crossbows at the enchanting table:").translation("bow_enchantments_on_crossbows").define("bow_enchantments_on_crossbows", false);
//        this.quickchargePiercingBow = builder.comment("Should quick charge and piercing be able to be applied to and work on bows:").translation("quickcharge_piercing_bow").define("quickcharge_piercing_bow", false);
        this.piercingBow = builder.comment("Should piercing be able to be applied to and work on bows:").translation("piercing_bow").define("piercing_bow", false);
        this.powerPunchFlameCrossbow = builder.comment("Should power, punch, and flame be able to be applied to and work on crossbows:").translation("power_punch_flame_crossbow").define("power_punch_flame_crossbow", false);

    }

}
