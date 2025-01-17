package com.github.FlyBird.FutureMITE.events.listener;


import com.github.FlyBird.FutureMITE.world.structure.OceanMonument;

public class PropertyRegistry implements Runnable {
    @Override
    public void run() {
        OceanMonument.makeMap();
    }
}
