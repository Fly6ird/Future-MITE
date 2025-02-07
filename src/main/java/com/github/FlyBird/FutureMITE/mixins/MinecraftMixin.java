package com.github.FlyBird.FutureMITE.mixins;

import com.github.FlyBird.FutureMITE.compat.CampfireHUDHandlerMITE;
import mcp.mobius.waila.api.impl.ModuleRegistrar;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/ReferenceFileWriter;write()V"))
    private void onWorldUnload(CallbackInfo ci) {
//        WailaHandler.register();
        CampfireHUDHandlerMITE.callbackRegister(ModuleRegistrar.instance());
    }
}
