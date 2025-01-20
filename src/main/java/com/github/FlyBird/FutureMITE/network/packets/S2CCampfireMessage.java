package com.github.FlyBird.FutureMITE.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;

public class S2CCampfireMessage implements Packet {
    private int[] itemID = new int[4];
    private int[] itemBurnTime = new int[4];

    @Override
    public void write(PacketByteBuf packetByteBuf) {

    }

    @Override
    public void apply(EntityPlayer entityPlayer) {

    }

    @Override
    public ResourceLocation getChannel() {
        return null;
    }
}
