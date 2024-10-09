package com.ipdaneip.ipdnaeipenchantments.util;

import com.ipdaneip.ipdnaeipenchantments.registry.IEEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

public class IEUtils {

    private static final Map<Enum<EnchantmentCategory>, List<RegistryObject<Enchantment>>> enchantmentROMapping = new HashMap<>();
    private static final Map<Object, List<Object>> objectMap = new HashMap<>();

    public static void addObjectKeyMap(Object objectKey, Object[]... objects) {
        List<Object> keyList = objectMap.get(objectKey);
        List<Object> keyListConverted = Arrays.asList(objects);
        if (keyList == null) {
            keyList = keyListConverted;
        } else {
            keyList.addAll(keyListConverted);
        }
        objectMap.put(objectKey, keyList);
    }

/*    @SafeVarargs
    public static void addCategoryROMap(EnchantmentCategory enchantmentCategory, RegistryObject<Enchantment>... enchantments) {
        addObjectKeyMap(enchantmentCategory, enchantments);
    }

    public static List<RegistryObject<Enchantment>> getROsInCategory(EnchantmentCategory enchantmentCategory) {
        return objectMap.get(enchantmentCategory);
    }*/

    @SafeVarargs
    public static void addEnchantmentROs(EnchantmentCategory enchantmentCategory, RegistryObject<Enchantment>... enchantments) {
        List<RegistryObject<Enchantment>> enchantmentROs = enchantmentROMapping.get(enchantmentCategory);
        List<RegistryObject<Enchantment>> enchantmentROsConverted = Arrays.asList(enchantments);
        if (enchantmentROs == null) {
            enchantmentROs = enchantmentROsConverted;
        } else {
            enchantmentROs.addAll(enchantmentROsConverted);
        }
        enchantmentROMapping.put(enchantmentCategory, enchantmentROs);
    }

    public static List<RegistryObject<Enchantment>> getROsInCategory(EnchantmentCategory enchantmentCategory) {
        return enchantmentROMapping.get(enchantmentCategory);
    }

    public static void applyProjectileEnchantments(Projectile projectile, LivingEntity owner, EnchantmentCategory enchantmentCategory) {
        if (enchantmentCategory.canEnchant(owner.getMainHandItem().getItem())) {
            for (RegistryObject<Enchantment> enchantment : getROsInCategory(enchantmentCategory)) {
                int level = owner.getMainHandItem().getEnchantmentLevel(enchantment.get());
                if (level > 0) {
                    projectile.getPersistentData().putInt(enchantment.getId().toString(), level);
                }
            }
        } else if (enchantmentCategory.canEnchant(owner.getOffhandItem().getItem())) {
            for (RegistryObject<Enchantment> enchantment : getROsInCategory(enchantmentCategory)) {
                int level = owner.getOffhandItem().getEnchantmentLevel(enchantment.get());
                if (level > 0) {
                    projectile.getPersistentData().putInt(enchantment.getId().toString(), level);
                }
            }
        }
    }

    public static boolean hasEnchantment(Entity entity, RegistryObject<Enchantment> registryObject) {
        return entity.getPersistentData().get(registryObject.getId().toString()) != null;
    }

    public static int getEnchantmentLevel(Entity entity, RegistryObject<Enchantment> registryObject) {
        return entity.getPersistentData().getInt(registryObject.getId().toString());
    }

}
