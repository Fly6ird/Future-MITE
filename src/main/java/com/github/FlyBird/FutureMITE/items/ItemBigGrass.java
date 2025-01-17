package com.github.FlyBird.FutureMITE.items;

import net.minecraft.Block;
import net.minecraft.Icon;
import net.minecraft.ItemColored;
import net.minecraft.ItemStack;

public class ItemBigGrass extends ItemColored {

    public ItemBigGrass(Block block) {
        super(block);
    }

    public Icon getIconFromSubtype(int par1) {
        return this.getBlock().getIcon(0, (par1&2));
    }


    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if(par1ItemStack!=null)
            return "tile."+((par1ItemStack.getItemSubtype()&3)>>1==0?"tall_grass":"large_fern");
        return "";
    }
}
