package com.github.FlyBird.FutureMITE.mixins.entity;

import com.github.FlyBird.FutureMITE.entities.EntityNewBoat;
import com.github.FlyBird.FutureMITE.entities.EntityNewBoatWithChest;
import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayer.class)
public class EntityPlayerMPMixin extends EntityPlayer {

    @Redirect(method = "onUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/ServerPlayer;ridingEntity:Lnet/minecraft/Entity;", opcode = Opcodes.GETFIELD))
    private Entity redirectRidingEntityCheck(ServerPlayer instance) {
        // 替换原判断逻辑
        if (ridingEntity instanceof EntityNewBoatWithChest || ridingEntity instanceof EntityNewBoat || ridingEntity instanceof EntityBoat)
            return new EntityBoat(this.getWorld());

        return ridingEntity;
    }


    public EntityPlayerMPMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Shadow
    public NetServerHandler playerNetServerHandler;

    @Override
    public INetworkManager getNetManager() {
        return this.playerNetServerHandler.getNetManager();
    }

    @Override
    public void sendChatToPlayer(ChatMessageComponent par1ChatMessageComponent) {
        this.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(par1ChatMessageComponent));
    }

    @Shadow
    public MinecraftServer mcServer;

    public boolean canCommandSenderUseCommand(int par1, String par2Str) {
        if (Minecraft.inDevMode()) {
            return true;
        } else {
            return "seed".equals(par2Str) && !this.mcServer.isDedicatedServer() ? true : (!"tell".equals(par2Str) && !"help".equals(par2Str) && !"me".equals(par2Str) ? (this.mcServer.getConfigurationManager().isPlayerOpped(this.username) ? this.mcServer.func_110455_j() >= par1 : false) : true);
        }
    }

    @Override
    public ChunkCoordinates getPlayerCoordinates() {
        return new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + 0.5), MathHelper.floor_double(this.posZ));
    }
}
