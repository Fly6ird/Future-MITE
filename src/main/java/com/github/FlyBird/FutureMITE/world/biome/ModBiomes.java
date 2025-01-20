package com.github.FlyBird.FutureMITE.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenOcean;
import net.minecraft.Block;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class ModBiomes extends BiomeGenBase {
    protected ModBiomes(int par1) {
        super(par1);
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
    }

    public static final BiomeGenBase deepOcean = (new BiomeGenOcean(getNextBiomeID())).setColor(48).setBiomeName("Deep Ocean").setMinMaxHeight(-1.8F, 0.1F);
    public static final BiomeGenBase cherryBlossomGrove = (new CherryBlossomGroveBiome(getNextBiomeID())).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.1f, 0.5f);
    public static final BiomeGenBase cherryBlossomGroveHills = (new CherryBlossomGroveBiome(getNextBiomeID())).setBiomeName("Cherry Blossom Grove Hills").setMinMaxHeight(0.3F, 1.0F);

    private static int getNextBiomeID() {
        return IdUtil.getNextBiomeId();
    }
}
