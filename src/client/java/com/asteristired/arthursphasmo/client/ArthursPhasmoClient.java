package com.asteristired.arthursphasmo.client;

import com.asteristired.arthursphasmo.Entity.ModEntities;
import com.asteristired.arthursphasmo.client.Entity.model.GhostEntityModel;
import com.asteristired.arthursphasmo.client.Entity.model.GhostModelLayers;
import com.asteristired.arthursphasmo.client.Entity.renderer.GhostEntityRenderer;
import com.asteristired.arthursphasmo.client.Flashlight.FlashlightClient;
import foundry.veil.Veil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ArthursPhasmoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Veil.init();
        FlashlightClient.Initalise();

        EntityModelLayerRegistry.registerModelLayer(
                GhostModelLayers.GHOST,
                GhostEntityModel::getTexturedModelData
        );
        EntityRendererRegistry.register(ModEntities.GHOST_ENTITY, GhostEntityRenderer::new);
    }
}
