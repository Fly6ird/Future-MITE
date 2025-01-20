package com.github.FlyBird.FutureMITE.sound;

import net.minecraft.StepSound;

public class StepSoundLantern extends StepSound {
    public StepSoundLantern(String string, float volume, float pitch) {
        super(string, volume, pitch);
    }

    private float volume = 1.0f;
    private float pitch = 1.0f;

    @Override
    public String getBreakSound() {
        pitch = 1.0f;
        return "futuremite:block.lantern.break";
    }

    @Override
    public String getPlaceSound() {
        pitch = 1.1f;
        return "futuremite:block.lantern.place";
    }

    @Override
    public String getStepSound() {
        pitch = 1.0f;
        return "futuremite:block.lantern.break";
    }

    @Override
    public float getVolume() {
        return this.volume;
    }
}
