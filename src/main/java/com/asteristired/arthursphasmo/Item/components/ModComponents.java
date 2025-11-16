package com.asteristired.arthursphasmo.Item.components;

import com.asteristired.arthursphasmo.Item.items.ModItems;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

import static com.asteristired.arthursphasmo.ArthursPhasmo.MOD_ID;

public class ModComponents {
    public static final ComponentType<Boolean> SWITCHED_ON = register("switched_on", booleanBuilder -> booleanBuilder.codec(Codec.BOOL));

    // https://github.com/natdertale/VeilFlashlight/blob/main/src/main/java/natdertale/flashlights/component/ModComponents.java
    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MOD_ID, name), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void Initalise() {}
    public static void attachComponents() {
        DefaultItemComponentEvents.MODIFY.register((modifyContext -> {
            modifyContext.modify(ModItems.FLASHLIGHT, builder -> builder.add(SWITCHED_ON, false));
        }));
    }

}
