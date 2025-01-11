package com.github.FlyBird.FutureMITE;

import com.github.FlyBird.FutureMITE.events.FutureMITEEventRIC;
import com.github.FlyBird.FutureMITE.network.FutureMITENetWork;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class FutureMITEStart implements ModInitializer {
    public static final String NameSpaceCompact = "FutureMITE";
    public static final String MOD_ID="FutureMITE";
    @Override
    public void onInitialize() {   //相当于main函数，万物起源
        MITEEvents.MITE_EVENT_BUS.register(new EventListen());//注册一个事件监听类
        FutureMITENetWork.init();
        FutureMITEEventRIC.register();
    }
}