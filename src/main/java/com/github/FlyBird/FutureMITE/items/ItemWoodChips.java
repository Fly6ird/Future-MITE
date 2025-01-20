package com.github.FlyBird.FutureMITE.items;

import huix.glacier.api.extension.item.IFuelItem;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemWoodChips extends Item implements IFuelItem {
    protected ItemWoodChips(int id, Material material, String texture) {
        super(id, material, texture);
    }

    @Override
    public int getHeatLevel() {
        return 1;
    }

    @Override
    public int getBurnTime() {
        return 50;
    }
}
