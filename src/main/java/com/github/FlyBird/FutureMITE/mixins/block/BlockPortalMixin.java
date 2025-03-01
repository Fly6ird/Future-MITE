package com.github.FlyBird.FutureMITE.mixins.block;


import net.minecraft.Block;
import net.minecraft.BlockPortal;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockPortal.class)
public abstract class BlockPortalMixin {

    /**
     * @author FLyBird
     * @reason Make the portal support multiple sizes
     */
    @Overwrite
    public boolean tryToCreatePortal(World par1World, int x, int y, int z)
    {
        if (par1World.isTheEnd())
        {
            return false;
        }
        else
        {
            byte xDirection = 0;
            byte zDirection = 0;

            if (par1World.getBlockId(x - 1, y, z) == Block.obsidian.blockID || par1World.getBlockId(x + 1, y, z) == Block.obsidian.blockID)
            {
                xDirection = 1;
            }

            if (par1World.getBlockId(x, y, z - 1) == Block.obsidian.blockID || par1World.getBlockId(x, y, z + 1) == Block.obsidian.blockID)
            {
                zDirection = 1;
            }

            if (xDirection == zDirection)
            {
                return false;
            }
            else
            {
                if (par1World.getBlockId(x - xDirection, y, z - zDirection) == 0)
                {
                    x -= xDirection;
                    z -= zDirection;
                }

                int x_i;
                int y_i;

                for (x_i = -1; x_i <= 2; ++x_i)
                {
                    for (y_i = -1; y_i <= 3; ++y_i)
                    {
                        boolean isFrame = x_i == -1 || x_i == 2 || y_i == -1 || y_i == 3;

                        if (x_i != -1 && x_i != 2 || y_i != -1 && y_i != 3)
                        {
                            int blockId = par1World.getBlockId(x + xDirection * x_i, y + y_i, z + zDirection * x_i);

                            if (isFrame)
                            {
                                if (blockId != Block.obsidian.blockID)
                                {
                                    return false;
                                }
                            }
                            else if (blockId != 0 && blockId != Block.fire.blockID && blockId != Block.spark.blockID)
                            {
                                return false;
                            }
                        }
                    }
                }

                for (x_i = 0; x_i < 2; ++x_i)
                {
                    for (y_i = 0; y_i < 3; ++y_i)
                    {
                        par1World.setBlock(x + xDirection * x_i, y + y_i, z + zDirection * x_i, Block.portal.blockID, 0, 2);
                    }
                }

                int type = this.getPortalTypeBasedOnLocation(par1World, x, y, z, true);

                for (x_i = 0; x_i < 2; ++x_i)
                {
                    for (y_i = 0; y_i < 3; ++y_i)
                    {
                        par1World.setBlock(x + xDirection * x_i, y + y_i, z + zDirection * x_i, Block.portal.blockID, type, 2);
                    }
                }

                return true;
            }
        }
    }
    @Shadow public abstract int getPortalTypeBasedOnLocation(World world, int x, int y, int z, boolean test_for_runegate);

}
