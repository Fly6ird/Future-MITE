package com.github.FlyBird.FutureMITE.events.listener;

import com.github.FlyBird.FutureMITE.world.biome.ModBiomes;
import moddedmite.rustedironcore.api.event.listener.IBiomeGenerateListener;
import net.minecraft.BiomeGenBase;
import net.minecraft.GenLayer;

import java.util.List;

public class BiomeGenerateListener implements IBiomeGenerateListener {
    // areBiomesViable params: blockX, blockZ, radius
    // geInts params: blockX, blockZ, xWidth, zWidth, but with >> 2
    @Override
    public int onLayerHills(GenLayer genLayer, int original) {
        if (original == BiomeGenBase.ocean.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 2L)) {
                case 0 -> ModBiomes.deepOcean.biomeID;
                default -> BiomeGenBase.ocean.biomeID;
            };
        }
        return original;
    }


}
