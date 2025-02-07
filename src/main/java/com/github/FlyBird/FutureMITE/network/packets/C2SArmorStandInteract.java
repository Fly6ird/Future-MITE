package com.github.FlyBird.FutureMITE.network.packets;

import com.github.FlyBird.FutureMITE.entities.EntityArmorStand;
import com.github.FlyBird.FutureMITE.network.FutureMITENetWork;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.minecraft.Vec3;
import net.minecraft.World;

public class C2SArmorStandInteract implements Packet {
    private int standID;
    private Vec3 hitPos;

    public C2SArmorStandInteract(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readInt(), packetByteBuf.readDouble(), packetByteBuf.readDouble(), packetByteBuf.readDouble());
    }

    public C2SArmorStandInteract(int id, double x, double y, double z) {
        this.standID = id;
        hitPos = Vec3.createVectorHelper(x, y, z);
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.standID);
        packetByteBuf.writeDouble(hitPos.xCoord);
        packetByteBuf.writeDouble(hitPos.yCoord);
        packetByteBuf.writeDouble(hitPos.zCoord);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        World world = entityPlayer.worldObj;
        EntityArmorStand stand = (EntityArmorStand) world.getEntityByID(this.standID);
        stand.interact(entityPlayer, this.hitPos);
    }

    @Override
    public ResourceLocation getChannel() {
        return FutureMITENetWork.ArmorStandInteractC2S;
    }
}
