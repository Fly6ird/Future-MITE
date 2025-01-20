package com.github.FlyBird.FutureMITE.blocks;

import com.github.FlyBird.FutureMITE.render.RenderTypes;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import net.minecraft.*;

public class BlockExtinguishedCampfire extends BlockContainer {
    private Icon BlockCampfireIcon;

    protected BlockExtinguishedCampfire(int par1) {
        super(par1, Material.wood, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setHardness(0.5f);
        this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.BlockCampfireIcon = par1IconRegister.registerIcon("campfire_log");
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if ((info.wasHarvestedByPlayer() || info.wasSelfDropped() || info.wasNotLegal())) {
            return this.dropBlockAsEntityItem(info, new ItemStack(Item.coal, 2, 1));
        }
        return 0;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float dx, float dy, float dz) {
        ItemStack stack = player.getHeldItemStack();
        TileEntityCampfire tile = (TileEntityCampfire) world.getBlockTileEntity(x, y, z);
        if (tile != null && tile.getBurnTime() > 0) {
            if (stack.getItem() instanceof ItemFlintAndSteel) {
                TileEntityCampfire.updateCampfireBlockState(true, world, x, y, z);
                if (player.onClient()) {
                    player.swingArm();
                } else {
                    world.playSoundAtEntity(player, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
                    player.tryDamageHeldItem(DamageSource.generic, 1);
                }
            } else if (stack.getItem() instanceof ItemFireball) {
                TileEntityCampfire.updateCampfireBlockState(true, world, x, y, z);
                if (player.onClient()) {
                    player.swingArm();
                } else {
                    world.playSoundAtBlock(x, y, z, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
                    --stack.stackSize;
                }
            }
        } else if (stack.getItem().getBurnTime(stack) > 0 && stack.getItem().getHeatLevel(stack) < 3) {
            if (world.isRemote) {
                player.swingArm();
            } else {
                if (!player.capabilities.isCreativeMode && tile.addBurnTime(stack.getItem().getBurnTime(stack)))
                    --stack.stackSize;
            }
        }
        return true;
    }

    //设置为false必须不能弄物品栏标签
    @Override
    public boolean canBeCarried() {
        return false;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return this.BlockCampfireIcon;
    }

    @Override
    public int getRenderType() {
        return RenderTypes.normalcampfireRenderType;
    }

    @Override
    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
        return false;
    }

    @Override
    public EnumDirection getDirectionFacing(int metadata) {
        return (metadata & 4) != 0 ? EnumDirection.WEST : ((metadata & 8) != 0 ? EnumDirection.NORTH : EnumDirection.DOWN);
    }

    @Override
    public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
        return this.getItemSubtype(metadata) | (direction.isUpOrDown() ? 0 : (direction.isEastOrWest() ? 4 : (direction.isNorthOrSouth() ? 8 : -1)));
    }

    @Override
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        int metadata = super.getMetadataForPlacement(world, x, y, z, item_stack, entity, face, offset_x, offset_y, offset_z);
        if (face.isEastOrWest()) {
            metadata |= 4;
        } else if (face.isNorthOrSouth()) {
            metadata |= 8;
        }
        return metadata;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCampfire(this);
    }
}
