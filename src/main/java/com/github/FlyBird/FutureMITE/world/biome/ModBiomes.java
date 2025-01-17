package com.github.FlyBird.FutureMITE.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenOcean;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class ModBiomes extends BiomeGenBase {
    protected ModBiomes(int par1) {
        super(par1);
    }

    public static final BiomeGenBase deepOcean = (new BiomeGenOcean(getNextBiomeID())).setColor(48).setBiomeName("Deep Ocean").setMinMaxHeight(-1.8F,0.1F);
    private static int getNextBiomeID() {
        return IdUtil.getNextBiomeId();
    }
}
