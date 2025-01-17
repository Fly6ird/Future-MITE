/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     cpw - implementation
 */

package com.github.FlyBird.FutureMITE.common;

import com.github.FlyBird.FutureMITE.api.IWorldGenerator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import net.minecraft.IChunkProvider;
import net.minecraft.World;

import java.util.*;

public class GameRegistry
{
    private static Set<IWorldGenerator> worldGenerators = Sets.newHashSet();
    private static Map<IWorldGenerator, Integer> worldGeneratorIndex = Maps.newHashMap();
    private static List<IWorldGenerator> sortedGeneratorList;

    /**
     * Register a world generator - something that inserts new block types into the world
     *
     * @param generator the generator
     * @param modGenerationWeight a weight to assign to this generator. Heavy weights tend to sink to the bottom of
     * list of world generators (i.e. they run later)
     */
    public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight)
    {
        worldGenerators.add(generator);
        worldGeneratorIndex.put(generator, modGenerationWeight);
        if (sortedGeneratorList != null)
        {
            sortedGeneratorList = null;
        }
    }

    /**
     * Callback hook for world gen - if your mod wishes to add extra mod related generation to the world
     * call this
     *
     * @param chunkX
     * @param chunkZ
     * @param world
     * @param chunkGenerator
     * @param chunkProvider
     */
    public static void generateWorld(int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (sortedGeneratorList == null)
        {
            computeSortedGeneratorList();
        }
        long worldSeed = world.getSeed();
        Random fmlRandom = new Random(worldSeed);
        long xSeed = fmlRandom.nextLong() >> 2 + 1L;
        long zSeed = fmlRandom.nextLong() >> 2 + 1L;
        long chunkSeed = (xSeed * chunkX + zSeed * chunkZ) ^ worldSeed;

        for (IWorldGenerator generator : sortedGeneratorList)
        {
            fmlRandom.setSeed(chunkSeed);
            generator.generate(fmlRandom, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    private static void computeSortedGeneratorList()
    {
        ArrayList<IWorldGenerator> list = Lists.newArrayList(worldGenerators);
        Collections.sort(list, new Comparator<IWorldGenerator>() {
            @Override
            public int compare(IWorldGenerator o1, IWorldGenerator o2)
            {
                return Ints.compare(worldGeneratorIndex.get(o1), worldGeneratorIndex.get(o2));
            }
        });
        sortedGeneratorList = ImmutableList.copyOf(list);
    }
}