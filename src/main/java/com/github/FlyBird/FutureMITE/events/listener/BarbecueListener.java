package com.github.FlyBird.FutureMITE.events.listener;

import com.github.FlyBird.FutureMITE.blocks.BlockModLog;


import moddedmite.rustedironcore.api.event.listener.IBarbecueListener;
import net.minecraft.BlockLog;
import net.minecraft.Item;
import net.minecraft.ItemStack;

public class BarbecueListener implements IBarbecueListener {
    @Override
    public ItemStack getCookResult(ItemStack input) {
        Item item = input.getItem();
        if (item.isBlock() && (item.getAsItemBlock().getBlock() instanceof BlockModLog)) {
            return new ItemStack(Item.coal, input.stackSize, 1);
        }
        return null;
    }

    @Override
    public boolean isCookResult(ItemStack itemStack) {
        if (itemStack.itemID == Item.coal.itemID && itemStack.getItemSubtype() == 1) {
            return true;
        }
        return false;
    }
}
