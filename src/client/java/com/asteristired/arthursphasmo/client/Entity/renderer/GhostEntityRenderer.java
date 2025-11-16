package com.asteristired.arthursphasmo.client.Entity.renderer;

import com.asteristired.arthursphasmo.Entity.GhostEntity;
import com.asteristired.arthursphasmo.Enums.GhostBehaviorState;
import com.asteristired.arthursphasmo.client.Entity.GhostHelper;
import com.asteristired.arthursphasmo.client.Entity.model.GhostEntityModel;
import com.asteristired.arthursphasmo.client.Entity.model.GhostModelLayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

import static com.asteristired.arthursphasmo.ArthursPhasmo.MOD_ID;

public class GhostEntityRenderer extends MobEntityRenderer<GhostEntity, GhostEntityModel<GhostEntity>> {
    private float flickerTime;
    private boolean flickeringShow;

    public GhostEntityRenderer(EntityRendererFactory.Context context, GhostEntityModel<GhostEntity> entityModel, float f) {
        super(context, entityModel, f);
    }
    public GhostEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostEntityModel<>(context.getPart(GhostModelLayers.GHOST)), 0.4f);
    }

    @Override
    public boolean shouldRender(GhostEntity entity, Frustum frustum, double x, double y, double z) {
        Optional<GhostEntity> ghost = GhostHelper.getGhost(MinecraftClient.getInstance().player, entity.getWorld());

        if (ghost.isPresent()) {
            GhostBehaviorState state = ghost.get().getGhostState();
            flickerTime -= MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false);
            if (flickerTime < 0) {
                flickeringShow = !flickeringShow;
                flickerTime = Random.create().nextBetween(2,20);
            }
            return state == GhostBehaviorState.MANIFESTING || (state == GhostBehaviorState.HUNTING && flickeringShow);
        }
        return false;
    }

    @Override
    public Identifier getTexture(GhostEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/ghost.png");
    }
}
