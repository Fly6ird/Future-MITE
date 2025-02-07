package com.github.FlyBird.FutureMITE.blocks;

import com.github.FlyBird.FutureMITE.FutureMITEStart;
import com.github.FlyBird.FutureMITE.items.ItemModLeaves;
import moddedmite.rustedironcore.api.block.DoorBlock;
import moddedmite.rustedironcore.api.item.DoorItem;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

import static com.github.FlyBird.FutureMITE.blocks.Blocks.getNextBlockID;
import static net.xiaoyu233.fml.reload.utils.IdUtil.getNextItemID;

public class TreeHelper {
    public final String id;

    public final BlockStrippedLog Log;
    public final BlockModWoodPlanks Planks;
    public final BlockModLeaves Leaves;
    public final Block Stairs;
    public final BlockModWoodSlab SingleSlab;
    public final BlockDoubleSlab DoubleSlab;
    public final BlockModSapling Sapling = new BlockModSapling(getNextBlockID(), this);
    public final DoorBlock Door = new DoorBlock(getNextBlockID(), Material.wood, () -> this.ItemDoor);
    public final DoorItem ItemDoor = new DoorItem(getNextItemID(), Material.wood, () -> this.Door);
    public final BlockModTrapDoor TrapDoor;

    public TreeHelper(String id) {
        this.id = id;
        this.Log = new BlockStrippedLog(getNextBlockID(), id);
        this.Planks = new BlockModWoodPlanks(getNextBlockID(), id);
        this.Leaves = new BlockModLeaves(getNextBlockID(), id);
        this.Stairs = (new BlockModStairs(getNextBlockID(), this.Planks, 0));
        this.SingleSlab = (new BlockModWoodSlab(getNextBlockID(), this.Planks, 0, id));
        this.DoubleSlab = (new BlockDoubleSlab(getNextBlockID(), this.SingleSlab));
        this.TrapDoor = (new BlockModTrapDoor(getNextBlockID(), id, Planks));
    }

    public void registerRecipes(RecipeRegistryEvent register) {
        for (int i = 0; i <= 1; i++)
            register.registerShapedRecipe(new ItemStack(Log, 3, i + 2), true, "AA", "AA", 'A', new ItemStack(Log, 1, i));
        register.registerShapedRecipe(new ItemStack(Planks, 4), true, "A", 'A', Log);
        register.registerShapedRecipe(new ItemStack(Stairs, 4), true, "A  ", "AA ", "AAA", 'A', new ItemStack(Planks));
        register.registerShapedRecipe(new ItemStack(SingleSlab, 6), true, "AAA", 'A', new ItemStack(Planks));
        TrapDoor.registerRecipe(register);

        register.registerShapedRecipe(new ItemStack(this.ItemDoor, 1), true, new Object[]{"AA", "AA", "AA", Character.valueOf('A'), new ItemStack((this.Planks), 1)});
    }

    //注册物品事件
    public void registerItemBlocks(ItemRegistryEvent registryEvent) {
        registryEvent.register(FutureMITEStart.NameSpaceCompact, "futuremite:doors/" + id, "doors." + id, this.ItemDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "futuremite:planks/" + id, "planks." + id, this.Planks);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, this.DoubleSlab);
        registerModItemBlocks(registryEvent, this.SingleSlab, "slab." + id);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "futuremite:doors/" + id, id + "_door", this.Door);
        registerModItemBlocks(registryEvent, this.Log, "log." + id + "_wood");
        registerModItemBlocks(registryEvent, this.Leaves, "leaves." + id);
        registerModItemBlocks(registryEvent, this.Stairs, "stairs." + id);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, this.TrapDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "futuremite:sapling/" + id + "_sapling", id + "_sapling", this.Sapling);

        Item.itemsList[this.SingleSlab.blockID] = (new ItemSlab(this.SingleSlab, this.DoubleSlab, false));
        Item.itemsList[this.DoubleSlab.blockID] = (new ItemSlab(this.SingleSlab, this.DoubleSlab, true));
        Item.itemsList[this.Leaves.blockID] = (new ItemModLeaves(this.Leaves));
    }

    public void registerModItemBlocks(ItemRegistryEvent registryEvent, Block block, String name) {
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, block);
        block.setUnlocalizedName(name);
    }
}
