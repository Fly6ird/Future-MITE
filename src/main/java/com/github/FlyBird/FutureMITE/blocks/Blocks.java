package com.github.FlyBird.FutureMITE.blocks;

import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.Material;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Blocks extends Block {
    protected Blocks(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    //注册方块物品事件
    public static void registerItemBlocks(ItemRegistryEvent registryEvent) {

    }

    //注册方块的合成事件
    public static void registerRecipes(RecipeRegistryEvent register) {

    }

    public static int getNextBlockID() {return IdUtil.getNextBlockID();}
}
