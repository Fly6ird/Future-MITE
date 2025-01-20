package com.github.FlyBird.FutureMITE.sound;

import net.minecraft.StepSound;


public class StepSoundWood extends StepSound {
    public StepSoundWood(String string, float volume, float pitch) {
        super(string, volume, pitch);
    }

    private final float volume = 1.0f;

    @Override
    public String getBreakSound() {

        return "futuremite:dig.newwood";
    }

    @Override
    public String getPlaceSound() {
        return "futuremite:dig.newwood";
    }

    @Override
    public String getStepSound() {
        return "futuremite:step.newwood";
    }

    @Override
    public float getVolume() {
        return this.volume;
    }
}