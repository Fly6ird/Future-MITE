package com.github.FlyBird.FutureMITE.mixins.entity;


import com.github.FlyBird.FutureMITE.entities.EntityArmorStand;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {
    @Shadow
    public Entity myEntity;

    //因为原方法代码垃圾，导致EntityLiving判断根本加载不进去
    @Inject(method = "getPacketForThisEntity", at = @At("HEAD"), cancellable = true)
    private void getPacketForThisEntity(CallbackInfoReturnable<Packet> cbi) {
        // 保持最初的isDead判断
        if (this.myEntity.isDead) {
            this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
        }

        if (this.myEntity instanceof EntityArmorStand) {
            Packet ret = new Packet24MobSpawn((EntityArmorStand) this.myEntity);
            cbi.setReturnValue(ret);
        }
    }
}
