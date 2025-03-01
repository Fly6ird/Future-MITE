package com.github.FlyBird.FutureMITE.mixins.entity;

import net.minecraft.EntityItem;
import net.minecraft.Explosion;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityItem.class)
public class EntityItemMixin {
    @Inject(method = "handleExplosion",at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityItem;setDead()V",ordinal = 1), cancellable = true)
    public void handleExplosion(Explosion explosion, CallbackInfoReturnable<Boolean> cir) {
        if(instance.getEntityItem().getItem() == Item.netherStar)
        {
            cir.setReturnValue(false);
        }

    }

    @Unique
    private final EntityItem instance = (EntityItem) (Object)this;
}
