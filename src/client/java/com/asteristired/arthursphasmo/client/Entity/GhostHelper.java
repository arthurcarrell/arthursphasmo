package com.asteristired.arthursphasmo.client.Entity;

import com.asteristired.arthursphasmo.Entity.GhostEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.Optional;

public class GhostHelper {
    public static Optional<GhostEntity> getGhost(ClientPlayerEntity entity, World world) {
        return getGhost(entity, world, 20);
    }

    public static Optional<GhostEntity> getGhost(ClientPlayerEntity entity, World world, int radius) {
        return world.getEntitiesByClass(GhostEntity.class, entity.getBoundingBox().expand(radius), ghost -> ghost.isAlive() && !ghost.isRemoved()).stream().findFirst();
    }
}
