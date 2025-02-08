package com.github.FlyBird.FutureMITE.events.listener;

import com.github.FlyBird.FutureMITE.compat.CampfireHUDHandlerMITE;
import moddedmite.rustedironcore.api.event.listener.IInitializationListener;
import net.minecraft.Minecraft;
import net.xiaoyu233.fml.FishModLoader;

public class InitializationListener implements IInitializationListener {
    @Override
    public void onClientStarted(Minecraft client) {
//        WailaHandler.register();
        if (FishModLoader.hasMod("waila")) {
            CampfireHUDHandlerMITE.register();
        }
    }
}
