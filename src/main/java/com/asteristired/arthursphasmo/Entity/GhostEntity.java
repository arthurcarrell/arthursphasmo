package com.asteristired.arthursphasmo.Entity;

import com.asteristired.arthursphasmo.Enums.GhostBehaviorState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import static com.asteristired.arthursphasmo.ArthursPhasmo.LOGGER;

public class GhostEntity extends PathAwareEntity {
    private static final TrackedData<Integer> STATE = DataTracker.registerData(GhostEntity.class, TrackedDataHandlerRegistry.INTEGER);

    protected GhostEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(STATE, GhostBehaviorState.IDLE.ordinal());
        super.initDataTracker(builder);
    }

    public void setGhostState(GhostBehaviorState ghostState) {
        this.dataTracker.set(STATE, ghostState.ordinal());
    }

    public GhostBehaviorState getGhostState() {
        return GhostBehaviorState.values()[this.dataTracker.get(STATE)];
    }

    @Override
    public boolean isCollidable() {
        return ModEntities.isTangible(this);
    }

    @Override
    public boolean collidesWith(Entity other) {
        if (!ModEntities.isTangible(this)) return false;
        return super.collidesWith(other);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        if (!ModEntities.isTangible(this)) return false;
        return super.doesNotCollide(offsetX, offsetY, offsetZ);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void tick() {
        // need noclip so cannot be collided with - make sure no gravity is on to prevent falling through the floor.
        this.noClip = ModEntities.isTangible(this);
        this.setNoGravity(true);
        super.tick();
    }
}
