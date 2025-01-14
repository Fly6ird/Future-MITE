package com.github.FlyBird.FutureMITE.events.listener;


import com.github.FlyBird.FutureMITE.tileentities.TileEntityCampfire;
import moddedmite.rustedironcore.api.event.events.TileEntityDataTypeRegisterEvent;

import java.util.function.Consumer;

public class TileEntityDataTypeRegiste  implements Consumer<TileEntityDataTypeRegisterEvent> {
    @Override
    public void accept(TileEntityDataTypeRegisterEvent tileEntityDataTypeRegisterEvent) {
        tileEntityDataTypeRegisterEvent.register(TileEntityCampfire.class);
    }
}
