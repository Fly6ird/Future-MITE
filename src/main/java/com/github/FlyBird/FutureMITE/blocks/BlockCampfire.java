package com.github.FlyBird.FutureMITE.blocks;


import com.github.FlyBird.FutureMITE.misc.DamageSourceExtend;
import com.github.FlyBird.FutureMITE.misc.EnumParticles;
import com.github.FlyBird.FutureMITE.render.RenderTypes;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import net.minecraft.*;

import java.util.Objects;
import java.util.Random;

public class BlockCampfire extends BlockContainer {

    private Icon BlockCampfireIcon;
    private Icon ItemCampfireIcon;
    private Icon campFire_FireIcon;
    private Icon campFire_LogLitIcon;
    private boolean isActive;
    private static boolean keepCampfireData;
    public static final DamageSource CAMPFIRE_DAMAGE = (new DamageSourceExtend("CampFire"));

    private final float damage;

    protected BlockCampfire(int par1, float damage, boolean isActive) {
        super(par1, Material.wood, new BlockConstants().setNotAlwaysLegal().setNeverHidesAdjacentFaces());
        this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0);
        this.setHardness(2.0f);
        this.setStepSound(soundWoodFootstep);
        this.damage = damage;
        this.isActive = isActive;
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if ((info.wasHarvestedByPlayer() || info.wasSelfDropped() || info.wasNotLegal())) {
            return this.dropBlockAsEntityItem(info, new ItemStack(Item.coal, 2, 1));
        }
        return 0;
    }

    private int FireTime = 0;

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (par1World.isWorldServer()) {
            if (par5Entity instanceof EntityLivingBase) {
                this.FireTime++;
                if (FireTime >> 4 != 0) {
                    FireTime = 0;
                    par5Entity.attackEntityFrom(new Damage(CAMPFIRE_DAMAGE, damage));
                }
            }
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        String textureName=isActive?this.getTextureName():"campfire";
        if(isActive)

        this.campFire_FireIcon = par1IconRegister.registerIcon("futuremite:campfire/" +textureName + "_fire");
        this.BlockCampfireIcon = par1IconRegister.registerIcon("futuremite:campfire_log");
        this.campFire_LogLitIcon = par1IconRegister.registerIcon("futuremite:campfire/" + textureName+ "_log_lit");
        if (this.isActive)
            this.ItemCampfireIcon = par1IconRegister.registerIcon("futuremite:item/" + this.getTextureName());
        else
            this.ItemCampfireIcon = par1IconRegister.registerIcon("futuremite:item/campfire_base");
    }

    public Icon getFireIcon(int index) {
        if (index == 0)
            return this.campFire_FireIcon;
        else
            return this.campFire_LogLitIcon;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if (side == 1)
            return this.ItemCampfireIcon;   //返回影子物品的图标
        return this.BlockCampfireIcon;
    }

    @Override
    public int getRenderType() {
        return RenderTypes.campfireRenderType;
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
        if (face.isEastOrWest()) {//    0000
            metadata |= 4;//        4为  100      实际效果获取第三位的值
        } else if (face.isNorthOrSouth()) {
            metadata |= 8;    //实际效果获取第四位的值
        }
        return metadata;
    }

    public void breakBlock(World world, int x, int y, int z, int blockid, int metadata) {
        if (!keepCampfireData) {
            if (!world.isRemote) {
                TileEntity tile = world.getBlockTileEntity(x, y, z);
                if (tile instanceof TileEntityCampfire)
                    ((TileEntityCampfire) tile).popItems();
            }
            super.breakBlock(world, x, y, z, blockid, metadata);
        }
    }

    @Override
    public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random) {
        float var9;
        float var8;
        float var7;
        int var6;

        if (this.isActive) {
            if (par5Random.nextInt(10) == 0) {
                par1World.playSound((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f, "futuremite:block.campfire.crackle", 1.0f + par5Random.nextFloat(), par5Random.nextFloat() * 0.7f + 0.3f, false);
            }

            for (var6 = 0; var6 < 4; ++var6) {
                var7 = (float) x + 0.5f + par5Random.nextFloat() * 0.1f;
                var8 = (float) y + par5Random.nextFloat();//随机返回一个0—1.0的浮点
                var9 = (float) z + 0.5f;
                par1World.spawnParticle(EnumParticles.largerSmoke, x, y, z, 0.0, 0.0, 0.0);
                par1World.spawnParticle(EnumParticle.smoke, var7, var8, var9, 0.0, 0.0, 0.0);
            }

            if (par5Random.nextInt(5) == 0 && Objects.equals(this.getTextureName(), "campfire")) {
                for (int i = 0; i < par5Random.nextInt(1) + 1; ++i) {
                    double var21 = (float) x + par5Random.nextFloat();
                    double var22 = (double) y + this.maxY[Minecraft.getThreadIndex()];
                    double var23 = (float) z + par5Random.nextFloat();
                    par1World.spawnParticle(EnumParticle.lava, var21, var22, var23, 0.0, 0.0, 0.0);
                }
            }
        }

    }

    public static void updateCampfireBlockState(boolean isBurned, World par1World, int x, int y, int z) {
        int meta = par1World.getBlockMetadata(x, y, z);
        TileEntity tileEntity = par1World.getBlockTileEntity(x, y, z);

        //keepCampfireData = true;
        if (isBurned) {
            par1World.setBlock(x, y, z, Blocks.campfire.blockID, meta, 3);
        } else {
            par1World.setBlock(x, y, z, Blocks.extinguishedCampfire.blockID, meta, 3);
        }

        keepCampfireData = false;
        if (tileEntity != null) {
            tileEntity.validate();
            par1World.setBlockTileEntity(x, y, z, tileEntity);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float dx, float dy, float dz) {
        ItemStack stack = player.getHeldItemStack();
        if (stack != null) {
            TileEntityCampfire tile = (TileEntityCampfire) world.getBlockTileEntity(x, y, z);
            if (isActive) {
                if (stack.getItem() instanceof ItemShovel) {
                    if (player.onClient()) {
                        player.swingArm();
                        Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
                    } else {
                        //Auxiliary sound    1004灭火声音
                        world.playAuxSFXAtEntity(null, 1004, x, y, z, 0);
                        player.tryDamageHeldItem(DamageSource.generic, 2);
                        updateCampfireBlockState(false, world, x, y, z);
                    }
                    return true;
                }


                if (tile == null) {
                    return false;
                }
                if (tile.getCookFood(stack) != null) {
                    ItemStack queueItemStack = new ItemStack(stack.itemID, 1);
                    if (tile.joinCookQueue(queueItemStack)) {
                        if (world.isRemote) {
                            player.swingArm();
                        } else {
                            if (!player.capabilities.isCreativeMode)
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
            } else {
                if (tile != null && tile.getBurnTime() > 0) {
                    if (stack.getItem() instanceof ItemFlintAndSteel) {
                        updateCampfireBlockState(true, world, x, y, z);
                        if (player.onClient()) {
                            player.swingArm();
                        } else {
                            world.playSoundAtEntity(player, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
                            player.tryDamageHeldItem(DamageSource.generic, 1);
                        }
                    } else if (stack.getItem() instanceof ItemFireball) {
                        updateCampfireBlockState(true, world, x, y, z);
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
                        if (tile != null && !player.capabilities.isCreativeMode && tile.addBurnTime(stack.getItem().getBurnTime(stack)))
                            --stack.stackSize;
                    }
                }

            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        TileEntityCampfire tileEntity = new TileEntityCampfire(this);
        //tileEntity.addBurnTime(6000);
        return tileEntity;
    }
}

