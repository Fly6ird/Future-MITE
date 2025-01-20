package com.github.FlyBird.FutureMITE.mixins.render;



import com.github.FlyBird.FutureMITE.blocks.Blocks;
import com.github.FlyBird.FutureMITE.blocks.TreeHelper;
import com.github.FlyBird.FutureMITE.entityFX.EntityBigSmokeFX;
import com.github.FlyBird.FutureMITE.entityFX.EntitySoulFlameFX;
import com.github.FlyBird.FutureMITE.misc.EnumParticles;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderGlobal.class)

public class RenderGlobalMixin {
@Shadow private WorldClient theWorld;

@Shadow private Minecraft mc;

//修复叶子透视的问题
@Inject(method = "loadRenderers",at= @At(value = "INVOKE", target = "Lnet/minecraft/BlockLeaves;setGraphicsLevel(Z)V"))
    private void setGraphiscsLevel(CallbackInfo ci)
    {
        Blocks.acacia.Leaves.setGraphicsLevel(this.mc.gameSettings.isFancyGraphicsEnabled());
        Blocks.darkOak.Leaves.setGraphicsLevel(this.mc.gameSettings.isFancyGraphicsEnabled());
        Blocks.cherry.Leaves.setGraphicsLevel(this.mc.gameSettings.isFancyGraphicsEnabled());
    }

@Inject(method ="doSpawnParticle",at=@At(value = "RETURN",ordinal = 3))
    private void doSpawnParticle(EnumParticle enum_particle, int index, int metadata, double posX, double posY, double posZ, double par8, double par10, double par12, CallbackInfoReturnable<EntityFX> cir) {
        EntityFX var21;
        if (enum_particle ==  EnumParticles.soulFlame) {
            var21 = new EntitySoulFlameFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
            this.mc.effectRenderer.addEffect(var21);
        }
        if(enum_particle==EnumParticles.largerSmoke) {
            var21 = new EntityBigSmokeFX(this.theWorld, (int)posX, (int)posY, (int)posZ, false);
            this.mc.effectRenderer.addEffect(var21);
        }
    }

}



