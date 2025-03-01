package com.github.FlyBird.FutureMITE.mixins;

import net.minecraft.CommandHandler;
import net.minecraft.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommandHandler.class)
public class CommandHandlerMixin {
    @Redirect(method = "executeCommand",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/WorldServer;getDayOfWorld()I",
                    ordinal = 0))
    private int redirectGetDayOfWorld(WorldServer instance) {
        if (instance.getDayOfWorld() > 32) {
            return 1000;
        }
        return instance.getDayOfWorld();  // 返回一个大于60的值，这样 if 判断就不会为 true
    }

    @ModifyConstant(method = "executeCommand", constant = @Constant(stringValue = "Villages can generate at day 60"))
    private String modifyString(String original) {
        return "Villages can generate at day 33";
    }
   /* @Redirect(method = "executeCommand",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/WorldInfo;getVillageConditions()B",
                    ordinal = 0))
    private byte redirectGetDayOfWorld(WorldInfo instance) {
        return WorldInfo.getVillagePrerequisites();  // 生成村庄不再需要工具
    }*/

}
