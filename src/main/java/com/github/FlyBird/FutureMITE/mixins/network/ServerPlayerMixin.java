package com.github.FlyBird.FutureMITE.mixins.network;


import com.github.FlyBird.FutureMITE.api.IFutureMITEPlayer;
import com.github.FlyBird.FutureMITE.gui.grindstone.ContainerGrindStone;
import com.github.FlyBird.FutureMITE.gui.grindstone.GrindStoneSlots;
import com.github.FlyBird.FutureMITE.network.packets.S2COpenWindow;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ServerPlayer.class})
public abstract class ServerPlayerMixin extends EntityPlayer implements ICrafting, IFutureMITEPlayer{
    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Shadow
    public NetServerHandler playerNetServerHandler;

    @Shadow
    private int currentWindowId;

    @Shadow
    protected abstract void incrementWindowID();

    @Override
    public void futureMITE$displayGUIGrindstone(int x, int y, int z, GrindStoneSlots slots) {
        incrementWindowID();
        TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
        Network.sendToClient(this.getAsEntityPlayerMP(), (new S2COpenWindow(this.currentWindowId, S2COpenWindow.EnumInventoryType.GrindStone, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));

        this.openContainer = (Container)new ContainerGrindStone(slots, this, x, y, z);
        this.openContainer.windowId = this.currentWindowId;
        sendContainerAndContentsToPlayer(this.openContainer, ((ContainerGrindStone)this.openContainer).getInventory());
        this.openContainer.addCraftingToCrafters(this);
    }
}