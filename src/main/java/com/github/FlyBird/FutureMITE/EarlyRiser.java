package com.github.FlyBird.FutureMITE;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.xiaoyu233.fml.util.EnumExtends;

public class EarlyRiser implements PreLaunchEntrypoint {

    public void onPreLaunch() {
        EnumExtends.PARTICLE.addEnum("soul_fire_flame");
        EnumExtends.PARTICLE.addEnum("big_smoke");
    }
}
