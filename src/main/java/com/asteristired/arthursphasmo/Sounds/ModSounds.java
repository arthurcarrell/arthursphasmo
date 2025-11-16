package com.asteristired.arthursphasmo.Sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.asteristired.arthursphasmo.ArthursPhasmo.MOD_ID;

public class ModSounds {
    public static final Identifier FLASHLIGHT_CLICK = Identifier.of(MOD_ID, "flashlight_click");
    public static SoundEvent FLASHLIGHT_CLICK_EVENT = SoundEvent.of(FLASHLIGHT_CLICK);
}
