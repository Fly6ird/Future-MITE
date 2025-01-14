package com.github.FlyBird.FutureMITE.blocks;


import com.github.FlyBird.FutureMITE.misc.EnumWoodType;
import net.minecraft.BlockTrapDoor;
import net.minecraft.Material;

public class BlockModTrapDoor extends BlockTrapDoor {
    private final EnumWoodType woodType;

    protected BlockModTrapDoor(int par1, EnumWoodType type) {
        super(par1, Material.wood);
        this.woodType = type;
        //this.setLightOpacity(0);
    }

    public EnumWoodType getWoodType() {
        return woodType;
    }
}
