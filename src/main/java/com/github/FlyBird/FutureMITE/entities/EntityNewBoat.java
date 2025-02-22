package com.github.FlyBird.FutureMITE.entities;


import com.github.FlyBird.FutureMITE.items.ItemNewBoat;
import com.github.FlyBird.FutureMITE.network.FutureMITENetWork;
import com.github.FlyBird.FutureMITE.network.packets.C2SBoatMove;
import com.google.common.collect.Lists;
import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;


public class EntityNewBoat extends Entity {
    private static final int[] DATA_ID_PADDLE = new int[]{24, 25};
    private static final int DATA_ID_LAST_HIT = 17;
    private static final int DATA_ID_FORWARD = 18;
    private static final int DATA_ID_DAMAGE_TAKEN = 19;
    private static final int DATA_ID_OLD_TYPE = 20;
    private static final int DATA_ID_TYPE = 21;
    private static final int DATA_ID_RESOURCELOCATION = 22;
    private static final int DATA_ID_RAFT = 23;
    private final float[] paddlePositions;

    /**
     * How much of current speed to retain. Value zero to one.
     */
    private float momentum;
    private float outOfControlTicks;
    private float deltaRotation;
    public int lerpSteps;
    private double boatPitch;
    private double lerpY;
    private double lerpZ;
    public double boatYaw;
    private double lerpXRot;
    private boolean leftInputDown;
    private boolean rightInputDown;
    private boolean forwardInputDown;
    private boolean backInputDown;
    private double waterLevel;

    /**
     * How much the boat should glide given the slippery blocks it's currently gliding over.
     * Halved every tick.
     */
    private float boatGlide;
    private Status status;
    private Status previousStatus;
    private double lastYd;

    private EntityNewBoatSeat seat;
    private EntityNewBoatSeat seatToSpawn;
    private String entityName;


    protected ResourceLocation resourceLocation;

    protected ItemNewBoat.BoatInfo boatInfo = ItemNewBoat.BOAT_INFO.get("oak");


    public EntityNewBoat(World world) {
        super(world);
        this.paddlePositions = new float[2];
        this.preventEntitySpawning = true;
        this.setSize(1.375F, 0.5625F);
    }

