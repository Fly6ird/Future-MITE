package com.github.FlyBird.FutureMITE.items;

import com.github.FlyBird.FutureMITE.FutureMITEStart;
import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoat;
import com.github.FlyBird.FutureMITE.items.register.RecipesFurnaceExtend;
import com.github.FlyBird.FutureMITE.misc.EnumWoodType;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Items extends Item {
    public static final ItemNewBoat oakBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.OAK, false);
    public static final ItemNewBoat birchBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.BIRCH, false);
    public static final ItemNewBoat jungleBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.JUNGLE, false);
    public static final ItemNewBoat spruceBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.SPRUCE, false);
    public static final ItemNewBoat cherryBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.CHERRY, false);
    public static final ItemNewBoat darkOakBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.DARK_OAK, false);
    public static final ItemNewBoat acaciaBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.ACACIA, false);

    public static final ItemNewBoat oakChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.OAK, true);
    public static final ItemNewBoat birchChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.BIRCH, true);
    public static final ItemNewBoat jungleChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.JUNGLE, true);
    public static final ItemNewBoat spruceChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.SPRUCE, true);
    public static final ItemNewBoat cherryChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.CHERRY, true);
    public static final ItemNewBoat darkOakChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.DARK_OAK, true);
    public static final ItemNewBoat acaciaChestBoat = new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.ACACIA, true);

    public static final Item sweetBerry = new ItemSweetBerry(getNextItemID(), 1, 1, false, true, Blocks.sweetBerryBush.blockID, "futuremite:sweet_berries").setUnlocalizedName("sweetberries");

    public static final ItemModDoor birchDoor = new ItemModDoor(getNextItemID(), Blocks.birchDoor, "birch");
    public static final ItemModDoor jungleDoor = new ItemModDoor(getNextItemID(), Blocks.jungleDoor, "jungle");
    public static final ItemModDoor spruceDoor = new ItemModDoor(getNextItemID(), Blocks.spruceDoor, "spruce");

    public static final ItemMeat rabbitRaw = (ItemMeat) (new ItemMeat(getNextItemID(), 3, 3, false, false, "futuremite:rabbit_raw")).setUnlocalizedName("rabbitRaw");
    public static final ItemMeat rabbitCooked = (ItemMeat) (new ItemMeat(getNextItemID(), 6, 6, false, false, "futuremite:rabbit_cooked")).setUnlocalizedName("rabbitCooked");

    public static final Item rabbitHide = (new Items(getNextItemID(), Material.leather, "futuremite:rabbit_hide")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("rabbitHide").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item rabbitFoot = (new Items(getNextItemID(), Material.blaze, "futuremite:rabbit_foot")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("rabbitFoot").setPotionEffect(PotionHelper.blazePowderEffect).setCreativeTab(CreativeTabs.tabBrewing);
    public static final Item bowlRabbitStew = (new ItemBowl(getNextItemID(), Material.beef_stew, "futuremite:rabbit_stew")).setFoodValue(12, 14, true, false, true).setPlantProduct().setUnlocalizedName("rabbit_stew");

    public static final ItemArmourStand armourStand = new ItemArmourStand(getNextItemID(), Material.wood, "futuremite:wooden_armorstand");

    //海晶碎片
    public static final Item prismarineShard = (new Items(getNextItemID(), Material.diamond, "futuremite:prismarine_shard")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("prismarineShard").setCreativeTab(CreativeTabs.tabMaterials);
    //海晶砂粒
    public static final Item prismarineCrystals = (new Items(getNextItemID(), Material.diamond, "futuremite:prismarine_crystals")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("prismarineCrystals").setCreativeTab(CreativeTabs.tabMaterials);

    public static final Item woodChips = (new ItemWoodChips(getNextItemID(), Material.wood, "futuremite:wood_chips")).setUnlocalizedName("woodChips").setCreativeTab(CreativeTabs.tabMaterials);

    protected Items(int id, Material material, String texture) {
        super(id, material, texture);
    }

    public static void registerItems(ItemRegistryEvent event) {
        event.register(FutureMITEStart.NameSpaceCompact,"futuremite:rabbit_raw",rabbitRaw);
        event.register(FutureMITEStart.NameSpaceCompact,"futuremite:rabbit_cooked",rabbitCooked);
        event.register(FutureMITEStart.NameSpaceCompact,"futuremite:rabbit_hide",rabbitHide);
        event.register(FutureMITEStart.NameSpaceCompact,"futuremite:rabbit_foot",rabbitFoot);
        event.register(FutureMITEStart.NameSpaceCompact,"futuremite:rabbit_stew",bowlRabbitStew);
    }

    public static void registerRecipes(RecipeRegistryEvent register) {
        RecipesFurnaceExtend.registerFurnaceRecipes();
        register.registerShapedRecipe(new ItemStack(armourStand, 1), true, new Object[]{"AAA", " A ", "ABA", Character.valueOf('A'), new ItemStack(Item.stick, 1), Character.valueOf('B'), new ItemStack(Block.stoneSingleSlab, 1)});

        birchDoor.registerRecipeWithVanilla(register, 1);
        jungleDoor.registerRecipeWithVanilla(register, 2);
        spruceDoor.registerRecipeWithVanilla(register, 3);
        rabbitCooked.is_cooked = true;
        register.registerShapelessRecipe(new ItemStack(bowlRabbitStew, 1, 0), true, new ItemStack(rabbitCooked), new ItemStack(carrot), new ItemStack(potato), new ItemStack(Block.mushroomRed), new ItemStack(bowlWater));
        register.registerShapelessRecipe(new ItemStack(bowlRabbitStew, 1, 0), true, new ItemStack(rabbitCooked), new ItemStack(carrot), new ItemStack(potato), new ItemStack(Block.mushroomBrown), new ItemStack(bowlWater));
        register.registerShapedRecipe(new ItemStack(leather, 1), true, new Object[]{"AA", "AA", 'A', new ItemStack(rabbitHide, 1)});

        for (ItemNewBoat itemNewBoat : new ItemNewBoat[]{oakBoat, birchBoat, jungleBoat, spruceBoat, cherryBoat,darkOakBoat,acaciaBoat,oakChestBoat, birchChestBoat, jungleChestBoat, spruceChestBoat,cherryChestBoat,darkOakChestBoat,acaciaChestBoat}) {
            itemNewBoat.registerBoatRecipes(register);
        }
    }


    public static int getNextItemID() {
        return IdUtil.getNextItemID();
    }
}
