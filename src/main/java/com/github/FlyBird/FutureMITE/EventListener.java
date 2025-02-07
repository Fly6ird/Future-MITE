package com.github.FlyBird.FutureMITE;

import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.blocks.TreeHelper;
import com.github.FlyBird.FutureMITE.entities.*;
import com.github.FlyBird.FutureMITE.items.Items;
import com.github.FlyBird.FutureMITE.render.RenderCampfireTileEntity;
import com.github.FlyBird.FutureMITE.render.RenderGrindstoneTileEntity;
import com.github.FlyBird.FutureMITE.render.entity.*;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityBarrel;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import com.github.FlyBird.FutureMITE.tileentities.TileEntityGrindstone;
import com.google.common.eventbus.Subscribe;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.fml.reload.event.*;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class EventListener {

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        //物品被注册事件
        Items.registerItems(event);
        Blocks.registerItemBlocks(event);
        Blocks.furnaceRecipe();
    }

    @Subscribe
    public void onRecipeRegister(RecipeRegistryEvent event) {
        //合成方式被注册事件
        Items.registerRecipes(event);
        Blocks.registerRecipes(event);
    }

    //玩家登录事件
    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {

    }

    public static final ResourceLocation barrelOpen = new ResourceLocation("futuremite", "block.barrel.open");
    public static final ResourceLocation barrelClose = new ResourceLocation("futuremite", "block.barrel.close");

    public static final ResourceLocation chainBreak = new ResourceLocation("futuremite", "block.chain.break");
    public static final ResourceLocation chainStep = new ResourceLocation("futuremite", "block.chain.step");

    public static final ResourceLocation newWoodDig = new ResourceLocation("futuremite", "dig.newwood");
    public static final ResourceLocation newWoodStep = new ResourceLocation("futuremite", "step.newwood");

    public static final ResourceLocation lanternBreak = new ResourceLocation("futuremite", "block.lantern.break");
    public static final ResourceLocation lanternPlace = new ResourceLocation("futuremite", "block.lantern.place");

    public static final ResourceLocation sweetBerryBushBreak = new ResourceLocation("futuremite", "block.sweet_berry_bush.break");
    public static final ResourceLocation sweetBerryBushPlace = new ResourceLocation("futuremite", "block.sweet_berry_bush.place");
    public static final ResourceLocation sweetBerryBushPick = new ResourceLocation("futuremite", "item.sweet_berries.pick_from_bush");

    public static final ResourceLocation rabbitBunnyMurder = new ResourceLocation("futuremite", "mob.rabbit.bunnymurder");
    public static final ResourceLocation rabbitHop = new ResourceLocation("futuremite", "mob.rabbit.hop");
    public static final ResourceLocation rabbitHurt = new ResourceLocation("futuremite", "mob.rabbit.hurt");
    public static final ResourceLocation rabbitIdle = new ResourceLocation("futuremite", "mob.rabbit.Idle");

    public static final ResourceLocation campfireCrackle = new ResourceLocation("futuremite", "block.campfire.crackle");
    public static final ResourceLocation boatPaddleLand = new ResourceLocation("futuremite", "entity.boat.paddle_land");
    public static final ResourceLocation boatPaddleWater = new ResourceLocation("futuremite", "entity.boat.paddle_water");

    public static final ResourceLocation armorStandBreak = new ResourceLocation("futuremite", "entity.armorstand.break");
    public static final ResourceLocation armorStandHit = new ResourceLocation("futuremite", "entity.armorstand.hit");

    @Subscribe
    public void onSoundsRegister(SoundsRegisterEvent event) {
        event.registerSound(barrelOpen, 2);
        event.registerSound(barrelClose);

        event.registerSound(chainBreak, 4);
        event.registerSound(chainStep, 6);

        event.registerSound(newWoodDig, 4);
        event.registerSound(newWoodStep, 6);

        event.registerSound(lanternBreak, 6);
        event.registerSound(lanternPlace, 6);

        event.registerSound(sweetBerryBushBreak, 4);
        event.registerSound(sweetBerryBushPlace, 6);
        event.registerSound(sweetBerryBushPick, 2);

        event.registerSound(rabbitBunnyMurder);
        event.registerSound(rabbitHop, 4);
        event.registerSound(rabbitHurt, 4);
        event.registerSound(rabbitIdle, 4);

        event.registerSound(campfireCrackle, 6);

        event.registerSound(boatPaddleLand, 6);
        event.registerSound(boatPaddleWater, 8);

        event.registerSound(armorStandBreak, 4);
        event.registerSound(armorStandHit, 4);
    }

    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {

    }

    @Subscribe
    public void onEntityRegister(EntityRegisterEvent event) {
        event.register(EntityRabbit.class, FutureMITEStart.MOD_ID, "Rabbit", getNextEntityID(), 10051392, 7555121);
        event.register(EntityEndermite.class, FutureMITEStart.MOD_ID, "Endermite", getNextEntityID(), 1447446, 7237230);
        event.register(EntityArmorStand.class, FutureMITEStart.MOD_ID, "ArmorStand", getNextEntityID());

        event.register(EntityNewBoat.class, FutureMITEStart.MOD_ID, "NewBoat", getNextEntityID());
        event.register(EntityNewBoatSeat.class, FutureMITEStart.MOD_ID, "NewBoatSeat", getNextEntityID());
        event.register(EntityNewBoatWithChest.class, FutureMITEStart.MOD_ID, "NewBoatWithChest", getNextEntityID());
    }

    @Subscribe
    public void onEntityRendererRegistry(EntityRendererRegistryEvent event) {
        event.register(EntityRabbit.class, new RabbitRenderer());
        event.register(EntityArmorStand.class, new ArmorStandRenderer());
        event.register(EntityEndermite.class, new EndermiteRenderer());

        event.register(EntityNewBoat.class, new NewBoatRenderer());
        //event.register(EntityNewBoatSeat.class,new NewBoatRenderer());
        event.register(EntityNewBoatWithChest.class, new ChestBoatRenderer());
    }

    @Subscribe
    public void onTileEntityRendererRegister(TileEntityRendererRegisterEvent event) {
        event.register(TileEntityGrindstone.class, new RenderGrindstoneTileEntity());
        event.register(TileEntityCampfire.class, new RenderCampfireTileEntity());
    }

    @Subscribe
    public void onTileEntityRegister(TileEntityRegisterEvent event) {
        //注册方块实体的地方
        event.register(TileEntityBarrel.class, "Barrel");
        event.register(TileEntityGrindstone.class, "Grindstone");
        event.register(TileEntityCampfire.class, "Campfire");
    }

    public static int getNextEntityID() {
        return IdUtil.getNextEntityID();
    }
}
