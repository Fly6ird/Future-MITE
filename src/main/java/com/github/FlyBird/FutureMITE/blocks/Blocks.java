package com.github.FlyBird.FutureMITE.blocks;

import com.github.FlyBird.FutureMITE.FutureMITEStart;
import com.github.FlyBird.FutureMITE.items.ItemBigGrass;
import com.github.FlyBird.FutureMITE.items.ItemMultiTextureTileFuelBlock;
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

    public static final Block ironChain=new BlockChain(getNextBlockID(),Material.iron);
    public static final Block silverChain =new BlockChain(getNextBlockID(),Material.silver);
    public static final Block copperChain =new BlockChain(getNextBlockID(),Material.copper);
    public static final Block adamantiumChain =new BlockChain(getNextBlockID(),Material.adamantium);
    public static final Block ancientChain =new BlockChain(getNextBlockID(),Material.ancient_metal);
    public static final Block mithrilChain =new BlockChain(getNextBlockID(),Material.mithril);

    public static final Block campfire=new BlockCampfire(getNextBlockID(),1.0f).setLightValue(0.6f);
    public static final Block soulCampfire=new BlockCampfire(getNextBlockID(),2.0f).setLightValue(0.6666f);
    public static final Block normalCampfire=new BlockExtinguishedCampfire(getNextBlockID());

    public static final Block dirtPath = new BlockDirtPath(getNextBlockID());

    public static final Block soulTorch =new BlockSoulTorch(getNextBlockID());

    public static final Block ironLantern =new BlockLantern(getNextBlockID());
    public static final Block soulLantern =new BlockLantern(getNextBlockID());
    public static final Block ancientLantern =new BlockLantern(getNextBlockID());
    public static final Block adamantiumLantern =new BlockLantern(getNextBlockID());
    public static final Block silverLantern =new BlockLantern(getNextBlockID());
    public static final Block copperLantern =new BlockLantern(getNextBlockID());
    public static final Block mithrilLantern =new BlockLantern(getNextBlockID());

    public static final Block seaLantern =new BlockSeaLantern(getNextBlockID());
    public static final PrismarineBlocks prismarineBlock=new PrismarineBlocks(getNextBlockID());

    public static final Block endStoneBrick = new BlockStone(getNextBlockID()).setHardness(0.8f);

    public static final Block endStoneBrickWall = new BlockWallExtend(getNextBlockID(), endStoneBrick);
    public static final Block stoneBrickWall = new BlockWallExtend(getNextBlockID(), Block.stoneBrick);
    public static final Block BrickWall = new BlockWallExtend(getNextBlockID(), Block.brick);

    public static final Block barrel=new BlockBarrel(getNextBlockID(),Material.wood);

    public static final Block birchDoor=new BlockModDoor(getNextBlockID());
    public static final Block jungleDoor=new BlockModDoor(getNextBlockID());
    public static final Block spruceDoor=new BlockModDoor(getNextBlockID());
    public static final BlockWoodFenceExtend spruceFence = (new BlockWoodFenceExtend(getNextBlockID(), 1));
    public static final BlockWoodFenceExtend birchFence = (new BlockWoodFenceExtend(getNextBlockID(), 2));
    public static final BlockWoodFenceExtend jungleFence = (new BlockWoodFenceExtend(getNextBlockID(), 3));
    public static final BlockFenceGateExtend spruceFenceGate=new BlockFenceGateExtend(getNextBlockID(),1);
    public static final BlockFenceGateExtend birchFenceGate=new BlockFenceGateExtend(getNextBlockID(),2);
    public static final BlockFenceGateExtend jungleFenceGate=new BlockFenceGateExtend(getNextBlockID(),3);

    public static final BlockModLog birchLog =new BlockModLog(getNextBlockID(),"birch").setIsVanilla();
    public static final BlockModLog jungleLog =new BlockModLog(getNextBlockID(),"jungle").setIsVanilla();
    public static final BlockModLog oakLog =new BlockModLog(getNextBlockID(),"oak").setIsVanilla();
    public static final BlockModLog spruceLog =new BlockModLog(getNextBlockID(),"spruce").setIsVanilla();
    public static final TreeHelper acacia = new TreeHelper("acacia");
    public static final TreeHelper darkOak = new TreeHelper("dark_oak");

    public static final BlockWoodWorkBench oakWorkbench=(new BlockWoodWorkBench(getNextBlockID(),oakLog));
    public static final BlockWoodWorkBench birchWorkbench=(new BlockWoodWorkBench(getNextBlockID(),birchLog));
    public static final BlockWoodWorkBench jungleWorkbench=(new BlockWoodWorkBench(getNextBlockID(),jungleLog));
    public static final BlockWoodWorkBench spruceWorkbench=(new BlockWoodWorkBench(getNextBlockID(), spruceLog));


    public static final BlockModTrapDoor birchTrapDoor=(new BlockModTrapDoor(getNextBlockID(),"birch"));
    public static final BlockModTrapDoor jungleTrapDoor=(new BlockModTrapDoor(getNextBlockID(),"jungle"));
    public static final BlockModTrapDoor spruceTrapDoor=(new BlockModTrapDoor(getNextBlockID(),"spruce"));

    protected Blocks(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);

    }

    static {
        Item.itemsList[bigGrass.blockID] = (new ItemBigGrass(bigGrass));
        for(BlockModLog log : new BlockModLog[]{oakLog,birchLog,spruceLog,jungleLog}){
            Item.itemsList[log.blockID] = (new ItemMultiTextureTileFuelBlock(log, log.getNames(),1600));
        }

        for (BlockWoodWorkBench blockModLog: new BlockWoodWorkBench[]{oakWorkbench,birchWorkbench,jungleWorkbench,spruceWorkbench}) {
            String[] workbenchNameList = new String[8];
            for (int i = 0; i < workbenchNameList.length; i++)
                workbenchNameList[i] = ((i < 4) ? "flint" : "obsidian") + "." + blockModLog.Log.getName(i);
            Item.itemsList[blockModLog.blockID] = (new ItemMultiTextureTileFuelBlock(blockModLog, workbenchNameList, 1600));
        }
    }

    //注册方块物品事件
    public static void registerItemBlocks(ItemRegistryEvent registryEvent) {
        new ItemMultiTextureTile(prismarineBlock,prismarineBlock.getNames());
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "barrel",barrel);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "sea_lantern",seaLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "grindstone",grindStone);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "iron_chain", ironChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact,"ancient_chain", ancientChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "adamantium_chain", adamantiumChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "silver_chain", silverChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "copper_chain", copperChain);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "mithril_chain", mithrilChain);


        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "campfire",campfire);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "soul_campfire",soulCampfire);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "normalCampfire",normalCampfire);

        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "soul_torch", soulTorch);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "dirtPath",dirtPath);


        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "iron_lantern", ironLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "soul_lantern", soulLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "ancient_lantern", ancientLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "adamantium_lantern", adamantiumLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "copper_lantern", copperLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "silver_lantern", silverLantern);
        registryEvent.registerItemBlock(FutureMITEStart.NameSpaceCompact, "mithril_lantern", mithrilLantern);

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

        register.registerShapedRecipe(new ItemStack(ironChain,16), true, new Object[] { "A  ", "B  ", "A  ", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotIron.itemID) });
        register.registerShapedRecipe(new ItemStack(ironChain,16), true, new Object[] { " A ", " B ", " A ", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotIron.itemID) });
        register.registerShapedRecipe(new ItemStack(ironChain,16), true, new Object[] { "  A", "  B", "  A", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Item.ingotIron.itemID) });
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

        register.registerShapedRecipe(new ItemStack(ironLantern,1), true, new Object[] { "AAA", "ABA", "AAA", Character.valueOf('A'), Item.getItem(Item.ironNugget.itemID),Character.valueOf('B'), Item.getItem(Block.torchWood) });
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


        //WoodWorkBench
        birchWorkbench.registerRecipe(register);
        jungleWorkbench.registerRecipe(register);
        spruceWorkbench.registerRecipe(register);
        oakWorkbench.registerRecipe(register);

        //0为 oak   1 spruce   2birch  3jungle
        register.registerShapedRecipe(new ItemStack(oakLog, 3, 2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(wood, 1, 0)});
        register.registerShapedRecipe(new ItemStack(spruceLog, 3, 2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(wood, 1, 1)});
        register.registerShapedRecipe(new ItemStack(birchLog, 3, 2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(wood, 1, 2)});
        register.registerShapedRecipe(new ItemStack(jungleLog, 3, 2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(wood, 1, 3)});

        for (int i=0;i<=1;i++) {
            register.registerShapedRecipe(new ItemStack(birchLog, 3, i+2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(birchLog, 1, i)});
            register.registerShapedRecipe(new ItemStack(jungleLog, 3, i+2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(jungleLog, 1, i)});
            register.registerShapedRecipe(new ItemStack(oakLog, 3, i+2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(oakLog, 1, i)});
            register.registerShapedRecipe(new ItemStack(spruceLog, 3, i+2), true, new Object[]{"AA", "AA", Character.valueOf('A'), new ItemStack(spruceLog, 1, i)});
        }
        for (int i=0;i<4;i++) {
            register.registerShapedRecipe(new ItemStack(planks, 4, 0), true, "A",'A',new ItemStack(oakLog, 1,i));
            register.registerShapedRecipe(new ItemStack(planks, 4, 1), true, "A",'A',new ItemStack(spruceLog, 1,i));
            register.registerShapedRecipe(new ItemStack(planks, 4, 2), true, "A",'A',new ItemStack(birchLog, 1,i));
            register.registerShapedRecipe(new ItemStack(planks, 4, 3), true, "A",'A',new ItemStack(jungleLog, 1,i));
        }

        birchTrapDoor.registerRecipeWithVanilla(register,1);
        jungleTrapDoor.registerRecipeWithVanilla(register,2);
        spruceTrapDoor.registerRecipeWithVanilla(register,3);

        acacia.registerRecipes(register);
        darkOak.registerRecipes(register);

        for(BlockFenceGateExtend blockFenceGateExtend : new BlockFenceGateExtend[]{spruceFenceGate,birchFenceGate,jungleFenceGate}){
            blockFenceGateExtend.registerRecipe(register);
        }
        for(BlockWoodFenceExtend blockWoodFenceExtend : new BlockWoodFenceExtend[]{spruceFence,birchFence,jungleFence}){
            blockWoodFenceExtend.registerRecipe(register);
        }
        register.registerShapedRecipe(new ItemStack(Block.fence,3), true, new Object[] {"ABA","ABA", 'A', new ItemStack(Block.planks,1,0), 'B',Item.stick});
    }

    public static void furnaceRecipe() {
        FurnaceRecipes.smelting().addSmelting(sponge.blockID, new ItemStack(Item.itemsList[sponge.blockID],1,0));
    }

    public static int getNextBlockID() {return IdUtil.getNextBlockID();}
}