    public EntityNewBoat(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override
    public Item getModelItem() {
        return boatInfo.getBoatItem().getItem();
    }

    public EntityNewBoatSeat getSeat() {
        return seat;
    }

    public void setSeat(EntityNewBoatSeat seatIn) {
        seat = seatIn;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking() {
        return false;
    }

    protected void entityInit() {
        this.dataWatcher.addObject(DATA_ID_LAST_HIT, 0);
        this.dataWatcher.addObject(DATA_ID_FORWARD, 1);
        this.dataWatcher.addObject(DATA_ID_DAMAGE_TAKEN, 0.0F);
        // ET FUTURUM START
        this.dataWatcher.addObject(DATA_ID_OLD_TYPE, 0);
        this.dataWatcher.addObject(DATA_ID_TYPE, "oak");
        this.dataWatcher.addObject(DATA_ID_RESOURCELOCATION, "futuremite:textures/entity/boat/oak.png");
        this.dataWatcher.addObject(DATA_ID_RAFT, (byte) 0);
        for (int i = 0; i < DATA_ID_PADDLE.length; ++i) {
            dataWatcher.addObject(DATA_ID_PADDLE[i], (byte) 0);
        }
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entityIn) {
        return entityIn instanceof EntityNewBoatSeat ? null : entityIn.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed() {
        return true;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset() {
        return isRaft() ? 0.3D : 0;
    }

    public double getYOffset() {
        return 0;
    }

    public boolean isPassenger(Entity entity) {
        return getPassengers().contains(entity);
    }

    public void removePassengers() {
        if (getPassengers().isEmpty()) return;
        for (EntityLivingBase passenger : getPassengers()) {
            passenger.mountEntity(null);
        }
    }

    public List<EntityLivingBase> getPassengers() {
        List<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        if (riddenByEntity instanceof EntityLivingBase) {
            list.add((EntityLivingBase) riddenByEntity);
            if (seat != null && seat.riddenByEntity != null) {
                list.add((EntityLivingBase) seat.riddenByEntity);
            }
        }
        return list;
    }

    public void sitEntity(Entity entity) {
        entity.mountEntity(this);
    }

    private void addToSeat(Entity entity) {
        seat.sitEntity(entity);
    }

    public void addToBoat(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) return;
        EntityLivingBase oldDriver = (EntityLivingBase) getControllingPassenger();
        if (getPassengers().isEmpty()) {
            sitEntity(entity);
        } else if (getPassengers().size() == 1) {
            if (seat == null) return;
            if (entity instanceof EntityPlayer && !(oldDriver instanceof EntityPlayer)) {
                addToSeat(oldDriver);
                entity.mountEntity(this);
            } else {
                addToSeat(entity);
            }
        }
        entity.prevRotationYaw = this.rotationYaw;
        entity.rotationYaw = this.rotationYaw;
    }

    public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
        if (!player.isSneaking() && this.outOfControlTicks < 60.0F) {
            addToBoat(player);
        }
        return true;
    }
/*
	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!player.isSneaking() && this.outOfControlTicks < 60.0F) {
			addToBoat(player);
		}

		return true;
	}
*/

    public boolean isBeingRidden() {
        return !getPassengers().isEmpty();
    }

    public boolean canPassengerSteer() {
        return !worldObj.isRemote || getControllingPassenger() instanceof EntityPlayer && riddenByEntity == getControllingPassenger();
    }

    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 2;
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
    public Entity getControllingPassenger() {
        List<EntityLivingBase> list = this.getPassengers();
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Called when the entity is attacked.
     */
    public EntityDamageResult attackEntityFrom(Damage damage) {
        DamageSource source = damage.getSource();

        if (this.isEntityInvulnerable()) {
            return null;
        } else if (!this.worldObj.isRemote && !this.isDead) {
            if (source instanceof EntityDamageSourceIndirect && source.getResponsibleEntity() != null && this.isPassenger(source.getResponsibleEntity())) {
                return null;
            }
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + damage.getAmount() * 10.0F);
            this.setBeenAttacked();
            boolean flag = source.getResponsibleEntity() instanceof EntityPlayer && ((EntityPlayer) source.getResponsibleEntity()).capabilities.isCreativeMode;

            if (flag || this.getDamageTaken() > 40.0F) {
                if (!flag && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
				/*	ItemStack boat = getBoatDrop();
					if (this.entityName != null) {
						boat.setStackDisplayName(this.entityName);
					}*/
                    dropItemStack(boatInfo.getBoatItem(), 1);
				/*	if (this instanceof EntityNewBoatWithChest ) {
						this.dropItem(Item.getItem(Block.chest), 1);
					}*/
                }

                this.setDead();
            }

            //return true;
        } else {
            //return true;
        }
        return null;
    }

    public void setDead() {
        if (riddenByEntity instanceof EntityLivingBase) {
            ((EntityLivingBase) riddenByEntity).dismountEntity(this);
            riddenByEntity.ridingEntity = null;
        }
        super.setDead();
    }

    /**
     * Applies a velocity to the entities, to push them away from eachother.
     */
    public void applyEntityCollision(Entity entityIn) {
        if (entityIn instanceof EntityNewBoat) {
            if (entityIn.boundingBox.minY < this.boundingBox.maxY) {
                super.applyEntityCollision(entityIn);
            }
        }
        if (entityIn instanceof EntityNewBoatSeat || getPassengers().contains(entityIn) || entityIn.ridingEntity instanceof EntityNewBoat || entityIn.ridingEntity instanceof EntityNewBoatSeat) {
        } else if (entityIn.boundingBox.minY <= this.boundingBox.minY) {
            super.applyEntityCollision(entityIn);
        }
    }

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
    public void performHurtAnimation() {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    /**
     * Set the position and rotation values directly without any clamping.
     */
    @Override
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {
        this.boatPitch = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpXRot = pitch;
        this.lerpSteps = 5;
        boatYaw = yaw;
    }

    /**
     * Gets the horizontal facing direction of this Entity, adjusted to take specially-treated entity types into
     * account.
     */
    public EnumFacing getAdjustedHorizontalFacing() {
        return this.getAdjustedHorizontalFacing();
    }

    public boolean hasSeat() {
        return seat != null && !seat.isDead;
    }

    /**
     * Let subclasses disable the passenger seat if they want to.
     */
    protected boolean shouldHaveSeat() {
        return true;
    }

    public void onEntityUpdate() {
        super.onEntityUpdate();

        //TODO add option for no passenger seat and don't run this code
//		if(worldObj.isRemote )
//			System.out.print("啊哈哈");
        //This causes the boat to not fall for some reason!
        if (!worldObj.isRemote && !hasSeat() && shouldHaveSeat()) {  //ConfigBlocksItems.newBoatPassengerSeat
            EntityNewBoatSeat newSeat;
            if (seatToSpawn == null) {
                newSeat = new EntityNewBoatSeat(worldObj, this, this.posX, this.posY, this.posZ);
                newSeat.setBoat(this);
            } else {
                newSeat = seatToSpawn;
                seatToSpawn = null;
            }
            newSeat.forceSpawn = true;
            newSeat.copyLocationAndAnglesFrom(this);
            worldObj.spawnEntityInWorld(newSeat);
            this.setSeat(newSeat);
            newSeat.forceSpawn = false;
        }

        if (getSeat() != null && getSeat().riddenByEntity != null && riddenByEntity == null) {
            sitEntity(getSeat().riddenByEntity);
        }
    }

    private void collideWithSurfaceBlocks() {
        if (this.worldObj.isRemote) {
            return; // 如果在客户端，直接返回
        }

        AxisAlignedBB box = this.boundingBox;
        int minX = MathHelper.floor_double(box.minX - 0.2d);
        int minY = MathHelper.floor_double(box.minY - 0.2d);
        int minZ = MathHelper.floor_double(box.minZ - 0.2d);
        int maxX = MathHelper.floor_double(box.maxX + 0.2d);
        int maxY = MathHelper.floor_double(box.maxY + 0.2d);
        int maxZ = MathHelper.floor_double(box.maxZ + 0.2d);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = this.worldObj.getBlock(x, y, z);

                    if (block == Block.snow) {
                        this.worldObj.setBlockToAir(x, y, z);
                        this.isCollidedHorizontally = false;
                    } else if (block == Block.waterlily) {

                        this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, x, y, z)).setCollidedWith(this), true);
                        this.isCollidedHorizontally = false;
                    }
                }
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        this.previousStatus = this.status;
        this.status = this.getBoatStatus();

