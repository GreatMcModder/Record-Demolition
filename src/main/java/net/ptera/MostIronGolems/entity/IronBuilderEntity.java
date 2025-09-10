package net.ptera.MostIronGolems.entity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IronBuilderEntity extends PathAwareEntity {
    public AnimationState build = new AnimationState();
    public AnimationState walk = new AnimationState();
    public static final TrackedData<Integer> BUILD_TIME = DataTracker.registerData(IronBuilderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> START_X = DataTracker.registerData(IronBuilderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> START_Y = DataTracker.registerData(IronBuilderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> START_Z = DataTracker.registerData(IronBuilderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public IronBuilderEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer registerAttributes() {
        return createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,15).add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.3F).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,3).build();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BUILD_TIME,0);
        this.dataTracker.startTracking(START_X,0);
        this.dataTracker.startTracking(START_Y,0);
        this.dataTracker.startTracking(START_Z,0);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.age=nbt.getInt("Age");
        this.dataTracker.set(START_X, nbt.getInt("StartX"));
        this.dataTracker.set(START_Y, nbt.getInt("StartY"));
        this.dataTracker.set(START_Z, nbt.getInt("StartZ"));
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0,new WanderAroundFarGoal(this,1D));
        super.initGoals();
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Age",this.age);
        nbt.putInt("StartX",this.dataTracker.get(START_X));
        nbt.putInt("StartY",this.dataTracker.get(START_Y));
        nbt.putInt("StartZ",this.dataTracker.get(START_Z));
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return true;
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.dataTracker.set(START_X,(int)this.getX());
        this.dataTracker.set(START_Y,(int)this.getY());
        this.dataTracker.set(START_Z,(int)this.getZ());
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void tick() {
        super.tick();
        this.clearVoid();
        this.setPosition(this.getX(),this.dataTracker.get(START_Y),this.getZ());
        this.setYaw(90);
        if (this.age % 5 == 0 && !this.isAiDisabled() && this.age<=1200) {
            walk.stop();
            walk.setRunning(false,this.age);
            this.buildGolem(this.getBlockPos().up(7));
            this.setVelocity(3, 0,0);
            walk.startIfNotRunning(this.age);
            walk.setRunning(true,this.age);
        }
        if (this.age%5==1) {
            this.setVelocity(Vec3d.ZERO);
        }
    }

    public void clearVoid() {
        for (int x=-3; x<=3;x++) {
            for (int z=-3; z<=3;z++) {
                for (int y = 0; y <= 3; y++) {
                    this.getWorld().setBlockState(this.getBlockPos().add(x, y, z), Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    public void buildGolem(BlockPos pos) {
        if (!this.getWorld().isClient) {
            for (int x=-1; x<=1;x++) {
                for (int z=-1; z<=1;z++) {
                    this.getWorld().setBlockState(pos.add(x,-1,z),Blocks.IRON_BLOCK.getDefaultState());
                }
            }
            this.getWorld().setBlockState(pos.east(), Blocks.AIR.getDefaultState());
            this.getWorld().setBlockState(pos.west(), Blocks.AIR.getDefaultState());
            this.getWorld().setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState());
            this.getWorld().setBlockState(pos.up(), Blocks.IRON_BLOCK.getDefaultState());
            this.getWorld().setBlockState(pos.up().east(), Blocks.IRON_BLOCK.getDefaultState());
            this.getWorld().setBlockState(pos.up().west(), Blocks.IRON_BLOCK.getDefaultState());
            this.getWorld().setBlockState(pos.up(2), Blocks.CARVED_PUMPKIN.getDefaultState(), Block.NOTIFY_ALL);
        }
    }
}
