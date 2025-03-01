//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.FlyBird.FutureMITE.blocks;

import net.minecraft.*;

import java.util.Random;

public class BlockModLeaves extends BlockLeavesBase {
    private int iconType;
    private Icon iconLeaves;
    int[] adjacentTreeBlocks;
    private TreeHelper treeHelper;
    public final String id;

    public BlockModLeaves(int par1, String id, TreeHelper treeHelper) {
        super(par1, Material.tree_leaves, false);
        this.id = id;
        this.setTickRandomly(true);
        this.setStepSound(soundGrassFootstep);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName(id);
        this.setCushioning(0.8F);
        this.treeHelper = treeHelper;
    }


    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return 0xffffff;
    }

    @Override
    public int getRenderColor(int metadata) {
        return 0xffffff;
    }


    public int getBlockColor() {
        double var1 = 0.5;
        double var3 = 1.0;
        return ColorizerFoliage.getFoliageColor(var1, var3);
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        if (!par1World.decorating) {
            byte var7 = 1;
            int var8 = var7 + 1;
            if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
                for (int var9 = -var7; var9 <= var7; ++var9) {
                    for (int var10 = -var7; var10 <= var7; ++var10) {
                        for (int var11 = -var7; var11 <= var7; ++var11) {
                            int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
                            if (var12 == Block.leaves.blockID) {
                                int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);
                                par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 8, 4);
                            }
                        }
                    }
                }
            }

        }
    }

    public boolean updateTick(World par1World, int x, int y, int z, Random par5Random) {
        int meta = par1World.getBlockMetadata(x, y, z);
        int subtype;
        if ((meta & 8) != 0 && (meta & 4) == 0) {
            byte var7 = 4;
            subtype = var7 + 1;
            byte var9 = 32;
            int var10 = var9 * var9;
            int var11 = var9 / 2;
            if (this.adjacentTreeBlocks == null) {
                this.adjacentTreeBlocks = new int[var9 * var9 * var9];
            }

            int var12;
            if (par1World.checkChunksExist(x - subtype, y - subtype, z - subtype, x + subtype, y + subtype, z + subtype)) {
                int var13;
                int var14;
                int var15;
                int var12_plus_var11_times_var10_plus_var11;
                int var13_plus_var11_minus_1_times_var10;
                for (var12 = -var7; var12 <= var7; ++var12) {
                    var12_plus_var11_times_var10_plus_var11 = (var12 + var11) * var10 + var11;

                    for (var13 = -var7; var13 <= var7; ++var13) {
                        var13_plus_var11_minus_1_times_var10 = (var13 + var11) * var9;

                        for (var14 = -var7; var14 <= var7; ++var14) {
                            var15 = par1World.getBlockId(x + var12, y + var13, z + var14);
                            if (var15 == Block.wood.blockID) {
                                this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_minus_1_times_var10 + var14] = 0;
                            } else if (var15 == Block.leaves.blockID) {
                                this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_minus_1_times_var10 + var14] = -2;
                            } else {
                                this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_minus_1_times_var10 + var14] = -1;
                            }
                        }
                    }
                }

                for (var12 = 1; var12 <= 4; ++var12) {
                    for (var13 = -var7; var13 <= var7; ++var13) {
                        var12_plus_var11_times_var10_plus_var11 = var13 + var11;
                        var13_plus_var11_minus_1_times_var10 = (var12_plus_var11_times_var10_plus_var11 - 1) * var10;
                        int var13_plus_var11_plus_1_times_var10 = (var12_plus_var11_times_var10_plus_var11 + 1) * var10;
                        int var13_plus_var11_times_var10 = var12_plus_var11_times_var10_plus_var11 * var10;

                        for (var14 = -var7; var14 <= var7; ++var14) {
                            int var14_plus_var11 = var14 + var11;
                            int var14_plus_var11_times_var9 = var14_plus_var11 * var9;

                            for (var15 = -var7; var15 <= var7; ++var15) {
                                int index1 = var13_plus_var11_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
                                if (this.adjacentTreeBlocks[index1] == var12 - 1) {
                                    int index2 = var13_plus_var11_minus_1_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
                                    if (this.adjacentTreeBlocks[index2] == -2) {
                                        this.adjacentTreeBlocks[index2] = var12;
                                    }

                                    index2 = var13_plus_var11_plus_1_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
                                    if (this.adjacentTreeBlocks[index2] == -2) {
                                        this.adjacentTreeBlocks[index2] = var12;
                                    }

                                    index2 = var13_plus_var11_times_var10 + (var14_plus_var11 - 1) * var9 + var15 + var11;
                                    if (this.adjacentTreeBlocks[index2] == -2) {
                                        this.adjacentTreeBlocks[index2] = var12;
                                    }

                                    index2 = var13_plus_var11_times_var10 + (var14_plus_var11 + 1) * var9 + var15 + var11;
                                    if (this.adjacentTreeBlocks[index2] == -2) {
                                        this.adjacentTreeBlocks[index2] = var12;
                                    }

                                    index2 = var13_plus_var11_times_var10 + var14_plus_var11_times_var9 + (var15 + var11 - 1);
                                    if (this.adjacentTreeBlocks[index2] == -2) {
                                        this.adjacentTreeBlocks[index2] = var12;
                                    }

                                    if (this.adjacentTreeBlocks[index1 + 1] == -2) {
                                        this.adjacentTreeBlocks[index1 + 1] = var12;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];
            if (var12 >= 0) {
                par1World.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
                return true;
            } else {
                this.removeLeaves(par1World, x, y, z);
                return true;
            }
        } else {
            if (RNG.chance_in_100[++RNG.random_number_index + (int) par1World.total_time & 32767] && !wasPlaced(meta) && (!par1World.getAsWorldServer().fast_forwarding || RNG.chance_in_32[++RNG.random_number_index & 32767]) && par5Random.nextInt(500) == 0) {
                if (par1World.getBiomeGenForCoords(x, z).isSwampBiome() && par5Random.nextInt(2) == 0) {
                    return false;
                }

                Item item = Item.stick;
                subtype = this.getBlockSubtype(meta);
                if (subtype == 0) {
                    if (par5Random.nextInt(3) > 0) {
                        item = par1World.getBiomeGenForCoords(x, z).isJungleBiome() ? Item.orange : Item.appleRed;
                    }
                } else if (subtype == 3 && par5Random.nextInt(3) > 0) {
                    item = Item.banana;
                }

                this.dropBlockAsEntityItem((new BlockBreakInfo(par1World, x, y, z)).setWindfall(), (Item) item);
            }

            return false;
        }
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
            double var6 = (double) ((float) par2 + par5Random.nextFloat());
            double var8 = (double) par3 - 0.05;
            double var10 = (double) ((float) par4 + par5Random.nextFloat());
            par1World.spawnParticle(EnumParticle.dripWater, var6, var8, var10, 0.0, 0.0, 0.0);
        }

    }

    private void removeLeaves(World par1World, int par2, int par3, int par4) {
        this.dropBlockAsEntityItem((new BlockBreakInfo(par1World, par2, par3, par4)).setDroppedSelf());
        par1World.setBlockToAir(par2, par3, par4);
        Block block_above = par1World.getBlock(par2, par3 + 1, par4);
        if (block_above instanceof BlockUnderminable) {
            ((BlockUnderminable) block_above).tryToFall(par1World, par2, par3 + 1, par4);
        }

    }

    public static boolean wasPlaced(int metadata) {
        return BitHelper.isBitSet(metadata, 4);
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (BitHelper.isBitSet(info.getMetadata(), 4)) {
            return 0;
        } else if (info.getBiome().isSwampBiome() && info.world.rand.nextInt(2) == 0) {
            return 0;
        } else {
            int leaf_kind = this.getBlockSubtype(info.getMetadata());
            int num_drops;
            if ((num_drops = this.dropBlockAsEntityItem(info, this.treeHelper.Sapling.blockID, leaf_kind, 1, 0.01F)) > 0) {
                return num_drops;
            } else if ((num_drops = this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 0.05F)) > 0) {
                return num_drops;
            }/* else if (leaf_kind == 0) {
                return this.dropBlockAsEntityItem(info, info.getBiome().isJungleBiome() ? Item.orange.itemID : Item.appleRed.itemID, 0, 1, 0.005F);
            } else {
                return leaf_kind == 3 ? this.dropBlockAsEntityItem(info, Item.banana.itemID, 0, 1, 0.005F) : 0;
            }*/
        }
        return 0;
    }

    public String getMetadataNotes() {
        return "Bits 1 and 2 used for subtype, bit 4 set if placed by player, and bit 8 set when a neighboring leaf block of any type is destroyed";
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 16;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return 0;
    }

    public Icon getIcon(int side, int meta) {
        return this.iconLeaves;
        //return (meta & 3) == 1 ? this.iconArray[this.iconType][1] : ((meta & 3) == 3 ? this.iconArray[this.iconType][3] : ((meta & 3) == 2 ? this.iconArray[this.iconType][2] : this.iconArray[this.iconType][0]));
    }

    public void setGraphicsLevel(boolean par1) {
        this.graphicsLevel = par1;
        this.iconType = par1 ? 0 : 1;
    }

    public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
        return !this.graphicsLevel;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.iconLeaves = par1IconRegister.registerIcon("futuremite:leaves/" + id);

    }

    public static boolean isBlockUnplacedJungleLeaves(World world, int x, int y, int z) {
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block != Block.leaves) {
            return false;
        } else {
            int metadata = world.getBlockMetadata(x, y, z);
            return metadata == 3 || metadata == 11;
        }
    }
}
