package com.github.FlyBird.FutureMITE.mixins.entity;


import com.github.FlyBird.FutureMITE.entities.EntityArmourStand;
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

    @Inject(method = "getPacketForThisEntity", at = @At("HEAD"), cancellable = true)
    private void getPacketForThisEntity(CallbackInfoReturnable<Packet> cbi) {
        // 保持最初的isDead判断
        if (this.myEntity.isDead) {
            this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
        }

        if (this.myEntity instanceof EntityArmourStand) {
            Packet ret = new Packet24MobSpawn((EntityArmourStand) this.myEntity);
            cbi.setReturnValue(ret);
        }
    }
}