        if (this.status != Status.UNDER_WATER && this.status != Status.UNDER_FLOWING_WATER) {
            this.outOfControlTicks = 0.0F;
        } else {
            ++this.outOfControlTicks;
        }

        if (!this.worldObj.isRemote && this.outOfControlTicks >= 60.0F) {
            this.removePassengers();
        }

        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F) {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        super.onUpdate();
        this.tickLerp();

        if (this.canPassengerSteer()) {
            this.collideWithSurfaceBlocks();
            if (this.getPassengers().size() == 0) {
                this.setPaddleState(false, false);
            }

            this.updateMotion();
            if (this.getPassengers().size() > 0 && getControllingPassenger() instanceof EntityPlayer) {
                EntityPlayer living = (EntityPlayer) this.getControllingPassenger();
                boolean left = living.moveStrafing > 0;
                boolean right = living.moveStrafing < 0;
                boolean forward = living.moveForward > 0;
                boolean back = living.moveForward < 0;
                this.updateInputs(left, right, forward, back);
            } else {
                this.updateInputs(false, false, false, false);
            }

            this.controlBoat();

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            // 添加水花效果
            if (this.status == Status.IN_WATER || this.status == Status.UNDER_FLOWING_WATER) {
                // 根据船的速度生成水花
                double speed = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ); // 计算船的速度
                if (speed > 0.1) { // 如果速度足够大，生成水花
                    int splashCount = (int) Math.min(speed * 30, 300); // 根据速度生成的水花数量，增加粒子数量

                    double sideOffsetX = Math.cos((rotationYaw + 90) * Math.PI / 180.0F) * 1.2F; // 左右两侧X偏移，增加偏移值
                    double sideOffsetZ = Math.sin((rotationYaw + 90) * Math.PI / 180.0F) * 1.2F; // 左右两侧Z偏移，增加偏移值

                    // 生成水花粒子
                    for (int i = 0; i < splashCount; i++) {
                        double offsetX = (rand.nextFloat() - 0.5) * 0.6; // 水花的横向偏移
                        double offsetY = (rand.nextFloat() - 0.5) * 0.3; // 水花的垂直偏移
                        double offsetZ = (rand.nextFloat() - 0.5) * 0.6; // 水花的纵向偏移

                        this.worldObj.spawnParticle(EnumParticle.splash, this.posX - sideOffsetX + offsetX, this.posY + 0.2D, this.posZ - sideOffsetZ + offsetZ, motionX * 0.5, motionY * 0.5, motionZ * 0.5);
                    }

                }
            }
        } else {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.deltaRotation = 0.0F;
        }

        for (int i = 0; i <= 1; ++i) {
            if (this.getPaddleState(i)) {
                if ((double) (this.paddlePositions[i] % ((float) Math.PI * 2F)) <= (Math.PI / 4D) && ((double) this.paddlePositions[i] + 0.39269909262657166D) % (Math.PI * 2D) >= (Math.PI / 4D)) {
                    if (getPaddleSound() != null)
                        playSound(getPaddleSound(), 1, 1);
                }
                this.paddlePositions[i] = (float) ((double) this.paddlePositions[i] + 0.39269909262657166D);
            } else {
                this.paddlePositions[i] = 0.0F;
            }
        }

        this.doBlockCollisions();
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
                this.boundingBox.expand(0.20000000298023224D, -0.009999999776482582D,
                        0.20000000298023224D)/* , EntitySelectors.<Entity>getTeamCollisionPredicate(this) */);

        if (!list.isEmpty()) {
            boolean flag = !this.worldObj.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

            for (int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);

                if (entity.ridingEntity != this) {
                    if (flag && this.getPassengers().size() < 2 && !entity.isRiding() && entity.width < this.width && entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob) && !(entity instanceof EntityPlayer)) {
                        if (canEntitySit(entity)) {
                            addToBoat(entity);
                        }
                    } else {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }

        if (this.worldObj.isRemote && canPassengerSteer() && getControllingPassenger() instanceof EntityClientPlayerMP) {
            //EtFuturum.networkWrapper.sendToServer(new BoatMoveMessage(this));//给服务器发包
            FutureMITENetWork.sendToServer(new C2SBoatMove(this.entityId, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch));
            //Minecraft.theMinecraft.thePlayer.sendQueue.addToSendQueue(new BoatMoveMessage(this));
        }
    }


    private boolean canEntitySit(Entity entity) {
        return true;
		/*if (ConfigBlocksItems.newBoatEntityBlacklist.length == 0) {
			return !ConfigBlocksItems.newBoatEntityBlacklistAsWhitelist;
		}

		if (ArrayUtils.contains(ConfigBlocksItems.newBoatEntityBlacklist, EntityList.getEntityString(entity))) {
			return ConfigBlocksItems.newBoatEntityBlacklistAsWhitelist;
		}

		for (int i = 0; i < ConfigBlocksItems.newBoatEntityBlacklist.length; i++) {
			String blacklistEntry = ConfigBlocksItems.newBoatEntityBlacklist[i];
			if (blacklistEntry.startsWith("classpath:")) {
				if (entity.getClass().getName().contains(blacklistEntry.replace("classpath:", ""))) {
					return ConfigBlocksItems.newBoatEntityBlacklistAsWhitelist;
				}
			}
		}
		return !ConfigBlocksItems.newBoatEntityBlacklistAsWhitelist;*/
    }

    protected String getPaddleSound() {
        switch (this.status) {
            case IN_WATER:
            case UNDER_WATER:
            case UNDER_FLOWING_WATER:
                return "entity.boat.paddle_water";
            case ON_LAND:
                return "entity.boat.paddle_land";
            case IN_AIR:
            default:
                return null;
        }
    }

    private void tickLerp() {
        if (worldObj.isRemote && this.lerpSteps > 0) {
            if (worldObj.isRemote && getSeat() != null && getSeat().riddenByEntity instanceof EntityClientPlayerMP) {
                double i = MathHelper.wrapAngleTo180_double(this.boatYaw - (double) this.rotationYaw);
                getSeat().riddenByEntity.rotationYaw += i / lerpSteps;
                i = MathHelper.wrapAngleTo180_double(this.boatYaw - (double) this.prevRotationYaw);
                getSeat().riddenByEntity.prevRotationYaw += i / lerpSteps;
            }
            if (!(getControllingPassenger() instanceof EntityClientPlayerMP)) {
                double d0 = this.posX + (this.boatPitch - this.posX) / (double) this.lerpSteps;
                double d1 = this.posY + (this.lerpY - this.posY) / (double) this.lerpSteps;
                double d2 = this.posZ + (this.lerpZ - this.posZ) / (double) this.lerpSteps;
                double d3 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double) this.rotationYaw);
                this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.lerpSteps);
                this.rotationPitch = (float) ((double) this.rotationPitch + (this.lerpXRot - (double) this.rotationPitch) / (double) this.lerpSteps);
                --this.lerpSteps;
                this.setPosition(d0, d1, d2);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
        }
    }

    public void setPaddleState(boolean p_184445_1_, boolean p_184445_2_) {
        this.dataWatcher.updateObject(DATA_ID_PADDLE[0], (byte) (p_184445_1_ ? 1 : 0));
        this.dataWatcher.updateObject(DATA_ID_PADDLE[1], (byte) (p_184445_2_ ? 1 : 0));
    }

    public static double denormalizeClamp(double p_151238_0_, double p_151238_2_, double p_151238_4_) {
        return p_151238_4_ < 0.0D ? p_151238_0_ : (p_151238_4_ > 1.0D ? p_151238_2_ : p_151238_0_ + (p_151238_2_ - p_151238_0_) * p_151238_4_);
    }

    public float getRowingTime(int side, float limbSwing) {
        return this.getPaddleState(side) ? (float) denormalizeClamp((double) this.paddlePositions[side] - 0.39269909262657166D, this.paddlePositions[side], limbSwing) : 0.0F;
    }

    /**
     * Determines whether the boat is in water, gliding on land, or in air
     */
    private Status getBoatStatus() {
        Status EntityNewBoat$status = this.getUnderwaterStatus();

        if (EntityNewBoat$status != null) {
            this.waterLevel = this.boundingBox.maxY;
            return EntityNewBoat$status;
        } else if (this.checkInWater()) {
            return Status.IN_WATER;
        } else {
            float f = Math.min(this.getBoatGlide(), 0.986F);//默认是0.986F = ConfigBlocksItems.newBoatMaxLandSpeed

            if (f > 0.0F) {
                this.boatGlide = f;
                return Status.ON_LAND;
            }
            return Status.IN_AIR;
        }
    }

    public float getWaterLevelAbove() {
        AxisAlignedBB axisalignedbb = this.boundingBox;
        int i = MathHelper.floor_double(axisalignedbb.minX);
        int j = MathHelper.ceiling_double_int(axisalignedbb.maxX);
        int k = MathHelper.floor_double(axisalignedbb.maxY);
        int l = MathHelper.ceiling_double_int(axisalignedbb.maxY - this.lastYd);
        int i1 = MathHelper.floor_double(axisalignedbb.minZ);
        int j1 = MathHelper.ceiling_double_int(axisalignedbb.maxZ);

        label78:

        for (int k1 = k; k1 < l; ++k1) {
            float f = 0.0F;
            int l1 = i;

            while (true) {
                if (l1 >= j) {
                    if (f < 1.0F) {
                        float f2 = (float) k1 + f;
                        return f2;
                    }

                    break;
                }

                for (int i2 = i1; i2 < j1; ++i2) {
                    Block iblockstate = this.worldObj.getBlock(l1, k1, i2);
                    if (iblockstate != null) {
                        if ((iblockstate.blockMaterial == Material.water))
                            f = Math.max(f, getBlockLiquidHeight(worldObj, l1, k1, i2));
                    }

                    if (f >= 1.0F) {
                        continue label78;
                    }
                }

                ++l1;
            }
        }

        float f1 = (float) (l + 1);
        return f1;
    }

    /**
     * Decides how much the boat should be gliding on the land (based on any slippery blocks)
     */
    public float getBoatGlide() {
        AxisAlignedBB axisalignedbb = this.boundingBox;
        AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(axisalignedbb.minX, axisalignedbb.minY - 0.001D, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        int i = MathHelper.floor_double(axisalignedbb1.minX) - 1;
        int j = MathHelper.ceiling_double_int(axisalignedbb1.maxX) + 1;
        int k = MathHelper.floor_double(axisalignedbb1.minY) - 1;
        int l = MathHelper.ceiling_double_int(axisalignedbb1.maxY) + 1;
        int i1 = MathHelper.floor_double(axisalignedbb1.minZ) - 1;
        int j1 = MathHelper.ceiling_double_int(axisalignedbb1.maxZ) + 1;
        List<AxisAlignedBB> list = Lists.newArrayList();
        float f = 0.0F;
        int k1 = 0;

        for (int l1 = i; l1 < j; ++l1) {
            for (int i2 = i1; i2 < j1; ++i2) {
                int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);

                if (j2 != 2) {
                    for (int k2 = k; k2 < l; ++k2) {
                        if (j2 <= 0 || k2 != k && k2 != l - 1) {
                            Block iblockstate = this.worldObj.getBlock(l1, k2, i2);
                            //addCollisionBoxesToList    不知道是不是
                            if (iblockstate == null) {
                                //System.out.println("Block at (" + l1 + ", " + k1 + ", " + i2 + ") is null.");
                            } else {
                                iblockstate.addCollidingBoundsToList(this.worldObj, l1, k2, i2, axisalignedbb1, list, this);
                            }
                            if (!list.isEmpty()) {
                                f += iblockstate.slipperiness;
                                ++k1;
                            }

                            list.clear();
                        }
                    }
                }
            }
        }

        return f == 0 || k1 == 0 ? 0 : f / (float) k1;
    }

    private boolean checkInWater() {
        AxisAlignedBB axisalignedbb = this.boundingBox;
        int i = MathHelper.floor_double(axisalignedbb.minX);
        int j = MathHelper.ceiling_double_int(axisalignedbb.maxX);
        int k = MathHelper.floor_double(axisalignedbb.minY);
        int l = MathHelper.ceiling_double_int(axisalignedbb.minY + 0.001D);
        int i1 = MathHelper.floor_double(axisalignedbb.minZ);
        int j1 = MathHelper.ceiling_double_int(axisalignedbb.maxZ);
        boolean flag = false; //TODO: Cleanup
        this.waterLevel = Double.MIN_VALUE;

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    Block iblockstate = this.worldObj.getBlock(k1, l1, i2);
                    if (iblockstate != null) {
                        if (iblockstate.blockMaterial == Material.water) {
                            float f = getLiquidHeight(this.worldObj, k1, l1, i2);
                            this.waterLevel = Math.max(f, this.waterLevel);
                            flag |= axisalignedbb.minY < (double) f;
                            if (flag) return true;
                        }
                    }

                }
            }
        }

        return flag;
    }

    /**
     * Decides whether the boat is currently underwater.
     */
    private Status getUnderwaterStatus() {
        AxisAlignedBB axisalignedbb = this.boundingBox;
        double d0 = axisalignedbb.maxY + 0.001D;
        int i = MathHelper.floor_double(axisalignedbb.minX);
        int j = MathHelper.ceiling_double_int(axisalignedbb.maxX);
        int k = MathHelper.floor_double(axisalignedbb.maxY);
        int l = MathHelper.ceiling_double_int(d0);
        int i1 = MathHelper.floor_double(axisalignedbb.minZ);
        int j1 = MathHelper.ceiling_double_int(axisalignedbb.maxZ);
        boolean flag = false;

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    Block iblockstate = this.worldObj.getBlock(k1, l1, i2);
                    if (iblockstate != null) {
                        if (iblockstate.blockMaterial == Material.water && d0 < (double) getLiquidHeight(this.worldObj, k1, l1, i2)) {
                            if (worldObj.getBlockMetadata(k1, l1, i2) != 0) {
                                Status EntityNewBoat$status = Status.UNDER_FLOWING_WATER;
                                return EntityNewBoat$status;
                            }

                            flag = true;
                        }
                        //System.out.println("Block at (" + l1 + ", " + k1 + ", " + i2 + ") is null.");
                    }
                }
            }
        }

        return flag ? Status.UNDER_WATER : null;
    }

    public static float getLiquidHeightPercent(int p_149801_0_) {
        if (p_149801_0_ >= 8) {
            p_149801_0_ = 0;
        }

        return (float) (p_149801_0_ + 1) / 9.0F;
    }

    public static float getBlockLiquidHeight(World world, int x, int y, int z) {
        int i = world.getBlockMetadata(x, y, z);
        if (!world.isAirBlock(x, y + 1, z))
            return (i % 8) == 0 && world.getBlock(x, y + 1, z).blockMaterial == Material.water ? 1.0F : 1.0F - getLiquidHeightPercent(i);
        else
            return 1.0F - getLiquidHeightPercent(i);
    }

    public static float getLiquidHeight(World world, int x, int y, int z) {
        return (float) y + getBlockLiquidHeight(world, x, y, z);
    }

    /**
     * Update the boat's speed, based on momentum.
     */
    private void updateMotion() {
        double d0 = -0.03999999910593033D;
        double d1 = d0;
        double d2 = 0.0D;
        this.momentum = 0.05F;

        if (this.previousStatus == Status.IN_AIR && this.status != Status.IN_AIR && this.status != Status.ON_LAND) {
            this.waterLevel = this.getBoundingBox().minY + (double) this.height;
            this.setPosition(this.posX, (double) (this.getWaterLevelAbove() - this.height) + 0.101D, this.posZ);
            this.motionY = 0.0D;
            this.lastYd = 0.0D;
            this.status = Status.IN_WATER;
        } else {
            if (this.status == Status.IN_WATER) {
                d2 = (this.waterLevel - this.getBoundingBox().minY) / (double) this.height;
                this.momentum = 0.9F;
            } else if (this.status == Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.momentum = 0.9F;
            } else if (this.status == Status.UNDER_WATER) {
                d2 = 0.009999999776482582D;
                this.momentum = 0.45F;
            } else if (this.status == Status.IN_AIR) {
                this.momentum = 0.9F;
            } else if (this.status == Status.ON_LAND) {
                this.momentum = this.boatGlide;

                if (this.getControllingPassenger() instanceof EntityPlayer) {
                    this.boatGlide /= 2.0F;
                }
            }

            this.motionX *= this.momentum;
            this.motionZ *= this.momentum;
            this.deltaRotation *= this.momentum;
            this.motionY += d1;

            if (d2 > 0.0D) {
                double d3 = 0.65D;
                this.motionY += d2 * (-d0 / 0.65D);
                double d4 = 0.75D;
                this.motionY *= 0.75D;
            }
        }
    }

    private void controlBoat() {
        if (isBeingRidden()) {
            float f = 0.0F;

            if (this.leftInputDown) {
                this.deltaRotation += -1.0F;
            }

            if (this.rightInputDown) {
                ++this.deltaRotation;
            }

            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
                f += 0.005F;
            }

            this.rotationYaw += this.deltaRotation;

            if (this.forwardInputDown) {
                f += 0.04F;
            }

            if (this.backInputDown) {
                f -= 0.005F;
            }

            f *= 1.0f;//默认是 1f ConfigBlocksItems.newBoatSpeed

            this.motionX += MathHelper.sin(-this.rotationYaw * 0.017453292F) * f;
            this.motionZ += MathHelper.cos(this.rotationYaw * 0.017453292F) * f;
            if (!worldObj.isRemote)
                this.setPaddleState(this.rightInputDown || this.forwardInputDown, this.leftInputDown || this.forwardInputDown);
        }
    }

    @Override
    public void updateRiderPosition() {
        if (riddenByEntity != null)
            updatePassenger(riddenByEntity);
    }

    protected float getDefaultRiderOffset() {
        return 0.0f;
    }

    public void updatePassenger(Entity passenger) {
        float f = this.getDefaultRiderOffset();
        float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

        if (this.getPassengers().size() > 1) {
            int i = this.getPassengers().indexOf(passenger);

            if (i == 0) {
                f = 0.2F;
            } else {
                f = -0.6F;
            }

            if (passenger instanceof EntityAnimal) {
                f = (float) ((double) f + 0.2D);
            }
        }

        Vec3 vec3d = (Vec3.createVectorHelper(f, 0.0D, 0.0D));
        vec3d.rotateAroundY(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
        passenger.setPosition(this.posX + vec3d.xCoord, this.posY + (double) f1, this.posZ + vec3d.zCoord);
        passenger.rotationYaw += this.deltaRotation;
        this.applyYawToEntity(passenger);
    }

    /**
     * Applies this boat's yaw to the given entity. Used to update the orientation of its passenger.
     */
    protected void applyYawToEntity(Entity entityToUpdate) {
        int j = 0;
        if (entityToUpdate instanceof EntityAnimal && this.getPassengers().size() > 1) {
            j = entityToUpdate.entityId % 2 == 0 ? 90 : 270;
        }
        if (entityToUpdate instanceof EntityLivingBase) {
            float f = MathHelper.wrapAngleTo180_float(((EntityLivingBase) entityToUpdate).rotationYawHead - this.rotationYaw) + j;
            float f1 = MathHelper.clamp_float(f, -105.0F + j, 105.0F + j % 360);
            entityToUpdate.prevRotationYaw += f1 - f;
            entityToUpdate.rotationYaw += f1 - f;
            ((EntityLivingBase) entityToUpdate).rotationYawHead += f1 - f;
            ((EntityLivingBase) entityToUpdate).prevRotationYawHead += f1 - f;
            ((EntityLivingBase) entityToUpdate).renderYawOffset = this.rotationYaw + j;
            ((EntityLivingBase) entityToUpdate).prevRenderYawOffset = this.rotationYaw + j;
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

        if (compound.hasKey("Type")) {
            this.setBoatType(compound.getString("Type"));
        } else {
            this.setBoatType("oak");
        }
        if (compound.hasKey("Seat") && !worldObj.isRemote && shouldHaveSeat()) { //TODO add seat config
            Entity entity = EntityList.createEntityFromNBT(compound.getCompoundTag("Seat"), worldObj);
            if (entity instanceof EntityNewBoatSeat && entity.riddenByEntity == null) {
                ((EntityNewBoatSeat) entity).setBoat(this);
                seatToSpawn = ((EntityNewBoatSeat) entity);
            }
        }

        if (compound.hasKey("CustomName") && !compound.getString("CustomName").isEmpty()) {
            this.entityName = compound.getString("CustomName");
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setString("Type", getBoatType());

        if (hasSeat() && shouldHaveSeat()) {//&& ConfigBlocksItems.newBoatPassengerSeat
            String s = EntityList.getEntityString(seat);
            NBTTagCompound seatData = new NBTTagCompound();

            if (!seat.isDead && !this.isDead && s != null && seat.riddenByEntity == null) {
                seatData.setString("id", s);
                seat.writeToNBT(seatData);
                compound.setTag("Seat", seatData);
            }
        }

        if (this.entityName != null && this.entityName.length() > 0) {
            compound.setString("CustomName", this.entityName);
        }
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn) {
        this.lastYd = this.motionY;

        if (!this.isRiding()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != Status.ON_LAND && this.getControllingPassenger() instanceof EntityPlayer) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.fall(this.fallDistance);

                    if (!this.worldObj.isRemote && !this.isDead) {
                        this.setDead();

                        if (this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
                            for (int i = 0; i < 3; ++i) {
                                this.dropItemStack(getPlankToDrop(), 0.0F);
                            }

                            for (int j = 0; j < 2; ++j) {
                                this.dropItemStack(new ItemStack(Item.stick), 0.0F);
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            }
            if (y < 0.0D) {
                if (this.worldObj.isAirBlock((int) posX, (int) (posY - 1D), (int) posZ)) {
                    this.fallDistance = (float) ((double) this.fallDistance - y);
                } else if (this.worldObj.getBlock((int) posX, (int) (posY - 1D), (int) posZ).blockMaterial != Material.water && y < 0.0D) {
                    this.fallDistance = (float) ((double) this.fallDistance - y);
                }
            }
        }
    }

    public boolean getPaddleState(int p_184457_1_) {
        return dataWatcher.getWatchableObjectByte(DATA_ID_PADDLE[p_184457_1_]) == 1 && this.getControllingPassenger() != null;
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(float p_70266_1_) {
        this.dataWatcher.updateObject(DATA_ID_DAMAGE_TAKEN, p_70266_1_);
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamageTaken() {
        return this.dataWatcher.getWatchableObjectFloat(DATA_ID_DAMAGE_TAKEN);
    }

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int p_70265_1_) {
        this.dataWatcher.updateObject(DATA_ID_LAST_HIT, p_70265_1_);
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit() {
        return this.dataWatcher.getWatchableObjectInt(DATA_ID_LAST_HIT);
    }

    /**
     * Sets the forward direction of the entity.
     */
    public void setForwardDirection(int p_70269_1_) {
        this.dataWatcher.updateObject(DATA_ID_FORWARD, p_70269_1_);
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getForwardDirection() {
        return this.dataWatcher.getWatchableObjectInt(DATA_ID_FORWARD);
    }

//	public ItemStack getBoatDrop() {
//		ItemStack boatItem = boatInfo.getBoatItem();
//		if (boatItem.getItem() == Item.boat) {
//			boatItem.func_150996_a(ModItems.OAK_BOAT.get());
//		}
//
//		return boatItem;
//	}

/*	@Deprecated
	public Item getItemBoat() {
		return getBoatDrop().getItem();
	}*/

    public ItemStack getPlankToDrop() {
        return boatInfo.getPlank();
    }

    public boolean isRaft() {
        return getDataWatcher().getWatchableObjectByte(DATA_ID_RAFT) == 1;
    }

    public void setBoatType(String boatType) {
        getDataWatcher().updateObject(DATA_ID_TYPE, boatType);

        //ConfigFunctions.dropVehiclesTogether &&
        boatInfo = ItemNewBoat.BOAT_INFO.get(boatType + (this instanceof EntityNewBoatWithChest ? "_chest" : ""));
        if (boatInfo == null) {                                                //ConfigFunctions.dropVehiclesTogether &&
            boatInfo = ItemNewBoat.BOAT_INFO.get("oak" + (this instanceof EntityNewBoatWithChest ? "_chest" : ""));
            getDataWatcher().updateObject(DATA_ID_TYPE, "oak");
        }
        getDataWatcher().updateObject(DATA_ID_RAFT, boatInfo.isRaft() ? (byte) 1 : (byte) 0);
        getDataWatcher().updateObject(DATA_ID_RESOURCELOCATION, "futuremite:textures/entity/boat/" + getBoatType() + ".png");
    }

    public String getBoatType() {
        return getDataWatcher().getWatchableObjectString(DATA_ID_TYPE);
    }

    @Deprecated
    public void setBoatType(Type boatType) {
        setBoatType(boatType.name);
    }

    public void updateInputs(boolean p_184442_1_, boolean p_184442_2_, boolean p_184442_3_, boolean p_184442_4_) {
        this.leftInputDown = p_184442_1_;
        this.rightInputDown = p_184442_2_;
        this.forwardInputDown = p_184442_3_;
        this.backInputDown = p_184442_4_;
    }

    public String getBoatName() {
        return entityName;
    }

    public void setBoatName(String p_96094_1_) {
        this.entityName = p_96094_1_;
    }

    /**
     * Provided through the entity as a way for modders to be able to easily make modded boats withing needing a base class to attach a renderer to
     *
     * @return
     */
    public ResourceLocation getResourceLocation() {
        if (resourceLocation == null) {
            resourceLocation = new ResourceLocation(getDataWatcher().getWatchableObjectString(DATA_ID_RESOURCELOCATION));
        }
        return resourceLocation;
    }

    public enum Status {
        IN_WATER,
        UNDER_WATER,
        UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR
    }

    @Deprecated
    public enum Type {
        OAK(0, "oak"),
        SPRUCE(1, "spruce"),
        BIRCH(2, "birch"),
        JUNGLE(3, "jungle"),
        ACACIA(4, "acacia"),
        DARK_OAK(5, "dark_oak"),
        CHERRY(6, "cherry");
        public static final Type[] VALUES = values();

        private final String name;
        private final int metadata;

        Type(int metadataIn, String nameIn) {
            this.name = nameIn;
            this.metadata = metadataIn;
        }

        public String getName() {
            return this.name;
        }

        public int getMetadata() {
            return this.metadata;
        }

        public String toString() {
            return this.name;
        }

        public static Type byId(int id) {
            return VALUES[MathHelper.clamp_int(id, 0, VALUES.length - 1)];
        }

        public static Type getTypeFromString(String nameIn) {
            for (Type value : VALUES) {
                if (value.getName().equals(nameIn)) {
                    return value;
                }
            }

            return VALUES[0];
        }
    }
}
