package com.github.FlyBird.FutureMITE.tileentities;

import com.github.FlyBird.FutureMITE.blocks.BlockBarrel;
import com.github.FlyBird.FutureMITE.blocks.Blocks;
import net.minecraft.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TileEntityBarrel extends TileEntity implements IInventory {
    private ItemStack[] chestContents = new ItemStack[36];

    private boolean state;
    private boolean laststate;

    public int numUsingPlayers;

    private int ticksSinceSync;


    public TileEntityBarrel() {
    }

    public TileEntityBarrel(Block block) {
        this.setBlock(block);
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.chestContents[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.chestContents[par1] != null) {
            if ((this.chestContents[par1]).stackSize <= par2) {
                ItemStack itemStack = this.chestContents[par1];
                this.chestContents[par1] = null;
                onInventoryChanged();
                return itemStack;
            }
            ItemStack var3 = this.chestContents[par1].splitStack(par2);
            if ((this.chestContents[par1]).stackSize == 0)
                this.chestContents[par1] = null;
            onInventoryChanged();
            return var3;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.chestContents[par1] != null) {
            ItemStack var2 = this.chestContents[par1];
            this.chestContents[par1] = null;
            return var2;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.chestContents[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
            par2ItemStack.stackSize = getInventoryStackLimit();
        onInventoryChanged();
    }

    @Override
    public String getUnlocalizedInvName() {
        return "container.barrel";
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.chestContents = new ItemStack[getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 0xFF;
            if (var5 < this.chestContents.length)
                this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.chestContents.length; var3++) {
            if (this.chestContents[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.chestContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        par1NBTTagCompound.setTag("Items", var2);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D);
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        this.ticksSinceSync++;
        this.state = this.numUsingPlayers > 0; //记录桶是否有玩家使用

        if (!this.worldObj.isRemote && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
            this.numUsingPlayers = 0;
            float f = 5.0F;
            List var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((this.xCoord - f), (this.yCoord - f), (this.zCoord - f), ((this.xCoord + 1) + f), ((this.yCoord + 1) + f), ((this.zCoord + 1) + f)));
            Iterator<EntityPlayer> var3 = var2.iterator();
            while (var3.hasNext()) {
                EntityPlayer var4 = var3.next();
                if (var4.openContainer instanceof ContainerChest) {
                    IInventory var5 = ((ContainerChest) var4.openContainer).getLowerChestInventory();
                    if (var5 == this || (var5 instanceof InventoryLargeChest && ((InventoryLargeChest) var5).isPartOfLargeChest(this)))
                        this.numUsingPlayers++;
                }
            }
        }

        // 如果桶的状态发生了变化，才进行更新和播放音效
        if (this.laststate != this.state) {
            double var8 = this.xCoord + 0.5D;
            double var11 = this.zCoord + 0.5D;

            this.laststate = this.state;

            // 只在状态变化时更新元数据和播放音效
            if (!this.worldObj.isRemote) {
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, BitHelper.flipBit(this.getBlockMetadata(), 8), 2);
            }

            // 播放对应的音效
            if (this.numUsingPlayers > 0) {
                this.worldObj.playSoundEffect(var8, this.yCoord + 0.5D, var11, "futuremite:block.barrel.open", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            } else {
                this.worldObj.playSoundEffect(var8, this.yCoord + 0.5D, var11, "futuremite:block.barrel.close", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
    }


    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1) {
            this.numUsingPlayers = par2;
            return true;
        }
        return super.receiveClientEvent(par1, par2);
    }

    @Override
    public void openChest() {
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, Blocks.barrel.blockID);
        if (this.numUsingPlayers < 0)
            this.numUsingPlayers = 0;
        this.numUsingPlayers++;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID, 1, this.numUsingPlayers);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, (getBlockType()).blockID);
    }

    @Override
    public void closeChest() {
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, Blocks.barrel.blockID);
        if (getBlockType() != null && getBlockType() instanceof BlockBarrel) {
            this.numUsingPlayers--;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID, 1, this.numUsingPlayers);
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID);
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, (getBlockType()).blockID);
        }
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public void destroyInventory() {
        ItemStack[] item_stacks = this.chestContents;
        Arrays.fill(item_stacks, null);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        updateContainingBlockInfo();
    }
}