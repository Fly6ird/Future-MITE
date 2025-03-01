package com.github.FlyBird.FutureMITE.world.gen;

import com.github.FlyBird.FutureMITE.blocks.Blocks;
import net.minecraft.Block;
import net.minecraft.Direction;
import net.minecraft.World;
import net.minecraft.WorldGenerator;

import java.util.Random;

public class WorldGenCherryTrees extends WorldGenerator {
    private final int minTreeHeight;
    private final boolean vinesGrow;
    private final int metaWood;
    private final int metaLeaves;

    public WorldGenCherryTrees(boolean par1) {
        this(par1, 4, 0, 0, false);
    }

    public WorldGenCherryTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
        this.vinesGrow = par5;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int var6 = random.nextInt(3) + this.minTreeHeight;
        boolean var7 = true;
        if (y >= 1 && y + var6 + 1 <= 256) {
            int var12;
            int var11;
            int var9;
            int var8;
            for (var8 = y; var8 <= y + 1 + var6; ++var8) {
                var9 = 1;
                if (var8 == y) {
                    var9 = 0;
                }
                if (var8 >= y + 1 + var6 - 2) {
                    var9 = 2;
                }
                for (int var10 = x - var9; var10 <= x + var9 && var7; ++var10) {
                    for (var11 = z - var9; var11 <= z + var9 && var7; ++var11) {
                        if (var8 >= 0 && var8 < 256) {
                            var12 = world.getBlockId(var10, var8, var11);
                            if (var12 == 0 || var12 == Blocks.cherry.Leaves.blockID || var12 == Block.grass.blockID || var12 == Block.dirt.blockID || var12 == Block.wood.blockID)
                                continue;
                            var7 = false;
                            continue;
                        }
                        var7 = false;
                    }
                }
            }
            if (!var7) {
                return false;
            }
            int radius = 0;
            var8 = world.getBlockId(x, y - 1, z);
            if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && y < 256 - var6 - 1) {
                int var15;
                int var14;
                int var13;
                this.setBlock(world, x, y - 1, z, Block.dirt.blockID);
                var9 = 3;
                int var19 = 0;
                for (var11 = y - var9 + var6; var11 <= y + var6; ++var11) {
                    var12 = var11 - (y + var6);
                    var13 = var19 + 1 - var12 / 2;
                    if (var13 > radius) {
                        radius = var13;
                    }
                    for (var14 = x - var13; var14 <= x + var13; ++var14) {
                        var15 = var14 - x;
                        for (int var16 = z - var13; var16 <= z + var13; ++var16) {
                            int var18;
                            int var17 = var16 - z;
                            if (var15 == var13 && -var15 == var13 && var17 == var13 && -var17 == var13 && (random.nextInt(2) == 0 || var12 == 0) || (var18 = world.getBlockId(var14, var11, var16)) != 0 && var18 != Blocks.cherry.Leaves.blockID)
                                continue;
                            this.setBlockAndMetadata(world, var14, var11, var16, Blocks.cherry.Leaves.blockID, this.metaLeaves);
                        }
                    }
                }
                for (var11 = 0; var11 < var6; ++var11) {
                    var12 = world.getBlockId(x, y + var11, z);
                    if (var12 != 0 && var12 != Blocks.cherry.Leaves.blockID) continue;
                    this.setBlockAndMetadata(world, x, y + var11, z, Blocks.cherry.Log.blockID, this.metaWood);
                    if (!this.vinesGrow || var11 <= 0) continue;
                    if (random.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var11, z)) {
                        this.setBlockAndMetadata(world, x - 1, y + var11, z, Block.vine.blockID, 8);
                    }
                    if (random.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var11, z)) {
                        this.setBlockAndMetadata(world, x + 1, y + var11, z, Block.vine.blockID, 2);
                    }
                    if (random.nextInt(3) > 0 && world.isAirBlock(x, y + var11, z - 1)) {
                        this.setBlockAndMetadata(world, x, y + var11, z - 1, Block.vine.blockID, 1);
                    }
                    if (random.nextInt(3) <= 0 || !world.isAirBlock(x, y + var11, z + 1)) continue;
                    this.setBlockAndMetadata(world, x, y + var11, z + 1, Block.vine.blockID, 4);
                }
                if (this.vinesGrow) {
                    for (var11 = y - 3 + var6; var11 <= y + var6; ++var11) {
                        var12 = var11 - (y + var6);
                        var13 = 2 - var12 / 2;
                        for (var14 = x - var13; var14 <= x + var13; ++var14) {
                            for (var15 = z - var13; var15 <= z + var13; ++var15) {
                                if (world.getBlockId(var14, var11, var15) != Blocks.cherry.Leaves.blockID) continue;
                                if (random.nextInt(4) == 0 && world.getBlockId(var14 - 1, var11, var15) == 0) {
                                    this.growVines(world, var14 - 1, var11, var15, 8);
                                }
                                if (random.nextInt(4) == 0 && world.getBlockId(var14 + 1, var11, var15) == 0) {
                                    this.growVines(world, var14 + 1, var11, var15, 2);
                                }
                                if (random.nextInt(4) == 0 && world.getBlockId(var14, var11, var15 - 1) == 0) {
                                    this.growVines(world, var14, var11, var15 - 1, 1);
                                }
                                if (random.nextInt(4) != 0 || world.getBlockId(var14, var11, var15 + 1) != 0) continue;
                                this.growVines(world, var14, var11, var15 + 1, 4);
                            }
                        }
                    }
                    if (random.nextInt(5) == 0 && var6 > 5) {
                        for (var11 = 0; var11 < 2; ++var11) {
                            for (var12 = 0; var12 < 4; ++var12) {
                                if (random.nextInt(4 - var11) != 0) continue;
                                var13 = random.nextInt(3);
                                this.setBlockAndMetadata(world, x + Direction.offsetX[Direction.rotateOpposite[var12]], y + var6 - 5 + var11, z + Direction.offsetZ[Direction.rotateOpposite[var12]], Block.cocoaPlant.blockID, var13 << 2 | var12);
                            }
                        }
                    }
                }
                if (world.decorating) {
                    world.placeNaturallyOccurringSnow(x - radius, z - radius, x + radius, z + radius);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void growVines(World par1World, int par2, int par3, int par4, int par5) {
        this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
        int var6 = 4;
        while (par1World.getBlockId(par2, --par3, par4) == 0 && var6 > 0) {
            this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
            --var6;
        }
    }
}
