package com.github.FlyBird.FutureMITE.mixins.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockTorch.class)
public abstract class BlockTorchMixin extends BlockMounted {

    public BlockTorchMixin(int id, Material material, BlockConstants constants) {
        super(id, material, constants);
    }

    public boolean canMountToBlock(int metadata, Block neighbor_block, int neighbor_block_metadata, EnumFace face) {
        if (face.isTop()) {
            if (neighbor_block instanceof BlockFence || neighbor_block instanceof BlockWall) {
                return true;
            }

            if (neighbor_block != leaves && !(neighbor_block instanceof BlockDirectional) && neighbor_block.isFaceFlatAndSolid(neighbor_block_metadata, face)) {
                return true;
            }
        } else if (face.isSide()) {
            if (neighbor_block == snow && neighbor_block_metadata > 2) {
                return true;
            }

            if (neighbor_block.isSingleSlab()) {
                if (BlockSlab.isBottom(neighbor_block_metadata)) {
                    return true;
                }
            } else if (neighbor_block instanceof BlockStairs && (neighbor_block_metadata & 4) == 0) {
                return true;
            }
        }

        if (neighbor_block == cloth) {
            return true;
        } else {
            return (neighbor_block instanceof BlockPistonBase || neighbor_block instanceof BlockPistonMoving || neighbor_block instanceof BlockPistonExtension) && face == Block.pistonBase.getFrontFace(neighbor_block_metadata) ? true : super.canMountToBlock(metadata, neighbor_block, neighbor_block_metadata, face);
        }
    }
}
