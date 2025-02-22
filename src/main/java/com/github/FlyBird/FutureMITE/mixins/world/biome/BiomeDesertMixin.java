package com.github.FlyBird.FutureMITE.mixins.world.biome;


import com.github.FlyBird.FutureMITE.entities.EntityRabbit;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenDesert;
import net.minecraft.SpawnListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin({BiomeGenDesert.class})
public class BiomeDesertMixin extends BiomeGenBase {
    protected BiomeDesertMixin(int par1) {
        super(par1);
    }

    @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {   //i 为加权数量   j 最小数量   k最大数量
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 10, 1, 2));

    }

}
