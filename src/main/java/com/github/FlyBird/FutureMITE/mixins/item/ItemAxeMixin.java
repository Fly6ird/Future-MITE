package com.github.FlyBird.FutureMITE.mixins.item;


import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.items.Items;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;


@Mixin({ItemAxe.class})
public abstract class ItemAxeMixin extends ItemTool {
    protected ItemAxeMixin(int par1, Material material) {
        super(par1, material);
    }

    @Shadow
    public abstract float getBaseDamageVsEntity();

    @Unique
    private static boolean tryBecomeWood(World world, int x, int y, int z, EnumFace face, EntityPlayer player, ItemStack item_stack) {
        if (!player.canPlayerEdit(x, y, z, item_stack)) {
            return false;
        }
        Block block = world.getBlock(x, y, z);
        int meta = block.getBlockSubtype(world.getBlockMetadata(x, y, z));
        int direction = world.getBlockMetadata(x, y, z);

        if (block != Block.wood) {
            return false;
        }
        if (player.onClient()) {
            player.swingArm();
            Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
        } else {
            if (meta == 0)
                world.setBlock(x, y, z, Blocks.oakLog.blockID, direction, 2);
            if (meta == 1)
                world.setBlock(x, y, z, Blocks.spruceLog.blockID, direction, 2);
            if (meta == 2)
                world.setBlock(x, y, z, Blocks.birchLog.blockID, direction, 2);
            if (meta == 3)
                world.setBlock(x, y, z, Blocks.jungleLog.blockID, direction, 2);
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);

            world.playSoundAtBlock(x, y, z, Block.wood.stepSound.getStepSound(), (Block.wood.stepSound.getVolume() + 1.0f) / 2.0f, Block.wood.stepSound.getPitch() * 0.8f);
            player.tryDamageHeldItem(DamageSource.generic, 10);
            player.addHungerServerSide(world.getBlockHardness(x, y, z) / 2.0f * EnchantmentHelper.getEnduranceModifier(player));
            dropWoodChips(world, x, y, z);

        }
        return true;
    }

    @Unique
    private static void dropWoodChips(World world, int x, int y, int z) {
        Random rand = new Random();

        // 设置一个不掉落木屑的概率，比如有30%的概率不掉落
        if (rand.nextInt(100) < 30) {
            return;  // 30%的概率不掉落木屑
        }
        // 基础掉落数量，先掉落1-2个木屑
        int chipCount = rand.nextInt(2) + 1; // 先保证至少掉1个
        // 使用一个递减的概率机制，生成更多木屑的概率较低
        if (rand.nextInt(100) < 20) {  // 30%的概率再掉落1个木屑
            chipCount++;
        }
        if (rand.nextInt(100) < 10) {  // 10%的概率再掉落1个木屑
            chipCount++;
        }
        if (rand.nextInt(100) < 2) {  // 2%的概率掉落额外1个木屑
            chipCount++;
        }

        // 确保木屑数量不会超过一个合理的范围，例如最多5个
        chipCount = Math.min(chipCount, 5);

        for (int i = 0; i < chipCount; i++) {
            // 随机生成木屑掉落的坐标
            double offsetX = rand.nextFloat() * 0.7F + 0.15F;
            double offsetY = rand.nextFloat() * 0.7F + 0.15F;
            double offsetZ = rand.nextFloat() * 0.7F + 0.15F;

            // 创建木屑实体
            EntityItem woodChip = new EntityItem(world, x + offsetX, y + offsetY, z + offsetZ,
                    new ItemStack(Items.woodChips, 1)); // 这里的`Items.apple`替换成你想要掉落的木屑物品

            // 设置木屑的掉落速度
            float velocityFactor = 0.05F;
            woodChip.motionX = (rand.nextGaussian() * velocityFactor);
            woodChip.motionY = (rand.nextGaussian() * velocityFactor + 0.2);
            woodChip.motionZ = (rand.nextGaussian() * velocityFactor);

            // 添加到世界中
            world.spawnEntityInWorld(woodChip);
        }
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

        boolean flag = tryBecomeWood(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
        if (!flag) {
            if (this.canBlock()) {
                player.setHeldItemInUse();
                return true;
            } else {
                return false;
            }
        }
        return flag;
    }
}
