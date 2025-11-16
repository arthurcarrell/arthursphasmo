package com.asteristired.arthursphasmo.Entity.attributes;

import com.asteristired.arthursphasmo.Entity.ModEntities;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;

public class ModEntityAttributes {
    public static void Register() {
        FabricDefaultAttributeRegistry.register(
                ModEntities.GHOST_ENTITY,
                PathAwareEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f)
        );
    }
}
