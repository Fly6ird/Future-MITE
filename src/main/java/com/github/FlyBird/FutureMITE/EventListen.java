package com.github.FlyBird.FutureMITE;

import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoat;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoatSeat;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoatWithChest;
import com.github.FlyBird.FutureMITE.items.Items;
import com.github.FlyBird.FutureMITE.render.entity.ChestBoatRenderer;
import com.github.FlyBird.FutureMITE.render.entity.NewBoatRenderer;
import com.google.common.eventbus.Subscribe;
import net.minecraft.ChatMessageComponent;
import net.minecraft.EntityPlayer;
import net.xiaoyu233.fml.reload.event.*;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class EventListen {

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        //物品被注册事件
        Items.registerItems(event);
        Blocks.registerItemBlocks(event);
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

    @Subscribe
    public void onSoundsRegister(SoundsRegisterEvent event) {
        event.register("sound/block/barrel/open1.ogg");
        event.register("sound/block/barrel/open2.ogg");
        event.register("sound/block/barrel/close.ogg");

        event.register("sound/block/chain/break1.ogg");
        event.register("sound/block/chain/break2.ogg");
        event.register("sound/block/chain/break3.ogg");
        event.register("sound/block/chain/break4.ogg");
        event.register("sound/block/chain/step1.ogg");
        event.register("sound/block/chain/step2.ogg");
        event.register("sound/block/chain/step3.ogg");
        event.register("sound/block/chain/step4.ogg");
        event.register("sound/block/chain/step5.ogg");
        event.register("sound/block/chain/step6.ogg");

        event.register("sound/dig/newwood1.ogg");
        event.register("sound/dig/newwood2.ogg");
        event.register("sound/dig/newwood3.ogg");
        event.register("sound/dig/newwood4.ogg");
        event.register("sound/step/newwood1.ogg");
        event.register("sound/step/newwood2.ogg");
        event.register("sound/step/newwood3.ogg");
        event.register("sound/step/newwood4.ogg");
        event.register("sound/step/newwood5.ogg");
        event.register("sound/step/newwood6.ogg");

        event.register("sound/block/lantern/break1.ogg");
        event.register("sound/block/lantern/break2.ogg");
        event.register("sound/block/lantern/break3.ogg");
        event.register("sound/block/lantern/break4.ogg");
        event.register("sound/block/lantern/break5.ogg");
        event.register("sound/block/lantern/break6.ogg");
        event.register("sound/block/lantern/place1.ogg");
        event.register("sound/block/lantern/place2.ogg");
        event.register("sound/block/lantern/place3.ogg");
        event.register("sound/block/lantern/place4.ogg");
        event.register("sound/block/lantern/place5.ogg");
        event.register("sound/block/lantern/place6.ogg");

        event.register("sound/block/sweet_berry_bush/break1.ogg");
        event.register("sound/block/sweet_berry_bush/break2.ogg");
        event.register("sound/block/sweet_berry_bush/break3.ogg");
        event.register("sound/block/sweet_berry_bush/break4.ogg");
        event.register("sound/block/sweet_berry_bush/place1.ogg");
        event.register("sound/block/sweet_berry_bush/place2.ogg");
        event.register("sound/block/sweet_berry_bush/place3.ogg");
        event.register("sound/block/sweet_berry_bush/place4.ogg");
        event.register("sound/block/sweet_berry_bush/place5.ogg");
        event.register("sound/block/sweet_berry_bush/place6.ogg");

        event.register("sound/item/sweet_berries/pick_from_bush1.ogg");
        event.register("sound/item/sweet_berries/pick_from_bush2.ogg");

        event.register("sound/mob/rabbit/bunnymurder.ogg");
        event.register("sound/mob/rabbit/hop1.ogg");
        event.register("sound/mob/rabbit/hop2.ogg");
        event.register("sound/mob/rabbit/hop3.ogg");
        event.register("sound/mob/rabbit/hop4.ogg");
        event.register("sound/mob/rabbit/hurt1.ogg");
        event.register("sound/mob/rabbit/hurt2.ogg");
        event.register("sound/mob/rabbit/hurt3.ogg");
        event.register("sound/mob/rabbit/hurt4.ogg");
        event.register("sound/mob/rabbit/idle4.ogg");
        event.register("sound/mob/rabbit/idle2.ogg");
        event.register("sound/mob/rabbit/idle3.ogg");
        event.register("sound/mob/rabbit/idle4.ogg");

        event.register("sound/block/campfire/crackle1.ogg");
        event.register("sound/block/campfire/crackle2.ogg");
        event.register("sound/block/campfire/crackle3.ogg");
        event.register("sound/block/campfire/crackle4.ogg");
        event.register("sound/block/campfire/crackle5.ogg");
        event.register("sound/block/campfire/crackle6.ogg");

        event.register("sound/entity/boat/paddle_land1.ogg");
        event.register("sound/entity/boat/paddle_land2.ogg");
        event.register("sound/entity/boat/paddle_land3.ogg");
        event.register("sound/entity/boat/paddle_land4.ogg");
        event.register("sound/entity/boat/paddle_land5.ogg");
        event.register("sound/entity/boat/paddle_land6.ogg");

        event.register("sound/entity/boat/paddle_water1.ogg");
        event.register("sound/entity/boat/paddle_water2.ogg");
        event.register("sound/entity/boat/paddle_water3.ogg");
        event.register("sound/entity/boat/paddle_water4.ogg");
        event.register("sound/entity/boat/paddle_water5.ogg");
        event.register("sound/entity/boat/paddle_water6.ogg");
        event.register("sound/entity/boat/paddle_water7.ogg");
        event.register("sound/entity/boat/paddle_water8.ogg");

        event.register("sound/entity/armorstand/break1.ogg");
        event.register("sound/entity/armorstand/break2.ogg");
        event.register("sound/entity/armorstand/break3.ogg");
        event.register("sound/entity/armorstand/break4.ogg");

        event.register("sound/entity/armorstand/hit1.ogg");
        event.register("sound/entity/armorstand/hit2.ogg");
        event.register("sound/entity/armorstand/hit3.ogg");
        event.register("sound/entity/armorstand/hit4.ogg");
    }

    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {
        String par2Str = event.getCommand();
        EntityPlayer player = event.getPlayer();
        if (par2Str.startsWith("hello")) {    //当玩家输入 /Hello
            EntityNewBoat test=new EntityNewBoat(event.getWorld());
            test.setPosition(player.posX,player.posY,player.posZ);
            event.getWorld().spawnEntityInWorld(test);

        }
    }

    @Subscribe
    public void onEntityRegister(EntityRegisterEvent event) {
/*        event.register(EntityRabbit.class, BetterMiteStart.MOD_ID, "EntityRabbit", getNextEntityID(),10051392,7555121);
        event.register(EntityEndermite.class, BetterMiteStart.MOD_ID, "EntityEndermite", getNextEntityID(),1447446,7237230);
        event.register(EntityArmourStand.class, BetterMiteStart.MOD_ID,"EntityArmourStand", getNextEntityID());*/

        event.register(EntityNewBoat.class, FutureMITEStart.MOD_ID, "NewBoat", getNextEntityID());
        event.register(EntityNewBoatSeat.class, FutureMITEStart.MOD_ID, "NewBoatSeat", getNextEntityID());
        event.register(EntityNewBoatWithChest.class, FutureMITEStart.MOD_ID, "NewBoatWithChest", getNextEntityID());
    }

    @Subscribe
    public void onEntityRendererRegistry(EntityRendererRegistryEvent event)
    {
   /*     event.register(EntityRabbit.class,new RabbitRenderer());
        event.register(EntityArmourStand.class,new ArmourStandRenderer());
        event.register(EntityEndermite.class,new EndermiteRenderer());*/

        event.register(EntityNewBoat.class,new NewBoatRenderer());
        //event.register(EntityNewBoatSeat.class,new NewBoatRenderer());
        event.register(EntityNewBoatWithChest.class,new ChestBoatRenderer());
    }

    public static int getNextEntityID() {
        return IdUtil.getNextEntityID();
    }
}
