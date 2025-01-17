package com.github.FlyBird.FutureMITE.events;


import com.github.FlyBird.FutureMITE.events.listener.*;
import moddedmite.rustedironcore.api.event.Handlers;

public class FutureMITEEventRIC extends Handlers {
    public static void register() {
        EntityTracker.register(new EntityTrackerRegistry());
        LootTable.register(new LootTableRegistry());
        Barbecue.register(new BarbecueListener());
        TileEntityData.register(new TileEntityDataTypeRegistry());
        BiomeGenerate.register(new BiomeGenerateListener());
        PropertiesRegistry.register(new PropertyRegistry());
    }
}
