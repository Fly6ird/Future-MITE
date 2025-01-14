package com.github.FlyBird.FutureMITE.mixins.network;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({NetClientHandler.class})
public abstract class NetClientHandlerMixin extends NetHandler {
  @Shadow
  private WorldClient worldClient;

  @Shadow
  private Minecraft mc;


/*  @Inject(method = "handleVehicleSpawn", at = @At(value = "FIELD", target = "Lnet/minecraft/Packet23VehicleSpawn;type:I", ordinal = 0), cancellable = true)
  private void handleVehicleSpawn(Packet23VehicleSpawn par1Packet23VehicleSpawn, CallbackInfo ci,@Local(ordinal = 0) double x,@Local(ordinal = 1) double y,@Local(ordinal = 2) double z) {

    if (par1Packet23VehicleSpawn.type == 200) {
      EntityNewBoat entity = new EntityNewBoat(this.worldClient, x, y, z);
      entity.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
      entity.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);

      entity.entityId = par1Packet23VehicleSpawn.entityId;
      this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, entity);
      if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
        if (par1Packet23VehicleSpawn.type == 60) {

          entity.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
          return;
        }

        entity.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
      }
      ci.cancel();
    }
    if(par1Packet23VehicleSpawn.type == 201)
    {
      EntityNewBoatSeat entity = new EntityNewBoatSeat(this.worldClient,x,y,z);
      entity.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
      entity.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);

      entity.entityId = par1Packet23VehicleSpawn.entityId;
      this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, entity);
      if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
        if (par1Packet23VehicleSpawn.type == 60) {
          entity.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
          return;
        }
        entity.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
      }
      // 不再继续执行原版的判断
      ci.cancel();
    }
    if(par1Packet23VehicleSpawn.type == 202)
    {
      EntityNewBoatWithChest entity = new EntityNewBoatWithChest(this.worldClient,x,y,z);
      entity.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
      entity.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);

      entity.entityId = par1Packet23VehicleSpawn.entityId;
      this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, entity);
      if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
        if (par1Packet23VehicleSpawn.type == 60) {
          entity.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
          return;
        }
        entity.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
      }
      // 不再继续执行原版的判断
      ci.cancel();
    }
  }*/

}
