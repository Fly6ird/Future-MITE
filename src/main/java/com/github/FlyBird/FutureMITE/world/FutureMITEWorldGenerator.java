package com.github.FlyBird.FutureMITE.world;

import com.github.FlyBird.FutureMITE.api.IWorldGenerator;
import com.github.FlyBird.FutureMITE.world.structure.OceanMonument;
import net.minecraft.IChunkProvider;
import net.minecraft.World;

import java.util.Random;


public class FutureMITEWorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId != -1 && world.provider.dimensionId != 1)
            if (OceanMonument.canSpawnAt(world, chunkX, chunkZ)) {
                int x = chunkX * 16 + rand.nextInt(16);
                int y = 256;
                int z = chunkZ * 16 + rand.nextInt(16);
                for (; y > 0; y--)
                    if (!world.isAirBlock(x, y, z))
                        break;
                int monumentCeiling = y - (1 + rand.nextInt(3));
                System.out.print("OceanMonument:X" + x + "Z:" + z);
                OceanMonument.buildTemple(world, x, monumentCeiling - 22, z);
            }
    }

}