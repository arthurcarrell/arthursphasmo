package com.asteristired.arthursphasmo;

import com.asteristired.arthursphasmo.Entity.ModEntities;
import com.asteristired.arthursphasmo.Entity.attributes.ModEntityAttributes;
import com.asteristired.arthursphasmo.Item.components.ModComponents;
import com.asteristired.arthursphasmo.Item.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArthursPhasmo implements ModInitializer {

    public static final String MOD_ID = "arthursphasmo";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        ModItems.Initialise();
        ModComponents.Initalise();
        ModComponents.attachComponents();

        ModEntities.Initalise();
        ModEntityAttributes.Register();
    }
}
