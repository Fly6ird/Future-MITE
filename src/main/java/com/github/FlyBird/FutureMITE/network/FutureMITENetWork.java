package com.github.FlyBird.FutureMITE.network;

import com.github.FlyBird.FutureMITE.FutureMITEStart;
import com.github.FlyBird.FutureMITE.network.packets.C2SBoatMove;
import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketReader;

import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.xiaoyu233.fml.FishModLoader;

public class FutureMITENetWork {
    public static final ResourceLocation BoatMoveC2S = new ResourceLocation(FutureMITEStart.NameSpaceCompact, "BoatMoveC2S");

    public static void sendToClient(ServerPlayer player, Packet packet) {
        Network.sendToClient(player, packet);
    }

    public static void sendToServer(Packet packet) {
        Network.sendToServer(packet);
    }

    public static void init() {
        if (!FishModLoader.isServer()) {
            initClient();
        }
        initServer();
    }

    private static void initClient() {



    }

    private static void initServer() {
        PacketReader.registerServerPacketReader(BoatMoveC2S, C2SBoatMove::new);
    }
}
