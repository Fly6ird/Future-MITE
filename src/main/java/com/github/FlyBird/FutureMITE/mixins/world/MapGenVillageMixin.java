package com.github.FlyBird.FutureMITE.mixins.world;

import net.minecraft.MapGenVillage;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(MapGenVillage.class)

public class MapGenVillageMixin {
    @Redirect(method = "canSpawnStructureAtCoords",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/World;getDayOfWorld()I"))
    private int redirectGetDayOfWorld(World world) {
        if (world.getDayOfWorld() > 32) {
            return 1000;  // 返回一个大于60的值，这样 if 判断就不会为 true
        } else
            return world.getDayOfWorld();
    }

    /*@Redirect(method = "canSpawnStructureAtCoords",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/WorldInfo;getVillageConditions()B"))
    private byte getVillageConditions(WorldInfo instance) {
        return WorldInfo.getVillagePrerequisites();  // 生成村庄不再需要工具
    }*/

}
