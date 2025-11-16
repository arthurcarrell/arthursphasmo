package com.asteristired.arthursphasmo.Item.items;

import com.asteristired.arthursphasmo.Enums.GhostBehaviorState;
import com.asteristired.arthursphasmo.Item.items.debug.GhostTrigger;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.asteristired.arthursphasmo.ArthursPhasmo.MOD_ID;

public class ModItems {

    public static Flashlight FLASHLIGHT = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "flashlight"), new Flashlight(new Item.Settings()
            .maxCount(1)));

    public static GhostTrigger GHOST_IDLE_TRIGGER = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ghost_idle_trigger"), new GhostTrigger(new Item.Settings()
            .maxCount(1), GhostBehaviorState.IDLE));
    public static GhostTrigger GHOST_HUNTING_TRIGGER = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ghost_hunting_trigger"), new GhostTrigger(new Item.Settings()
            .maxCount(1), GhostBehaviorState.HUNTING));
    public static GhostTrigger GHOST_INTERACTING_TRIGGER = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ghost_interacting_trigger"), new GhostTrigger(new Item.Settings()
            .maxCount(1), GhostBehaviorState.INTERACTING));
    public static GhostTrigger GHOST_MANIFESTING_TRIGGER = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ghost_manifesting_trigger"), new GhostTrigger(new Item.Settings()
            .maxCount(1), GhostBehaviorState.MANIFESTING));
    public static GhostTrigger GHOST_WANDERING_TRIGGER = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ghost_wandering_trigger"), new GhostTrigger(new Item.Settings()
            .maxCount(1), GhostBehaviorState.WANDERING));


    // Create the ItemGroup
    public static final RegistryKey<ItemGroup> ARTHURSPHASMO_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MOD_ID, "arthursphasmo"));
    public static final ItemGroup ARTHURSPHASMO_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.FLASHLIGHT))
            .displayName(Text.translatable("itemgroup.arthursphasmo"))
            .build();


    public static void Initialise() {
        PopulateItemGroup();
    }

    private static void PopulateItemGroup() {
        Registry.register(Registries.ITEM_GROUP, ARTHURSPHASMO_ITEM_GROUP_KEY, ARTHURSPHASMO_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(ARTHURSPHASMO_ITEM_GROUP_KEY).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModItems.FLASHLIGHT);

            // triggers
            fabricItemGroupEntries.add(ModItems.GHOST_IDLE_TRIGGER);
            fabricItemGroupEntries.add(ModItems.GHOST_WANDERING_TRIGGER);
            fabricItemGroupEntries.add(ModItems.GHOST_INTERACTING_TRIGGER);
            fabricItemGroupEntries.add(ModItems.GHOST_MANIFESTING_TRIGGER);
            fabricItemGroupEntries.add(ModItems.GHOST_HUNTING_TRIGGER);
        });
    }
}
