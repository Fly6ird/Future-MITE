package com.github.FlyBird.FutureMITE.events;


import com.github.FlyBird.FutureMITE.events.listener.EntityTrackerRegistry;
import moddedmite.rustedironcore.api.event.Handlers;

public class FutureMITEEventRIC extends Handlers {
    public static void register() {
        EntityTracker.register(new EntityTrackerRegistry());
    }
}
