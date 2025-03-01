package com.github.FlyBird.FutureMITE.events.listener;

import com.github.FlyBird.FutureMITE.entities.EntityArmorStand;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoat;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoatSeat;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoatWithChest;
import moddedmite.rustedironcore.api.event.events.EntityTrackerRegisterEvent;
import moddedmite.rustedironcore.api.event.handler.EntityTrackerHandler;
import net.minecraft.Entity;
import net.minecraft.WorldClient;

import java.util.function.Consumer;

public class EntityTrackerRegistry implements Consumer<EntityTrackerRegisterEvent> {
    @Override
    public void accept(EntityTrackerRegisterEvent event) {
        event.registerEntityTracker(entity -> entity instanceof EntityNewBoat, 80, 3, true);
        event.registerEntityTracker(entity -> entity instanceof EntityNewBoatWithChest, 80, 3, true);
        event.registerEntityTracker(entity -> entity instanceof EntityNewBoatSeat, 80, 3, true);
        event.registerEntityTracker(entity -> entity instanceof EntityArmorStand, 64, 5, true);

        event.registerEntityPacket(entity -> entity instanceof EntityNewBoat, transform(EntityNewBoat::new));
        event.registerEntityPacket(entity -> entity instanceof EntityNewBoatWithChest, transform(EntityNewBoatWithChest::new));
        event.registerEntityPacket(entity -> entity instanceof EntityNewBoatSeat, transform(EntityNewBoatSeat::new));
    }

    private static EntityTrackerHandler.EntitySupplier transform(SimpleConstructor simpleConstructor) {
        return (world, x, y, z, packet) -> simpleConstructor.get(world, x, y, z);
    }


    @FunctionalInterface
    private interface SimpleConstructor {
        Entity get(WorldClient world, double x, double y, double z);
    }
}
