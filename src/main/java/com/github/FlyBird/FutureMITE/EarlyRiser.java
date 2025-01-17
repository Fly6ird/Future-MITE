package com.github.FlyBird.FutureMITE;

import com.github.FlyBird.FutureMITE.common.GameRegistry;
import com.github.FlyBird.FutureMITE.world.FutureMITEWorldGenerator;
import com.github.FlyBird.FutureMITE.world.structure.OceanMonument;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.xiaoyu233.fml.util.EnumExtends;

public class EarlyRiser implements PreLaunchEntrypoint {

    public void onPreLaunch() {

        EnumExtends.PARTICLE.addEnum("soul_fire_flame");
        EnumExtends.PARTICLE.addEnum("big_smoke");

    }
}
