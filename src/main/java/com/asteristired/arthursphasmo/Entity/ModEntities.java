package com.asteristired.arthursphasmo.Entity;

import com.asteristired.arthursphasmo.Enums.GhostBehaviorState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;

import static com.asteristired.arthursphasmo.ArthursPhasmo.MOD_ID;

public class ModEntities {
    public static final EntityType<GhostEntity> GHOST_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "ghost"),
            EntityType.Builder.<GhostEntity>create(GhostEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 1.8f)
                    .build()
    );

    public static Optional<GhostEntity> getGhost(Entity entity, World world) {
        return world.getEntitiesByClass(GhostEntity.class, entity.getBoundingBox().expand(20), g -> g.isAlive() && !g.isRemoved())
                .stream().findFirst();
    }

    public static boolean isTangible(GhostEntity ghostEntity) {
        GhostBehaviorState state = ghostEntity.getGhostState();
        return state == GhostBehaviorState.HUNTING || state == GhostBehaviorState.MANIFESTING;
    }

    public static void Initalise() {}
}
