package com.ipdnaeip.ipdnaeipenchantments.util;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Add default value for each value/context and add whether the enchantment is enabled

public class IEEnchantmentProperties {

    private final Map<String, Number> values = new HashMap<>();

    private final Map<String, Boolean> contexts = new HashMap<>();

    private final List<ResourceLocation> compatibility = new ArrayList<>();

    public IEEnchantmentProperties() {
    }

    //Values

    public Map<String, Number> getValues() {
        return this.values;
    }

    public IEEnchantmentProperties createValue(String key, Number value) {
        if (this.values.containsKey(key)) {
            throw new IllegalArgumentException("Attempting to add a key that already exists.");
        } else {
            this.values.put(key, value);
        }
        return this;
    }

    public IEEnchantmentProperties setValue(String key, Number value) {
        if (!this.values.containsKey(key)) {
            throw new IllegalArgumentException("Map does not contain key to set");
        } else {
            this.values.put(key, value);
        }
        return this;
    }

    public Number getValue(String key) {
        if (!this.values.containsKey(key)) {
            throw new IllegalArgumentException("Map does not contain key to get value");
        }
        return this.values.get(key);
    }

    //Contexts

    public Map<String, Boolean> getContexts() {
        return this.contexts;
    }

    public IEEnchantmentProperties createContext(String key, Boolean value) {
        if (this.contexts.containsKey(key)) {
            throw new IllegalArgumentException("Attempting to add a key that already exists.");
        } else {
            this.contexts.put(key, value);
        }
        return this;
    }

    public IEEnchantmentProperties setContext(String key, Boolean value) {
        if (!this.contexts.containsKey(key)) {
            throw new IllegalArgumentException("Map does not contain key to set");
        } else {
            this.contexts.put(key, value);
        }
        return this;
    }

    public Boolean getContext(String key) {
        if (!this.contexts.containsKey(key)) {
            throw new IllegalArgumentException("Map does not contain key to get value");
        }
        return this.contexts.get(key);
    }

    //Compatibility

    public List<ResourceLocation> getCompatibility() {
        return this.compatibility;
    }

}
