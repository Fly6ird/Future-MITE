package com.github.FlyBird.FutureMITE.blocks;

import net.minecraft.Block;
import net.minecraft.BlockSlab;
import net.minecraft.Icon;
import net.minecraft.Material;

public class BlockModWoodSlab extends BlockSlab {
    protected final Block modelBlock;
    protected final int modelBlockMetadata;
    protected final String name;

    public BlockModWoodSlab(int id, Block block, int metadata, String name) {
        super(id, Material.wood);
        this.setUnlocalizedName("slab");
        modelBlock = block;
        modelBlockMetadata = metadata;
        this.name = name;
    }

    public float getBlockHardness(int metadata) {
        return 0.4F;
    }

    public int getGroup() {
        return 2;
    }

    public String[] getTypes() {
        return new String[]{"slab"};
    }


    public boolean isValidMetadata(int metadata) {
        return metadata == 0 || metadata == 8;
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return 0;
    }

    public Icon getIcon(int par1, int par2) {
        return this.modelBlock.getIcon(par1, this.modelBlockMetadata);
    }

    public Block getModelBlock(int metadata) {
        return modelBlock;
    }
}
