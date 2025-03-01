package com.github.FlyBird.FutureMITE.mixins.entity;


import net.minecraft.AxisAlignedBB;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({Entity.class})
public class EntityMixin {
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    @Shadow
    public float fallDistance;
    @Unique
    private boolean isInBush;

    @Inject(method = "moveEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/Entity;isSneaking()Z", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void sweetBerryMoveEntity(double par1, double par3, double par5, CallbackInfo ci, double var7, double var9, double var11, double var13, double var15, double var17, AxisAlignedBB var19) {
        if (this.isInBush) {
            this.isInBush = false;
            par1 *= 0.25;
            par3 *= 0.05;
            par5 *= 0.25;
            var13 = par1;
            var15 = par3;
            var17 = par5;
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
        }
    }

    @Inject(method = "setFire", at = @At("HEAD"), cancellable = true)
    private void setInBush(int par1, CallbackInfo ci) {
        //-1:setInBush
        if (par1 == -1) {
            this.isInBush = true;
            this.fallDistance = 0.0f;
            ci.cancel();
        }
    }

}
