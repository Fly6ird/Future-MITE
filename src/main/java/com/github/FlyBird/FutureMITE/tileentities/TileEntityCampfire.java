package com.github.FlyBird.FutureMITE.tileentities;

import net.minecraft.*;

public class TileEntityCampfire extends TileEntity {
    private ItemStack[] burnItemStacks = new ItemStack[4];
    private int[] burnFoodTime = new int[4];

    private int burnTime=12000;//营火燃烧时间   默认600   1s=20tick   也就是10分钟

    public TileEntityCampfire(){

    };
    public TileEntityCampfire(Block block) {
        this.setBlock(block);
    }

    @Override
    public void updateEntity() {
        if(this.burnTime>0){
            --this.burnTime;
            for (int i = 0; i < burnItemStacks.length; i++) {
                if(burnItemStacks[i]!=null&&burnFoodTime[i]>0)
                    --burnFoodTime[i];
                if(burnItemStacks[i]!=null&&burnFoodTime[i]<=0)
                {
                    burnItemStacks[i]=getCookFood(burnItemStacks[i]);
                    burnFoodTime[i]=60;//熟肉只显示30s
                }
            }
        }
    }

    public boolean joinCookQueue(ItemStack itemStack){
        if(getCookFood(itemStack)!=null)
        {
            for (int i = 0; i < this.burnItemStacks.length; i++) {
                if(burnItemStacks[i]==null)
                {
                    burnItemStacks[i]=itemStack;
                    burnFoodTime[i]=200;  //烧10s
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean isNeedItemStack(ItemStack itemStack){
        return itemStack.itemID == Item.beefRaw.itemID || itemStack.itemID == Item.chickenRaw.itemID || itemStack.itemID == Item.porkRaw.itemID
                || itemStack.itemID == Item.fishRaw.itemID || itemStack.itemID == Item.fishLargeRaw.itemID || itemStack.itemID == Item.wormRaw.itemID
                || itemStack.itemID == Item.lambchopRaw.itemID;
    }

    public ItemStack getCookFood(ItemStack food){
        if(food.itemID==Item.beefRaw.itemID)//牛肉
            return new ItemStack(Item.beefCooked);
        else if(food.itemID==Item.chickenRaw.itemID)//鸡肉
            return new ItemStack(Item.beefCooked);
        else if(food.itemID==Item.porkRaw.itemID)//猪肉
            return new ItemStack(Item.porkCooked);
        else if(food.itemID==Item.fishRaw.itemID)//鱼肉
            return new ItemStack(Item.fishCooked);
        else if(food.itemID==Item.fishLargeRaw.itemID)//不知道
            return new ItemStack(Item.fishLargeCooked);
        else if(food.itemID==Item.wormRaw.itemID)//虫子
            return new ItemStack(Item.wormCooked);
        else if(food.itemID==Item.lambchopRaw.itemID)//羊肉
            return new ItemStack(Item.lambchopCooked);

        return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbtTagList = par1NBTTagCompound.getTagList("Contains");
        this.burnItemStacks = new ItemStack[nbtTagList.tagCount()];
        this.burnItemStacks = new ItemStack[nbtTagList.tagCount()];

        for(int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound)nbtTagList.tagAt(i);
            byte index = tagCompound.getByte("Index");
            if (index >= 0 && index < this.burnItemStacks.length) {
                this.burnItemStacks[index] = ItemStack.loadItemStackFromNBT(tagCompound);
                this.burnFoodTime[index] =tagCompound.getInteger("BurnFoodTime");
            }
        }

        this.burnTime = par1NBTTagCompound.getInteger("BurnTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("BurnTime", this.burnTime);
        NBTTagList nbtTagList = new NBTTagList();

        for(int i = 0; i < this.burnItemStacks.length; ++i) {
            if (this.burnItemStacks[i] != null) {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Index", (byte)i);
                this.burnItemStacks[i].writeToNBT(nbtTagCompound);
                nbtTagCompound.setInteger("BurnFoodTime", this.burnFoodTime[i]);
                nbtTagList.appendTag(nbtTagCompound);
            }
        }

        par1NBTTagCompound.setTag("Contains", nbtTagList);
    }
}
