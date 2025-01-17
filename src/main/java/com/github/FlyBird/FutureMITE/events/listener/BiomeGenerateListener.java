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
            return ModBiomes.deepOcean.biomeID;
        }
        return original;
    }
    // here the minecraft transform some plain into ice plain
    public int onLayerAddSnow(GenLayer genLayer, int original) {
        if (original == BiomeGenBase.ocean.biomeID) {
            return ModBiomes.deepOcean.biomeID;
        }
        return original;
    }

    // here the minecraft transform some ice plain into frozen ocean
    public int onLayerAddIsland(GenLayer genLayer, int original) {
        if (original == BiomeGenBase.ocean.biomeID) {
            return ModBiomes.deepOcean.biomeID;
        }
        return original;
    }

}
