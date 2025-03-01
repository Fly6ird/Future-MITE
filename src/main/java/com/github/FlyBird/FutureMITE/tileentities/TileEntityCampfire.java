package com.github.FlyBird.FutureMITE.tileentities;

import com.github.FlyBird.FutureMITE.blocks.BlockCampfire;
import net.minecraft.*;

import java.util.Random;

import static com.github.FlyBird.FutureMITE.blocks.BlockCampfire.updateCampfireBlockState;

public class TileEntityCampfire extends TileEntity {
    private ItemStack[] burnItemStacks = new ItemStack[4];
    private int[] burnFoodTime = new int[4];
    protected static final Random RAND = new Random();

    private int burnTime = 6000;//营火燃烧时间   默认6000   1s=20tick   也就是5分钟

    public TileEntityCampfire() {

    }

    public TileEntityCampfire(Block block) {
        this.setBlock(block);
    }

    @Override
    public void updateEntity() {
        Block currentBlock = this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);
        if (currentBlock instanceof BlockCampfire) {
            boolean isActive = ((BlockCampfire) currentBlock).getIsActive();
            if (isActive) {
                if (this.burnTime > 0) {
                    --this.burnTime;
                    for (int i = 0; i < burnItemStacks.length; i++) {
                        if (burnItemStacks[i] != null) {
                            if (burnFoodTime[i] > 0)
                                --burnFoodTime[i];
                            else if (burnFoodTime[i] <= 0 && !isCookedItemStack(burnItemStacks[i])) {//如果是还没熟的，并且时间小于0
                                burnItemStacks[i] = getCookFood(burnItemStacks[i]);
                                burnFoodTime[i] = 60;//熟肉只显示3s
                            } else if (burnFoodTime[i] <= 0 && isCookedItemStack(burnItemStacks[i])) {//如果是熟的，并且时间小于0
                                popItem(burnItemStacks[i], this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                                burnItemStacks[i] = null;
                            }
                        }
                    }
                } else {
                    if (!this.worldObj.isRemote) {
                        updateCampfireBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                    }
                }
            }
        }
    }


    /**
     * Drops all the campfire's items into the world.
     */
    public void popItems() {
        for (int slot = 0; slot < 4; ++slot) {
            if (burnItemStacks[slot] != null) {
                popItem(burnItemStacks[slot], getWorldObj(), xCoord, yCoord, zCoord);
                burnItemStacks[slot] = null;
            }
        }
    }

    public static void popItem(ItemStack stack, World world, int x, int y, int z) {
        if (stack != null && stack.stackSize > 0) {
            EntityItem entityitem = new EntityItem(world, x + RAND.nextDouble() * 0.75 + 0.125, y + RAND.nextDouble() * 0.375 + 0.5,
                    z + RAND.nextDouble() * 0.75 + 0.125, stack);

            EntityXPOrb entityXPOrb;
            if (stack.getItem() instanceof ItemMeat) {
                if (((ItemMeat) stack.getItem()).is_cooked) {
                    entityXPOrb = new EntityXPOrb(world, x + RAND.nextDouble() * 0.75 + 0.125, y + RAND.nextDouble() * 0.375 + 0.5,
                            z + RAND.nextDouble() * 0.75 + 0.125, stack.getExperienceReward());

                    entityXPOrb.motionX = RAND.nextGaussian() * 0.05;
                    entityXPOrb.motionY = RAND.nextGaussian() * 0.05 + 0.2;
                    entityXPOrb.motionZ = RAND.nextGaussian() * 0.05;

                    if (!world.isRemote) {
                        world.spawnEntityInWorld(entityXPOrb);
                    }
                }
            }

            entityitem.motionX = RAND.nextGaussian() * 0.05;
            entityitem.motionY = RAND.nextGaussian() * 0.05 + 0.2;
            entityitem.motionZ = RAND.nextGaussian() * 0.05;

            if (!world.isRemote) {
                world.spawnEntityInWorld(entityitem);
            }

        }
    }

    public boolean addBurnTime(int tick) {
        if (burnTime >= 6000)
            return false;
        else if (burnTime + tick >= 6000) {
            this.burnTime = 6000;
            return true;
        }
        this.burnTime += tick;
        return true;
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    public ItemStack[] getBurnItemStacks() {
        return burnItemStacks;
    }

    public boolean isCookedItemStack(ItemStack stack) {
        return ((ItemMeat) stack.getItem()).is_cooked;
    }

    public boolean joinCookQueue(ItemStack itemStack) {
        for (int i = 0; i < this.burnItemStacks.length; i++) {
            if (burnItemStacks[i] == null) {
                burnItemStacks[i] = itemStack;
                burnFoodTime[i] = 400;  //烧20s
                return true;
            }
        }
        return false;
    }

    //来同步服务端和客户都消息的
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 5, var1);
    }

    public ItemStack getCookFood(ItemStack food) {
        if (food.getItem() instanceof ItemMeat)
            if (!((ItemMeat) food.getItem()).is_cooked) {
                ItemStack stackSmeltingResult = FurnaceRecipes.smelting().getSmeltingResult(food, 1);
                if (stackSmeltingResult != null)
                    return new ItemStack(stackSmeltingResult.itemID, 1);
            }
        return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbtTagList = par1NBTTagCompound.getTagList("Contains");
        this.burnItemStacks = new ItemStack[4];
        this.burnFoodTime = new int[4];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound) nbtTagList.tagAt(i);
            byte index = tagCompound.getByte("Index");
            if (index >= 0 && index < this.burnItemStacks.length) {
                this.burnItemStacks[index] = ItemStack.loadItemStackFromNBT(tagCompound);
                this.burnFoodTime[index] = tagCompound.getInteger("BurnFoodTime");
            }
        }

        this.burnTime = par1NBTTagCompound.getInteger("BurnTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("BurnTime", this.burnTime);
        NBTTagList nbtTagList = new NBTTagList();

        for (int i = 0; i < this.burnItemStacks.length; ++i) {
            if (this.burnItemStacks[i] != null) {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Index", (byte) i);
                this.burnItemStacks[i].writeToNBT(nbtTagCompound);
                nbtTagCompound.setInteger("BurnFoodTime", this.burnFoodTime[i]);
                nbtTagList.appendTag(nbtTagCompound);
            }
        }

        par1NBTTagCompound.setTag("Contains", nbtTagList);
    }
}
