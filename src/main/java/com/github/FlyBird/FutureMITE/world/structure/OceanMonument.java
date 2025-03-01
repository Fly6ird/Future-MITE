package com.github.FlyBird.FutureMITE.world.structure;


import com.github.FlyBird.FutureMITE.FutureMITEStart;
import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.world.WorldCoord;
import com.github.FlyBird.FutureMITE.world.biome.ModBiomes;
import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.Material;
import net.minecraft.World;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class OceanMonument {

    private static final List<BiomeGenBase> validBiomes = Arrays.asList(BiomeGenBase.ocean, ModBiomes.deepOcean, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver);
    private static final Map<WorldCoord, Integer> map = new HashMap<>();

    public static void makeMap() {
        try {
            InputStream is = FutureMITEStart.class.getResourceAsStream("/assets/ocean_monument.txt");
            BufferedReader br = null;
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is));
            }

            String s;
            if (br != null) {
                while ((s = br.readLine()) != null) {
                    String[] data = s.split("-");
                    data[0] = data[0].trim();
                    data[0] = data[0].substring(1, data[0].length() - 1);

                    data[1] = data[1].trim();

                    String[] coords = data[0].split(",");

                    WorldCoord key = new WorldCoord(Integer.parseInt(coords[0].trim()), Integer.parseInt(coords[1].trim()), Integer.parseInt(coords[2].trim()));
                    int value = Integer.parseInt(data[1]);

                    map.put(key, value);
                }
                is.close();
            }

            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<WorldCoord, Integer> getMap() {
        return map;
    }

    public static void buildTemple(World world, int x, int y, int z) {
        if (world.isRemote)
            return;

        for (Entry<WorldCoord, Integer> entry : OceanMonument.getMap().entrySet()) {
            WorldCoord pos = entry.getKey();
            int value = entry.getValue();

            Block block = null;
            int meta = 0;
            switch (value) {
                case 0:
                case 1:
                case 2:
                    block = Blocks.prismarineBlock;
                    meta = value;
                    break;
                case 3:
                    block = Blocks.seaLantern;
                    break;
                case 4:
                    block = Block.blockGold;
                    break;
                case 5:
                    block = Blocks.sponge;
                    meta = 1;
                    break;
                case 6:
                    block = Block.waterStill;
                    break;
            }

            if (block != null)
                world.setBlock(pos.x + x, pos.y + y, pos.z + z, block.blockID, meta, 2);
        }

        for (int i = 0; i < 7; i++) {
            generatePillar(world, x + 5 * i + 4 * i, y, z, Blocks.prismarineBlock, 1);
            generatePillar(world, x, y, z + 5 * i + 4 * i, Blocks.prismarineBlock, 1);
            generatePillar(world, x + 54, y, z + 5 * i + 4 * i, Blocks.prismarineBlock, 1);
            if (i != 3)
                generatePillar(world, x + 5 * i + 4 * i, y, z + 54, Blocks.prismarineBlock, 1);
        }
    }

    private static void generatePillar(World world, int x, int y, int z, Block block, int meta) {
        for (int i = 1; i <= 5; i++)
            generatePillarSection(world, x, y - i, z, block, meta);
        y -= 5;

        for (; y >= 0; y--) {
            generatePillarSection(world, x, y, z, block, meta);
            for (int i = 0; i < 4; i++)
                for (int k = 0; k < 4; k++) {
                    Block block1 = world.getBlock(x + i, y, z);
                    if (block1 != null && block1.blockMaterial != Material.water && y > 3) {
                        generatePillarSection(world, x, y - 1, z, block, meta);
                        generatePillarSection(world, x, y - 2, z, block, meta);
                        return;
                    }
                }
        }
    }

    private static void generatePillarSection(World world, int x, int y, int z, Block block, int meta) {
        for (int i = 0; i < 4; i++)
            for (int k = 0; k < 4; k++) {
                Block block1 = world.getBlock(x + i, y, z);
                if (block1 != null && block1.getBlockHardness(meta) > 0)
                    world.setBlock(x + i, y, z + k, block.blockID, meta, 2);
            }
    }

    public static void generateFile(World world, int x, int y, int z, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));

            for (int i = 0; i < 58; i++)
                for (int j = 0; j < 31 - 9; j++)
                    for (int k = 0; k < 58; k++) {
                        Block b = world.getBlock(x + i, y + j, z + k);
                        int meta = world.getBlockMetadata(x + i, y + j, z + k);

                        String s = "(" + i + ", " + j + ", " + k + ") - ";
                        if (b == Blocks.prismarineBlock)
                            s += meta;
                        else if (b == Blocks.seaLantern)
                            s += 3;
                        else if (b == Block.blockGold)
                            s += 4;
                        else if (b == Blocks.sponge)
                            s += 5;
/*						else if (b == Blocks.stained_glass)
							s += 6;*/
                        else
                            s = null;

                        if (s != null) {
                            bw.write(s);
                            bw.newLine();
                        }
                    }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean canSpawnAt(World worldObj, int chunkX, int chunkZ) {
        int spacing = 7;
        int separation = 5;
        int xx = chunkX;
        int zz = chunkZ;

        if (chunkX < 0)
            chunkX -= spacing - 1;

        if (chunkZ < 0)
            chunkZ -= spacing - 1;

        int i1 = chunkX / spacing;
        int j1 = chunkZ / spacing;
        Random random = worldObj.setRandomSeed(i1, j1, 10387313);
        i1 *= spacing;
        j1 *= spacing;
        int range = spacing - separation;
        if (range <= 0) {
            range = 1;  // 设置一个最小的合法值，避免 nextInt(0) 或 nextInt(负数)
        }
        i1 += (random.nextInt(range) + random.nextInt(range)) / 2;
        j1 += (random.nextInt(range) + random.nextInt(range)) / 2;

        if (xx == i1 && zz == j1) {
            if (worldObj.getWorldChunkManager().getBiomeGenAt(xx * 16 + 8, zz * 16 + 8) != BiomeGenBase.ocean)
                return false;
            return worldObj.getWorldChunkManager().areBiomesViable(xx * 16 + 8, zz * 16 + 8, 29, validBiomes);
        }

        return false;
    }
}
