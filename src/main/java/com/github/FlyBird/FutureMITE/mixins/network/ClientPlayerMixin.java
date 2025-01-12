package com.github.FlyBird.FutureMITE.mixins.network;



import com.github.FlyBird.FutureMITE.api.IFutureMITEPlayer;
import com.github.FlyBird.FutureMITE.gui.grindstone.GrindStoneSlots;
import com.github.FlyBird.FutureMITE.gui.grindstone.GuiGrindStone;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ClientPlayer.class})
public class ClientPlayerMixin extends AbstractClientPlayer implements IFutureMITEPlayer {
    @Shadow
    protected Minecraft mc;

    public ClientPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Override
    public void futureMITE$displayGUIGrindstone(int x, int y, int z, GrindStoneSlots slots) {
        this.mc.displayGuiScreen(new GuiGrindStone(this, x, y, z, slots));
    }

    @Override
    public INetworkManager getNetManager() {
        return null;
    }

    @Override
    public void sendChatToPlayer(ChatMessageComponent chatMessageComponent) {

    }

    @Override
    public boolean canCommandSenderUseCommand(int i, String string) {
        return false;
    }

    @Override
    public ChunkCoordinates getPlayerCoordinates() {
        return null;
    }
}