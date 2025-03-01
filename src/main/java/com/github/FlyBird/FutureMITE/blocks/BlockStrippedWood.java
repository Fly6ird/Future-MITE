package com.github.FlyBird.FutureMITE.blocks;

import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.CreativeTabs;
import net.minecraft.Material;

public class BlockStrippedWood extends Block {
    protected BlockStrippedWood(int par1) {
        super(par1, Material.wood, new BlockConstants());
        modifyMinHarvestLevel(1);
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(1.0F);
        setStepSound(Blocks.stepSoundWood);
    }
}
