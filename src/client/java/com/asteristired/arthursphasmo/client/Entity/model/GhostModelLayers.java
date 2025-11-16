package com.asteristired.arthursphasmo.client.Entity.model;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.asteristired.arthursphasmo.ArthursPhasmo.MOD_ID;

public class GhostModelLayers {
    public static final EntityModelLayer GHOST = new EntityModelLayer(Identifier.of(MOD_ID, "ghost"), "main");

    public static void Initalise() {};
}
