package com.github.FlyBird.FutureMITE.items;

import com.github.FlyBird.FutureMITE.entities.EntityNewBoat;
import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Items extends Item {
    public static final ItemNewBoat oakBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.OAK, false);
    public static final ItemNewBoat birchBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.BIRCH, false);
    public static final ItemNewBoat jungleBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.JUNGLE, false);
    public static final ItemNewBoat spruceBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.SPRUCE, false);

    public static final ItemNewBoat oakChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.OAK, true);
    public static final ItemNewBoat birchChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.BIRCH, true);
    public static final ItemNewBoat jungleChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.JUNGLE, true);
    public static final ItemNewBoat spruceChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.SPRUCE, true);


    public static void registerItems(ItemRegistryEvent event) {

    }

    public static void registerRecipes(RecipeRegistryEvent register) {
        registerBoatRecipes(register,oakBoat);
        registerBoatRecipes(register,birchBoat);
        registerBoatRecipes(register,jungleBoat);
        registerBoatRecipes(register,spruceBoat);
        registerBoatRecipes(register,oakChestBoat);
        registerBoatRecipes(register,birchChestBoat);
        registerBoatRecipes(register,jungleChestBoat);
        registerBoatRecipes(register,spruceChestBoat);
    }

    public static void  registerBoatRecipes(RecipeRegistryEvent register,ItemNewBoat itemNewboat) {
        //ItemNewBoat.BoatInfo boatInfo = ItemNewBoat.BOAT_INFO.get(itemNewboat.type);
        if (!itemNewboat.isChest)
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"B B", "A A", "AAA", Character.valueOf('A'), itemNewboat.boatinfo.getPlank(), Character.valueOf('B'), new ItemStack(Item.shovelWood, 1)});
        else {
            ItemNewBoat.BoatInfo boatInfo = ItemNewBoat.BOAT_INFO.get(itemNewboat.name);//获取没有箱子的对应Boatinfo  以便为了获取没有箱子的船
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"A  ", "B  ", "   ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{" A ", " B ", "   ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"  A", "  B", "   ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"   ", "A  ", "B  ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"   ", " A ", " B ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"   ", "  A", "  B", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});

            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"B B", "ACA", "AAA", Character.valueOf('A'), itemNewboat.boatinfo.getPlank(), Character.valueOf('B'), new ItemStack(Item.shovelWood, 1), Character.valueOf('C'),new ItemStack(Block.chest)});
        }
    }

    public static int getNextItemID() {
        return IdUtil.getNextItemID();
    }
}
