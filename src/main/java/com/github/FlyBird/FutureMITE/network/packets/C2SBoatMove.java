package com.github.FlyBird.FutureMITE.network.packets;

import com.github.FlyBird.FutureMITE.network.FutureMITENetWork;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;
import net.minecraft.server.MinecraftServer;

import static com.github.FlyBird.FutureMITE.network.FutureMITENetWork.BoatMoveC2S;

public class C2SBoatMove implements Packet {
    private int entityId;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;

    public C2SBoatMove(PacketByteBuf packetByteBuf){
        this(packetByteBuf.readInt(),packetByteBuf.readDouble(),packetByteBuf.readDouble(),packetByteBuf.readDouble(),packetByteBuf.readFloat(),packetByteBuf.readFloat());
    }

    public C2SBoatMove(int id,double x,double y,double z,float yaw,float pitch) {
        this.entityId = id;
        this.x=x;
        this.y=y;
        this.z=z;
        this.yaw=yaw;
        this.pitch=pitch;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.entityId);
        packetByteBuf.writeDouble(this.x);
        packetByteBuf.writeDouble(this.y);
        packetByteBuf.writeDouble(this.z);
        packetByteBuf.writeFloat(this.yaw);
        packetByteBuf.writeFloat(this.pitch);

    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        WorldServer vehWorld = entityPlayer.getWorldServer();
        if (vehWorld != null) {
            Entity vehicle = vehWorld.getEntityByID(this.entityId);
            if (vehicle == null)
                return;
            if (vehicle.riddenByEntity !=entityPlayer) {
                /* Only take position updates from the riding player */
                return ;
            }
            double expectedDelta = vehicle.motionX * vehicle.motionX + vehicle.motionY * vehicle.motionY + vehicle.motionZ * vehicle.motionZ;
            double deltaX = this.x - vehicle.posX;
            double deltaY = this.y - vehicle.posY;
            double deltaZ = this.z - vehicle.posZ;
            double actualDelta = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
            MinecraftServer server = MinecraftServer.getServer();
            if (Math.abs(expectedDelta - actualDelta) > 100.0D && !server.isSinglePlayer()) {
                System.err.println("Vehicle moved wrongly");
                vehicle.setPositionAndRotation(vehicle.posX - 1, vehicle.posY, vehicle.posZ - 1, vehicle.rotationYaw, vehicle.rotationPitch);
                //ctx.getServerHandler().sendPacket(new S18PacketEntityTeleport(vehicle));
                //playerEntity.sendPacket(new Packet34EntityTeleport(vehicle));
                entityPlayer.getWorldServer().getEntityTracker().sendPacketToAllPlayersTrackingEntity(vehicle,new Packet34EntityTeleport(vehicle));
                //((ServerPlayer)entityPlayer).playerNetServerHandler.sendPacketToPlayer(n);
                return ;
            }
            vehicle.setPositionAndRotation(this.x, this.y, this.z, this.yaw, this.pitch);
            entityPlayer.getWorldServer().getEntityTracker().sendPacketToAllPlayersTrackingEntity(vehicle,new Packet34EntityTeleport(vehicle));
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return BoatMoveC2S;
    }
}
