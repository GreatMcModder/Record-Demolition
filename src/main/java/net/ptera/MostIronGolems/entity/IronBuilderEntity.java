package net.ptera.MostIronGolems.entity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IronBuilderEntity extends PathAwareEntity {
    public AnimationState build = new AnimationState();
    public static final TrackedData<Integer> BUILD_TIME = DataTracker.registerData(IronBuilderEntity.class, TrackedDataHandlerRegistry.INTEGER);
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
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.age=nbt.getInt("Age");
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
    }

    @Override
    public void tick() {
        super.tick();
        this.setPosition(0,150,0);
    }

    public void buildGolem(BlockPos pos) {
        if (!this.getWorld().isClient) {
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
