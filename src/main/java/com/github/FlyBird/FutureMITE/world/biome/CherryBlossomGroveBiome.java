package com.github.FlyBird.FutureMITE.world.biome;


import com.github.FlyBird.FutureMITE.world.gen.WorldGenBigCherryTree;
import com.github.FlyBird.FutureMITE.world.gen.WorldGenCherryForest;
import com.github.FlyBird.FutureMITE.world.gen.WorldGenCherryTrees;
import net.minecraft.*;

import java.util.Random;

public class CherryBlossomGroveBiome extends BiomeGenBase {

    protected WorldGenCherryForest worldGenCherryForest;
    protected WorldGenBigCherryTree worldGenBigCherryTree;
    protected WorldGenCherryTrees worldGenCherryTrees;

    protected CherryBlossomGroveBiome(int par1) {
        super(par1);

        this.theBiomeDecorator.treesPerChunk=8;

        this.worldGenCherryForest = new WorldGenCherryForest(false);
        this.worldGenBigCherryTree = new WorldGenBigCherryTree(false);
        this.worldGenCherryTrees=new WorldGenCherryTrees(false);
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return par1Random.nextInt(5) == 0 ? this.worldGenCherryForest : (par1Random.nextInt(10) == 0 ? this.worldGenBigCherryTree : this.worldGenCherryTrees);
    }

    public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}
