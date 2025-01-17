package com.github.FlyBird.FutureMITE.mixins.world;


import com.github.FlyBird.FutureMITE.common.GameRegistry;
import net.minecraft.ChunkProviderServer;
import net.minecraft.IChunkProvider;
import net.minecraft.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkProviderServer.class)
public class ChunkProviderServerMixin {

    @Shadow
    private IChunkProvider currentChunkProvider;

    @Shadow
    public WorldServer worldObj;


    @Inject(method = "populate",at= @At(value = "INVOKE", target = "Lnet/minecraft/IChunkProvider;populate(Lnet/minecraft/IChunkProvider;II)V"))
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3, CallbackInfo ci) {
        //GameRegistry.generateWorld(par2, par3, worldObj, currentChunkProvider, par1IChunkProvider);
    }
}
