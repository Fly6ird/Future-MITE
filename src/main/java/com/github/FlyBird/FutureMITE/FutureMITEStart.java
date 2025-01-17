package com.github.FlyBird.FutureMITE;

import com.github.FlyBird.FutureMITE.common.GameRegistry;
import com.github.FlyBird.FutureMITE.events.FutureMITEEventRIC;
import com.github.FlyBird.FutureMITE.network.FutureMITENetWork;
import com.github.FlyBird.FutureMITE.world.FutureMITEWorldGenerator;
import com.github.FlyBird.FutureMITE.world.structure.OceanMonument;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class FutureMITEStart implements ModInitializer {
    public static final String NameSpaceCompact = "FutureMITE";
    public static final String MOD_ID="FutureMITE";
    @Override
    public void onInitialize() {   //相当于main函数，万物起源
        FutureMITEEventRIC.register();
        FutureMITENetWork.init();

        //GameRegistry.registerWorldGenerator(new FutureMITEWorldGenerator(), 0);


        MITEEvents.MITE_EVENT_BUS.register(new EventListener());//注册一个事件监听类
    }
}