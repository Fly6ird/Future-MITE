package com.github.FlyBird.FutureMITE.blocks;

import com.github.FlyBird.FutureMITE.FutureMITEStart;
import com.github.FlyBird.FutureMITE.items.Items;
import com.github.FlyBird.FutureMITE.misc.EnumWoodType;
import com.github.FlyBird.FutureMITE.sound.*;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Blocks extends Block {
    public static final StepSound stepSoundChain= new StepSoundChain("chain",1.0f,1.0f);
    public static final StepSound stepSoundWood= new StepSoundWood("wood",1.0f,1.0f);
    public static final StepSound stepSoundLantern= new StepSoundLantern("Lantern",1.0f,1.0f);
    public static final StepSound stepSoundSweetBerryBush= new StepSoundSweetBerryBush("sweetBerryBush",1.0f,1.0f);
    public static final StepSound stepSoundSlime =new StepSoundSliem("slime",1.0f,1.0f);

    public static final Block grindStone =new BlockGrindstone(getNextBlockID());
    public static final Block bigGrass=new BlockBigGrass(getNextBlockID()).setHardness(0.02F).setCushioning(0.2F).setStepSound(soundGrassFootstep).setUnlocalizedName("bigGrass");
    public static final Block sweetBerryBush=new BlockSweetBerry(getNextBlockID());

    public static final Block chain=new BlockChain(getNextBlockID(),Material.iron);
    public static final Block silverChain =new BlockChain(getNextBlockID(),Material.silver);
    public static final Block copperChain =new BlockChain(getNextBlockID(),Material.copper);
    public static final Block adamantiumChain =new BlockChain(getNextBlockID(),Material.adamantium);
    public static final Block ancientChain =new BlockChain(getNextBlockID(),Material.ancient_metal);
    public static final Block mithrilChain =new BlockChain(getNextBlockID(),Material.mithril);

    public static final Block campfire=new BlockCampfire(getNextBlockID(),1.0f).setLightValue(0.6f);
    public static final Block soulCampfire=new BlockCampfire(getNextBlockID(),2.0f).setLightValue(0.6666f);
    public static final Block normalCampfire=new BlockNormalCampfire(getNextBlockID());

    public static final Block dirtPath = new BlockDirtPath(getNextBlockID());

    public static final Block soulTorch =new BlockSoulTorch(getNextBlockID());

    public static final BlockStrippedLog strippedBirch =new BlockStrippedLog(getNextBlockID());
    public static final BlockStrippedLog strippedJungle =new BlockStrippedLog(getNextBlockID());
    public static final BlockStrippedLog strippedOak =new BlockStrippedLog(getNextBlockID());
    public static final BlockStrippedLog strippedSpruce =new BlockStrippedLog(getNextBlockID());
    public static final Block strippedBirchWood =new BlockStrippedWood(getNextBlockID());
    public static final Block strippedJungleWood =new BlockStrippedWood(getNextBlockID());
    public static final Block strippedOakWood =new BlockStrippedWood(getNextBlockID());
    public static final Block strippedSpruceWood =new BlockStrippedWood(getNextBlockID());

    public static final Block lantern =new BlockLantern(getNextBlockID());
    public static final Block soulLantern =new BlockLantern(getNextBlockID());
    public static final Block ancientLantern =new BlockLantern(getNextBlockID());
    public static final Block adamantiumLantern =new BlockLantern(getNextBlockID());
    public static final Block silverLantern =new BlockLantern(getNextBlockID());
    public static final Block copperLantern =new BlockLantern(getNextBlockID());
    public static final Block mithrilLantern =new BlockLantern(getNextBlockID());

    public static final Block birchDoor=new BlockNewDoor(getNextBlockID(), EnumWoodType.BIRCH);
    public static final Block jungleDoor=new BlockNewDoor(getNextBlockID(),EnumWoodType.JUNGLE);
    public static final Block spruceDoor=new BlockNewDoor(getNextBlockID(), EnumWoodType.SPRUCE);

    public static final Block birchTrapDoor=(new BlockModTrapDoor(getNextBlockID(), EnumWoodType.BIRCH));
    public static final Block jungleTrapDoor=(new BlockModTrapDoor(getNextBlockID(), EnumWoodType.JUNGLE));
    public static final Block spruceTrapDoor=(new BlockModTrapDoor(getNextBlockID(), EnumWoodType.SPRUCE));

    public static final Block seaLantern =new BlockSeaLantern(getNextBlockID());
    public static final PrismarineBlocks prismarineBlock=new PrismarineBlocks(getNextBlockID());

    public static final Block endStoneBrick = new BlockStone(getNextBlockID()).setHardness(0.8f);

    public static final Block endStoneBrickWall = new BlockWallExtend(getNextBlockID(), endStoneBrick);
    public static final Block stoneBrickWall = new BlockWallExtend(getNextBlockID(), Block.stoneBrick);
    public static final Block BrickWall = new BlockWallExtend(getNextBlockID(), Block.brick);

    public static final Block barrel=new BlockBarrel(getNextBlockID(),Material.wood);

    protected Blocks(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);

    }

    static {
        Item.itemsList[bigGrass.blockID] = (new ItemColored(bigGrass)).setBlockNames(new String[]{"Grass","Grass","Fern","Fern"});
    }

    //注册方块物品事件
    public static void registerItemBlocks(ItemRegistryEvent registryEvent) {
        new ItemMultiTextureTile(prismarineBlock,prismarineBlock.getNames());
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "barrel",barrel);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "sea_lantern",seaLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "grindstone",grindStone);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "chain",chain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact,"ancientChain", ancientChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "adamantiumChain", adamantiumChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "silverChain", silverChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "copperChain", copperChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "mithrilChain", mithrilChain);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "campfire",campfire);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "soul_campfire",soulCampfire);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "normalCampfire",normalCampfire);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "soul_torch", soulTorch);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "dirtPath",dirtPath);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_birch_log", strippedBirch);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_jungle_log", strippedJungle);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_oak_log", strippedOak);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_spruce_log", strippedSpruce);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_birch_log", strippedBirchWood);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_jungle_log", strippedJungleWood);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_oak_log", strippedOakWood);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stripped_spruce_log", strippedSpruceWood);

        strippedBirchWood.setUnlocalizedName("stripped_birch_wood");
        strippedJungleWood.setUnlocalizedName("stripped_jungle_wood");
        strippedOakWood.setUnlocalizedName("stripped_oak_wood");
        strippedSpruceWood.setUnlocalizedName("stripped_spruce_wood");

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "lantern", lantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "soul_lantern", soulLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "AncientLantern", ancientLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "AdamantiumLantern", adamantiumLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "CopperLantern", copperLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "SilverLantern", silverLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "MithrilLantern", mithrilLantern);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "door/birch_door",birchDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "door/jungle_door",jungleDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "door/spruce_door",spruceDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "birch_trapdoor",birchTrapDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "jungle_trapdoor",jungleTrapDoor);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "spruce_trapdoor",spruceTrapDoor);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "end_stone_bricks",endStoneBrick);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "stonebrick",stoneBrickWall);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "brick",BrickWall);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "end_stone_bricks",endStoneBrickWall);

        stoneBrickWall.setUnlocalizedName("stonebrickWall");
        BrickWall.setUnlocalizedName("brickWall");
        endStoneBrickWall.setUnlocalizedName("endStoneBrickWall");
    }

    //注册方块的合成事件
    public static void registerRecipes(RecipeRegistryEvent register) {
        register.registerShapedRecipe(new ItemStack(seaLantern,1), true, new Object[] { "ABA", "BBB", "ABA", Character.valueOf('A'), Item.getItem(Items.prismarineShard.itemID),Character.valueOf('B'), Item.getItem(Items.prismarineCrystals.itemID) });

        for(int i=0;i<4;i++) {
            register.registerShapedRecipe(new ItemStack(barrel, 1), true, new Object[]{"ABA", "A A", "ABA", Character.valueOf('A'), new ItemStack((Block.planks), 1, i), Character.valueOf('B'), new ItemStack((Block.woodSingleSlab), 1, i)});
            register.registerShapedRecipe(new ItemStack(campfire, 1), true, new Object[]{" A ", "ABA", "CCC", Character.valueOf('A'), Item.getItem(Item.stick.itemID), Character.valueOf('B'), Item.getItem(Item.coal.itemID), Character.valueOf('C'), new ItemStack(Block.wood, 1, i)});
            register.registerShapedRecipe(new ItemStack(campfire, 1), true, new Object[]{" A ", "ABA", "CCC", Character.valueOf('A'), Item.getItem(Item.stick.itemID), Character.valueOf('B'), new ItemStack(Item.coal, 1, 1), Character.valueOf('C'), new ItemStack(Block.wood, 1, i)});
            register.registerShapedRecipe(new ItemStack(soulCampfire,1), true, new Object[] { " A ", "ABA", "CCC", Character.valueOf('A'), Item.getItem(Item.stick.itemID),Character.valueOf('B'), Item.getItem(Block.slowSand) , Character.valueOf('C'),new ItemStack(Block.wood, 1, i)});

            register.registerShapedRecipe(new ItemStack(grindStone, 1), true, new Object[]{"ABA", "C C", "   ", Character.valueOf('A'), Item.getItem(Item.stick.itemID), Character.valueOf('B'), Item.getItem(Block.stoneSingleSlab), Character.valueOf('C'), new ItemStack(Block.planks, 1, i)});
            register.registerShapedRecipe(new ItemStack(grindStone, 1), true, new Object[]{"   ", "ABA", "C C", Character.valueOf('A'), Item.getItem(Item.stick.itemID), Character.valueOf('B'), Item.getItem(Block.stoneSingleSlab), Character.valueOf('C'), new ItemStack(Block.planks, 1, i)});
        }

        register.registerShapedRecipe(new ItemStack(chain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotIron.itemID) });
        register.registerShapedRecipe(new ItemStack(chain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotIron.itemID) });
        register.registerShapedRecipe(new ItemStack(chain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotIron.itemID) });
        register.registerShapedRecipe(new ItemStack(adamantiumChain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.adamantiumNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotAdamantium.itemID) });
        register.registerShapedRecipe(new ItemStack(adamantiumChain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.adamantiumNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotAdamantium.itemID) });
        register.registerShapedRecipe(new ItemStack(adamantiumChain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.adamantiumNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotAdamantium.itemID) });
        register.registerShapedRecipe(new ItemStack(ancientChain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.ancientMetalNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotAncientMetal.itemID) });
        register.registerShapedRecipe(new ItemStack(ancientChain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.ancientMetalNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotAncientMetal.itemID) });
        register.registerShapedRecipe(new ItemStack(ancientChain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.ancientMetalNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotAncientMetal.itemID) });
        register.registerShapedRecipe(new ItemStack(silverChain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.silverNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotSilver.itemID) });
        register.registerShapedRecipe(new ItemStack(silverChain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.silverNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotSilver.itemID) });
        register.registerShapedRecipe(new ItemStack(silverChain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.silverNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotSilver.itemID) });
        register.registerShapedRecipe(new ItemStack(copperChain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.copperNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotCopper.itemID) });
        register.registerShapedRecipe(new ItemStack(copperChain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.copperNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotCopper.itemID) });
        register.registerShapedRecipe(new ItemStack(copperChain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.copperNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotCopper.itemID) });
        register.registerShapedRecipe(new ItemStack(mithrilChain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.mithrilNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotMithril.itemID) });
        register.registerShapedRecipe(new ItemStack(mithrilChain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.mithrilNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotMithril.itemID) });
        register.registerShapedRecipe(new ItemStack(mithrilChain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.mithrilNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotMithril.itemID) });

        register.registerShapedRecipe(new ItemStack(soulTorch,4), true, new Object[] { "A  ", "B  ", "C  ", Character.valueOf('A'), Item.getItem(Item.coal.itemID),Character.valueOf('B'), Item.getItem(Item.stick.itemID) , Character.valueOf('C'),Item.getItem(Block.slowSand)});
        register.registerShapedRecipe(new ItemStack(soulTorch,4), true, new Object[] { " A ", " B ", " C ", Character.valueOf('A'), Item.getItem(Item.coal.itemID),Character.valueOf('B'), Item.getItem(Item.stick.itemID) , Character.valueOf('C'),Item.getItem(Block.slowSand)});
        register.registerShapedRecipe(new ItemStack(soulTorch,4), true, new Object[] { "  A", "  B", "  C", Character.valueOf('A'), Item.getItem(Item.coal.itemID),Character.valueOf('B'), Item.getItem(Item.stick.itemID) , Character.valueOf('C'),Item.getItem(Block.slowSand)});
        register.registerShapedRecipe(new ItemStack(soulTorch,4), true, new Object[] { "A  ", "B  ", "C  ", Character.valueOf('A'), new ItemStack(Item.coal,1,1),Character.valueOf('B'), Item.getItem(Item.stick.itemID) , Character.valueOf('C'),Item.getItem(Block.slowSand)});
        register.registerShapedRecipe(new ItemStack(soulTorch,4), true, new Object[] { " A ", " B ", " C ", Character.valueOf('A'), new ItemStack(Item.coal,1,1),Character.valueOf('B'), Item.getItem(Item.stick.itemID) , Character.valueOf('C'),Item.getItem(Block.slowSand)});
        register.registerShapedRecipe(new ItemStack(soulTorch,4), true, new Object[] { "  A", "  B", "  C", Character.valueOf('A'), new ItemStack(Item.coal,1,1),Character.valueOf('B'), Item.getItem(Item.stick.itemID) , Character.valueOf('C'),Item.getItem(Block.slowSand)});

        //0为 oak   1 spruce   2birch  3jungle
        register.registerShapelessRecipe(new ItemStack(Block.planks,4,0), false, new Object[] { new ItemStack(Blocks.strippedOak) });
        register.registerShapelessRecipe(new ItemStack(Block.planks,4,1), false, new Object[] { new ItemStack(Blocks.strippedSpruce) });
        register.registerShapelessRecipe(new ItemStack(Block.planks,4,2), false, new Object[] { new ItemStack(Blocks.strippedBirch) });
        register.registerShapelessRecipe(new ItemStack(Block.planks,4,3), false, new Object[] { new ItemStack(Blocks.strippedJungle) });

        register.registerShapedRecipe(new ItemStack(strippedBirchWood,3), true, new Object[] { "AA ", "AA ", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedBirch) });
        register.registerShapedRecipe(new ItemStack(strippedBirchWood,3), true, new Object[] { " AA", " AA", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedBirch) });
        register.registerShapedRecipe(new ItemStack(strippedBirchWood,3), true, new Object[] { "   ", "AA ", "AA ", Character.valueOf('A'), Item.getItem(Blocks.strippedBirch) });
        register.registerShapedRecipe(new ItemStack(strippedBirchWood,3), true, new Object[] { "   ", " AA", " AA", Character.valueOf('A'), Item.getItem(Blocks.strippedBirch) });

        register.registerShapedRecipe(new ItemStack(strippedJungleWood,3), true, new Object[] { "AA ", "AA ", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedJungle) });
        register.registerShapedRecipe(new ItemStack(strippedJungleWood,3), true, new Object[] { " AA", " AA", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedJungle) });
        register.registerShapedRecipe(new ItemStack(strippedJungleWood,3), true, new Object[] { "   ", "AA ", "AA ", Character.valueOf('A'), Item.getItem(Blocks.strippedJungle) });
        register.registerShapedRecipe(new ItemStack(strippedJungleWood,3), true, new Object[] { "   ", " AA", " AA", Character.valueOf('A'), Item.getItem(Blocks.strippedJungle) });

        register.registerShapedRecipe(new ItemStack(strippedOakWood,3), true, new Object[] { "AA ", "AA ", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedOak) });
        register.registerShapedRecipe(new ItemStack(strippedOakWood,3), true, new Object[] { " AA", " AA", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedOak) });
        register.registerShapedRecipe(new ItemStack(strippedOakWood,3), true, new Object[] { "   ", "AA ", "AA ", Character.valueOf('A'), Item.getItem(Blocks.strippedOak) });
        register.registerShapedRecipe(new ItemStack(strippedOakWood,3), true, new Object[] { "   ", " AA", " AA", Character.valueOf('A'), Item.getItem(Blocks.strippedOak) });

        register.registerShapedRecipe(new ItemStack(strippedSpruceWood,3), true, new Object[] { "AA ", "AA ", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedSpruce) });
        register.registerShapedRecipe(new ItemStack(strippedSpruceWood,3), true, new Object[] { " AA", " AA", "   ", Character.valueOf('A'), Item.getItem(Blocks.strippedSpruce) });
        register.registerShapedRecipe(new ItemStack(strippedSpruceWood,3), true, new Object[] { "   ", "AA ", "AA ", Character.valueOf('A'), Item.getItem(Blocks.strippedSpruce) });
        register.registerShapedRecipe(new ItemStack(strippedSpruceWood,3), true, new Object[] { "   ", " AA", " AA", Character.valueOf('A'), Item.getItem(Blocks.strippedSpruce) });

        register.registerShapedRecipe(new ItemStack(lantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Block.torchWood) });
        register.registerShapedRecipe(new ItemStack(soulLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Blocks.soulTorch) });
        register.registerShapedRecipe(new ItemStack(adamantiumLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.adamantiumNugget.itemID),Character.valueOf('B'), Item.getItem(Blocks.torchWood) });
        register.registerShapedRecipe(new ItemStack(ancientLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.ancientMetalNugget.itemID),Character.valueOf('B'), Item.getItem(Blocks.torchWood) });
        register.registerShapedRecipe(new ItemStack(copperLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.copperNugget.itemID),Character.valueOf('B'), Item.getItem(Blocks.torchWood) });
        register.registerShapedRecipe(new ItemStack(silverLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.silverNugget.itemID),Character.valueOf('B'), Item.getItem(Blocks.torchWood) });
        register.registerShapedRecipe(new ItemStack(mithrilLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.mithrilNugget.itemID),Character.valueOf('B'), Item.getItem(Blocks.torchWood) });

        register.registerShapedRecipe(new ItemStack(birchTrapDoor,2), true, new Object[] { "AAA", "AAA", "   ", Character.valueOf('A'),new ItemStack(Block.planks,1,2) });
        register.registerShapedRecipe(new ItemStack(jungleTrapDoor,2), true, new Object[] { "AAA", "AAA", "   ", Character.valueOf('A'),new ItemStack(Block.planks,1,3) });
        register.registerShapedRecipe(new ItemStack(spruceTrapDoor,2), true, new Object[] { "AAA", "AAA", "   ", Character.valueOf('A'),new ItemStack(Block.planks,1,1) });
        register.registerShapedRecipe(new ItemStack(birchTrapDoor,2), true, new Object[] { "   ", "AAA", "AAA", Character.valueOf('A'),new ItemStack(Block.planks,1,2) });
        register.registerShapedRecipe(new ItemStack(jungleTrapDoor,2), true, new Object[] { "   ", "AAA", "AAA", Character.valueOf('A'),new ItemStack(Block.planks,1,3) });
        register.registerShapedRecipe(new ItemStack(spruceTrapDoor,2), true, new Object[] { "   ", "AAA", "AAA", Character.valueOf('A'),new ItemStack(Block.planks,1,1) });

        register.registerShapedRecipe(new ItemStack(stoneBrickWall,8), true, new Object[] { "   ", "AAA", "AAA", Character.valueOf('A'), Item.getItem(Block.stoneBrick) });
        register.registerShapedRecipe(new ItemStack(stoneBrickWall,8), true, new Object[] { "AAA", "AAA", "   ", Character.valueOf('A'), Item.getItem(Block.stoneBrick) });
        register.registerShapedRecipe(new ItemStack(BrickWall,8), true, new Object[] { "   ", "AAA", "AAA", Character.valueOf('A'), Item.getItem(Block.brick) });
        register.registerShapedRecipe(new ItemStack(BrickWall,8), true, new Object[] { "AAA", "AAA", "   ", Character.valueOf('A'), Item.getItem(Block.brick) });
        register.registerShapedRecipe(new ItemStack(endStoneBrickWall,8), true, new Object[] { "   ", "AAA", "AAA", Character.valueOf('A'), Item.getItem(endStoneBrick) });
        register.registerShapedRecipe(new ItemStack(endStoneBrickWall,8), true, new Object[] { "AAA", "AAA", "   ", Character.valueOf('A'), Item.getItem(endStoneBrick) });

        register.registerShapedRecipe(new ItemStack(endStoneBrick,4), true, new Object[] { "AA ", "AA ", "   ", Character.valueOf('A'), Item.getItem(Block.whiteStone) });
        register.registerShapedRecipe(new ItemStack(endStoneBrick,4), true, new Object[] { " AA", " AA", "   ", Character.valueOf('A'), Item.getItem(Block.whiteStone) });
        register.registerShapedRecipe(new ItemStack(endStoneBrick,4), true, new Object[] { "   ", "AA ", "AA ", Character.valueOf('A'), Item.getItem(Block.whiteStone) });
        register.registerShapedRecipe(new ItemStack(endStoneBrick,4), true, new Object[] { "   ", " AA", " AA", Character.valueOf('A'), Item.getItem(Block.whiteStone) });

    }

    public static void furnaceRecipe() {
        FurnaceRecipes.smelting().addSmelting(sponge.blockID, new ItemStack(Item.itemsList[sponge.blockID],1,0));
    }

    public static int getNextBlockID() {return IdUtil.getNextBlockID();}
}
