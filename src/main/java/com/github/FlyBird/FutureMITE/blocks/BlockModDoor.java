package com.github.FlyBird.FutureMITE.blocks;

import net.minecraft.*;

public class BlockModDoor extends BlockDoor {
    private Item itemdoor;

    public BlockModDoor(int blockID) {
        super(blockID, Material.wood);
        //setCreativeTab(CreativeTabs.tabDecorations);
    }

    public BlockModDoor(int blockID, String id) {
        this(blockID);
        this.setUnlocalizedName("doors." + id);
        this.setTextureName("futuremite:doors/" + id);
    }

    public BlockModDoor(int blockID, String id,Item item) {
        this(blockID);
        this.setUnlocalizedName("doors." + id);
        this.setTextureName("futuremite:doors/" + id);
        this.itemdoor=item;
    }

    public void setDoorItem(Item door)
    {
        this.itemdoor=door;
    }
    public Item getDoorItem() {
        return itemdoor;
    }
}
