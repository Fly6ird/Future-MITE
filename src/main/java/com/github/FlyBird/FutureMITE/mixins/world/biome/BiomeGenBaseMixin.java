package com.github.FlyBird.FutureMITE.mixins.world.biome;

import com.github.FlyBird.FutureMITE.world.structure.OceanMonument;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenOcean;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BiomeGenBase.class)
public class BiomeGenBaseMixin {
    @Unique
    private final BiomeGenBase instance = (BiomeGenBase) (Object) this;

    @Inject(method = "decorate", at = @At("TAIL"))
    public void FutureMITEDecorate(World par1World, Random par2Random, int chunk_x, int chunk_z, CallbackInfo ci) {
        if (par1World.isOverworld() && OceanMonument.canSpawnAt(par1World, chunk_x, chunk_z)) {

            int x = chunk_x * 16 + par2Random.nextInt(16);
            int y = 256;
            int z = chunk_z * 16 + par2Random.nextInt(16);
            for (; y > 0; y--)
                if (!par1World.isAirBlock(x, y, z))
                    break;
            int monumentCeiling = y - (1 + par2Random.nextInt(3));
            System.out.print("OceanMonument:X" + x + "Z:" + z);
            OceanMonument.buildTemple(par1World, x, monumentCeiling - 22, z);
        }
    }

    @Inject(method = "setMinMaxHeight", at = @At("RETURN"))
    public final void setMinMaxHeight(float par1, float par2, CallbackInfoReturnable<BiomeGenBase> cir) {
        if (instance instanceof BiomeGenOcean) {
            instance.minHeight = -1.8f;
            instance.maxHeight = 0.1f;
        }
    }
}
