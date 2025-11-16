package com.asteristired.arthursphasmo.client.Flashlight;

import com.asteristired.arthursphasmo.Entity.GhostEntity;
import com.asteristired.arthursphasmo.Entity.ModEntities;
import com.asteristired.arthursphasmo.Enums.GhostBehaviorState;
import com.asteristired.arthursphasmo.Item.components.ModComponents;
import com.asteristired.arthursphasmo.Item.items.ModItems;
import com.asteristired.arthursphasmo.client.Entity.GhostHelper;
import com.asteristired.arthursphasmo.client.Entity.model.GhostEntityModel;
import foundry.veil.Veil;
import foundry.veil.VeilClient;
import foundry.veil.api.client.render.MatrixStack;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.event.VeilRenderLevelStageEvent;
import foundry.veil.impl.client.render.light.AreaLightRenderer;
import foundry.veil.platform.VeilEventPlatform;
import net.minecraft.block.enums.Orientation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Optional;

public class FlashlightClient {
    // first time using veil so this shall be interesting
    private static final AreaLight LIGHT = new AreaLight();
    private static final int LIGHT_COLOR = 0xfffbd3;
    private static boolean should_render = false;
    private static boolean is_rendering = true;
    private static final float base_brightness = 1.5f;
    private static float flickerTime;
    private static boolean flickeringShow;

    // set colors and stuff
    public static void Initalise() {
        LIGHT.setDistance(25f);
        LIGHT.setBrightness(base_brightness);
        LIGHT.setAngle(0.5061455f);
        LIGHT.setColor(LIGHT_COLOR);

        // register

        // this needs to be done whenever the light is enabled or not, but for now, just create it
        VeilEventPlatform.INSTANCE.onVeilRendererAvailable((veilRenderer) -> {
            createLight();
        });

        VeilEventPlatform.INSTANCE.onVeilRenderLevelStage(((stage, worldRenderer, immediate, matrixStack, matrix4fc, matrix4fc1, i, renderTickCounter, camera, frustum) -> {
            shouldRenderFlashlight();
            updateLight(camera);
            if (should_render != is_rendering) {
                toggleLight();
            }
        }));
    }

    private static void removeLight() {
        // remove the light from the renderer
        VeilRenderSystem.renderer().getLightRenderer().removeLight(LIGHT);
        is_rendering = false;
    }

    private static void shouldRenderFlashlight() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            if (client.player.getMainHandStack().getItem().equals(ModItems.FLASHLIGHT) || client.player.getOffHandStack().getItem().equals(ModItems.FLASHLIGHT)) {
                if (client.player.getMainHandStack().getItem().equals(ModItems.FLASHLIGHT)) {
                    should_render = client.player.getMainHandStack().get(ModComponents.SWITCHED_ON);
                } else {
                    should_render = client.player.getOffHandStack().get(ModComponents.SWITCHED_ON);
                }
            } else {
                should_render = false;
            }
        } else {
            should_render = false;
        }
    }

    private static void createLight() {
        VeilRenderSystem.renderer().getLightRenderer().addLight(LIGHT);
        is_rendering = true;
    }

    private static void toggleLight() {
        if (is_rendering) {
            removeLight();
        } else {
            createLight();
        }
    }

    private static void updateLight(Camera camera) {
        // get the player
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            LIGHT.setTo(camera);
            Vec3d lookVector = client.player.getRotationVector().rotateZ(3.141593f).add(new Vec3d(0,-0.1,0));

            // get the ghost
            assert client.world != null;
            Optional<GhostEntity> ghost = GhostHelper.getGhost(client.player, client.world, 7);
            LIGHT.setBrightness(base_brightness);
            if (ghost.isPresent()) {
                if (ModEntities.isTangible(ghost.get())) {
                    flickerTime -= camera.getLastTickDelta();
                    if (flickerTime < 0) {
                        flickeringShow = !flickeringShow;
                        flickerTime = Random.createLocal().nextBetween(10,50);
                    }
                    if (!flickeringShow) {
                        LIGHT.setBrightness(base_brightness/3);
                    }
                }
            }

            LIGHT.setOrientation(new Quaternionf().rotationTo(new Vector3f(0,0,1), lookVector.toVector3f()));
        }
    }
}
