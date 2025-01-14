package com.github.FlyBird.FutureMITE.events.listener;

import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class LootTableRegistry implements ILootTableRegisterListener {
    @Override
    public void onBlackSmithRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 3, 2));
        original.add(new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 1));
        original.add(new WeightedRandomChestContent(Item.copperNugget.itemID, 0, 1, 5, 5));
        original.add(new WeightedRandomChestContent(Item.silverNugget.itemID, 0, 1, 5, 5));
        original.add(new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 1, 8, 5));
        original.add(new WeightedRandomChestContent(Item.ironNugget.itemID, 0, 1, 5, 5));
        original.add(new WeightedRandomChestContent(Item.ingotCopper.itemID, 0, 1, 3, 3));
        original.add(new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 3, 3));
        original.add(new WeightedRandomChestContent(Item.coinCopper.itemID, 0, 1, 20, 15));
        original.add(new WeightedRandomChestContent(Item.coinSilver.itemID, 0, 1, 10, 10));
        original.add(new WeightedRandomChestContent(Item.coinGold.itemID, 0, 1, 3, 5));
        original.add(new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 3, 15));
        original.add(new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 3, 15));
        original.add(new WeightedRandomChestContent(Item.plateLeather.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.helmetLeather.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.legsLeather.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.bootsLeather.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3));
        original.add(new WeightedRandomChestContent(Item.helmetCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.plateCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.legsCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.bootsCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.helmetChainCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.plateChainCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.legsChainCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.bootsChainCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.helmetIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.plateIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.legsIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.bootsIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.helmetChainIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.plateChainIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.legsChainIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.bootsChainIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.shovelCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.shovelIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.hoeCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.hoeIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.mattockCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.mattockIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.hatchetCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.hatchetIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.axeCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.axeIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.shearsCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.shears.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.scytheCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.pickaxeCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.daggerCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.daggerIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.swordCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.swordIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.warHammerCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.warHammerIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.battleAxeCopper.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Item.battleAxeIron.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Block.sponge.blockID, 1, 1, 2, 10));
    }

}
