package com.github.FlyBird.FutureMITE.network.packets;

import com.github.FlyBird.FutureMITE.api.IFutureMITEPlayer;
import com.github.FlyBird.FutureMITE.gui.grindstone.GrindStoneSlots;
import com.github.FlyBird.FutureMITE.network.FutureMITENetWork;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;


public class S2COpenWindow implements Packet {
    public int windowId;
    public String windowTitle;
    public int slotsCount;
    public boolean has_set_coords;
    public boolean useProvidedWindowTitle;
    public EnumInventoryType enumInventoryType;
    public int x;
    public int y;
    public int z;

    public S2COpenWindow(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readByte() & 0xFF, EnumInventoryType.getFromID(packetByteBuf.readByte() & 0xFF), packetByteBuf.readString(), packetByteBuf.readByte() & 0xFF, packetByteBuf.readBoolean());
        if (this.hasCoords()) {
            this.x = packetByteBuf.readInt();
            this.y = packetByteBuf.readInt();
            this.z = packetByteBuf.readInt();
        }
    }

    public S2COpenWindow(int windowId, EnumInventoryType enumInventoryType, String windowTitle, int slotsCount, boolean useProvidedWindowTitle) {
        if (windowTitle == null) {
            windowTitle = "";
        }
        this.windowId = windowId;
        this.windowTitle = windowTitle;
        this.slotsCount = slotsCount;
        this.useProvidedWindowTitle = useProvidedWindowTitle;
        this.enumInventoryType = enumInventoryType;
    }

    public S2COpenWindow setCoords(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.has_set_coords = true;
        return this;
    }

    public boolean hasCoords() {
        return this.enumInventoryType.hasCoords;
    }

    public boolean hasTileEntity() {
        return this.hasCoords();
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeByte(this.windowId & 0xFF);
        packetByteBuf.writeByte(this.enumInventoryType.id & 0xFF);
        packetByteBuf.writeString(this.windowTitle);
        packetByteBuf.writeByte(this.slotsCount & 0xFF);
        packetByteBuf.writeBoolean(this.useProvidedWindowTitle);
        if (this.hasCoords()) {
            if (!this.has_set_coords) {
                Minecraft.setErrorMessage("S2COpenWindow: coords not set for type " + this.enumInventoryType);
            }
            packetByteBuf.writeInt(this.x);
            packetByteBuf.writeInt(this.y);
            packetByteBuf.writeInt(this.z);
        }
    }

    @Override
    public void apply(EntityPlayer player) {
        WorldClient world = player.worldObj.getAsWorldClient();
        TileEntity tile_entity = world.getBlockTileEntity(this.x, this.y, this.z);
        if (this.hasTileEntity() && tile_entity == null) {
            Minecraft.setErrorMessage("handleOpenWindow: no tile entity found at " + StringHelper.getCoordsAsString(this.x, this.y, this.z));
        }

        switch (this.enumInventoryType) {
            case GrindStone -> {
                ((IFutureMITEPlayer) player).futureMITE$displayGUIGrindstone(this.x, this.y, this.z, new GrindStoneSlots(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
                player.openContainer.windowId = this.windowId;
            }
            default -> Minecraft.setErrorMessage("handleOpenWindow: type not handled " + this.enumInventoryType);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return FutureMITENetWork.OpenWindow;
    }

    public enum EnumInventoryType {
        GrindStone(0, true),
        ;

        public final int id;
        public final boolean hasCoords;

        EnumInventoryType(int id, boolean hasCoords) {
            this.id = id;
            this.hasCoords = hasCoords;
        }

        public static EnumInventoryType getFromID(int id) {
            return EnumInventoryType.values()[id];
        }
    }
}
