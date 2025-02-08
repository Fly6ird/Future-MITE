package com.github.FlyBird.FutureMITE.events.listener;


import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import moddedmite.rustedironcore.api.event.events.TileEntityDataTypeRegisterEvent;

import java.util.function.Consumer;

public class TileEntityDataTypeRegistry implements Consumer<TileEntityDataTypeRegisterEvent> {
    public static int CAMPFIRE;

    @Override
    public void accept(TileEntityDataTypeRegisterEvent tileEntityDataTypeRegisterEvent) {
        CAMPFIRE = tileEntityDataTypeRegisterEvent.register(TileEntityCampfire.class);
    }
}
