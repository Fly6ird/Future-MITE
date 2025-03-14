package com.github.FlyBird.FutureMITE.blocks;


import com.github.FlyBird.FutureMITE.items.Items;
import net.minecraft.*;

import java.util.Random;

public class BlockSeaLantern extends Block {
    protected BlockSeaLantern(int blockID) {
        super(blockID, Material.glass, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        setHardness(0.3F);
        this.setLightValue(1.0F);
        setStepSound(soundGlassFootstep);
        setTextureName("futuremite:sea_lantern");
        setUnlocalizedName("sea_lantern");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    //info.getHarvesterFortune()
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return this.dropBlockAsEntityItem(info, new ItemStack(Items.prismarineCrystals, MathHelper.clamp_int(this.quantityDropped(info.world.rand) + info.world.rand.nextInt(info.getHarvesterFortune() + 1), 1, 5), 0));
    }

    public boolean connectsWithFence() {
        return true;
    }

    public int quantityDropped(Random random) {
        return 2 + random.nextInt(2);
    }


    /*@Override
    public MapColor getMapColor(int meta) {

    }*/

    @Override
    public boolean canSilkHarvest(int metadata) {
        return true;
    }
}
