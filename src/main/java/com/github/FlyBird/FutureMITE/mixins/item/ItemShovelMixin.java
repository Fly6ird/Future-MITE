package com.github.FlyBird.FutureMITE.mixins.item;

import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;


@Mixin({ItemShovel.class})
public abstract class ItemShovelMixin extends ItemTool {

    protected ItemShovelMixin(int par1, Material material) {
        super(par1, material);
    }


    @Unique
    private static boolean tryTillSoil(World world, int x, int y, int z, EnumFace face, EntityPlayer player, ItemStack item_stack) {
        if (!player.canPlayerEdit(x, y, z, item_stack)) {
            return false;
        }
        if (face.isBottom()) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        if (!world.isAirBlock(x, y + 1, z) && world.getBlockWithRefreshedBounds(x, y + 1, z).getBlockBoundsMinY(Minecraft.getThreadIndex()) <= 0.0) {
            return false;
        }
        if (block != Block.grass && block != Block.dirt) {
            return false;
        }
        if (player.onClient()) {
            player.swingArm();
            Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
        } else {
            world.playSoundAtBlock(x, y, z, Block.tilledField.stepSound.getStepSound(), (Block.tilledField.stepSound.getVolume() + 1.0f) / 2.0f, Block.tilledField.stepSound.getPitch() * 0.8f);
            player.tryDamageHeldItem(DamageSource.generic, 25);
            player.addHungerServerSide(world.getBlockHardness(x, y, z) / 2.0f * EnchantmentHelper.getEnduranceModifier(player));
            world.setBlock(x, y, z, Blocks.dirtPath.blockID);
        }
        return true;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        if (rc == null || !rc.isBlock()) {
            if (this.canBlock()) {
                player.setHeldItemInUse();
                return true;
            } else {
                return false;
            }
        }
        if (rc.face_hit.isTop()||rc.face_hit.isSide()) {
            boolean flag1=tryTillSoil(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
            if(!flag1)
            {
                if (this.canBlock()) {
                    player.setHeldItemInUse();
                    return true;
                } else {
                    return false;
                }
            }
            else
                return true;
        }
        else
        {
            if (this.canBlock()) {
                player.setHeldItemInUse();
                return true;
            } else {
                return false;
            }
        }

    }
}
