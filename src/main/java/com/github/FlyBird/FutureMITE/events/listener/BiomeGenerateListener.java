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
        if (original == BiomeGenBase.forestHills.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 3L)) {
                case 0 -> ModBiomes.cherryBlossomGrove.biomeID;
                case 1 -> ModBiomes.cherryBlossomGroveHills.biomeID;
                default -> ModBiomes.forestHills.biomeID;
            };
        }
        if (original == BiomeGenBase.forest.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 3L)) {
                case 0 -> ModBiomes.cherryBlossomGrove.biomeID;
                case 1 -> ModBiomes.cherryBlossomGroveHills.biomeID;
                default -> BiomeGenBase.forest.biomeID;
            };
        }
        if (original == BiomeGenBase.iceMountains.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 2L)) {
                case 0 -> ModBiomes.cherryBlossomGrove.biomeID;
                default -> BiomeGenBase.iceMountains.biomeID;
            };
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

    @Override
    public void onStrongholdAllowedRegister(List<BiomeGenBase> original) {
        original.add(ModBiomes.cherryBlossomGroveHills);
        original.add(ModBiomes.cherryBlossomGrove);
    }

}
