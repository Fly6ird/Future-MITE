package com.github.FlyBird.FutureMITE.blocks;


import net.minecraft.*;

import java.util.Random;

public class BlockModSapling extends BlockPlant {
    private Icon saplingIcon;
    private WorldGenerator largeTreeGenerator; // 大树生成器
    private WorldGenerator smallTreeGenerator; // 小树生成器
    private TreeHelper treeHelper;

    protected BlockModSapling(int blockID,TreeHelper treeHelper) {
        super(blockID);
        float var2 = 0.4F;
        this.setBlockBoundsForAllThreads(0.5F - var2, 0.0D, 0.5F - var2, 0.5F + var2, var2 * 2.0F, 0.5F + var2);
        this.setMaxStackSize(16);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setCushioning(0.2F);
        this.treeHelper=treeHelper;
        this.setStepSound(soundGrassFootstep);
    }

    public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        return super.updateTick(par1World, par2, par3, par4, par5Random) || (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(28) == 0 && this.markOrGrowMarked(par1World, par2, par3, par4, par5Random));
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2) {
        return this.saplingIcon;
    }

    public boolean markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random) {


        int metadata = par1World.getBlockMetadata(par2, par3, par4);

        if ((metadata & 8) == 0) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, metadata | 8, 4);
            return true;
        } else {
            this.growTree(par1World, par2, par3, par4, par5Random);
            return par1World.getBlock(par2, par3, par4) != this || par1World.getBlockMetadata(par2, par3, par4) != metadata;
        }

    }

    /**
     * Attempts to grow a sapling into a tree
     */
    private void growTree(World par1World, int x, int y, int z, Random par5Random) {
        int meta = par1World.getBlockMetadata(x, y, z) & 3;
        WorldGenerator worldGenerator;
        int x_offset = 0;
        int y_offset = 0;

        worldGenerator = smallTreeGenerator;

        if (par5Random.nextInt(10) == 0) {
            worldGenerator = largeTreeGenerator;
        }


        par1World.setBlock(x, y, z, 0, 0, 4);


        if (!worldGenerator.generate(par1World, par5Random, x + x_offset, y, z + y_offset)) {
            par1World.setBlock(x, y, z, this.blockID, meta, 4);
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    public String getMetadataNotes() {
        return "Bits 1 and 2 used for subtype, bit 8 used for (one) intermediate growth stage";
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 4 || metadata >= 8 && metadata < 12;
    }

  /*  public int getBlockSubtypeUnchecked(int metadata) {
        return metadata & 3;
    }*/

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister) {
        this.saplingIcon = par1IconRegister.registerIcon(this.getTextureName());
    }

    /**
     * 设置大树生成器
     */
    public TreeHelper setLargeTreeGenerator(WorldGenerator largeTreeGenerator) {
        this.largeTreeGenerator = largeTreeGenerator;
        return this.treeHelper;
    }

    /**
     * 设置小树生成器
     */
    public TreeHelper setSmallTreeGenerator(WorldGenerator smallTreeGenerator) {
        this.smallTreeGenerator = smallTreeGenerator;
        return this.treeHelper;
    }
}
