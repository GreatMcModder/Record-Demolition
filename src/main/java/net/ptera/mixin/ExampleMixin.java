package net.ptera.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.ptera.MostIronGolems.entity.IronBuilderEntity;
import net.ptera.MostIronGolems.util.EvolutionAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolemEntity.class)
public class ExampleMixin extends GolemEntity implements EvolutionAccess {
	private static final TrackedData<Integer> EVOLUTION_NUMBER = DataTracker.registerData(IronGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);

	protected ExampleMixin(EntityType<? extends GolemEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(at = @At("TAIL"), method = "initDataTracker")
	private void init(CallbackInfo info) {
		this.dataTracker.startTracking(EVOLUTION_NUMBER,0);
	}


	@Override
	public int evolution() {
		return this.dataTracker.get(EVOLUTION_NUMBER);
	}

	@Override
	public void setEvolution(int evolution) {
		this.dataTracker.set(EVOLUTION_NUMBER,evolution);
	}

	@Inject(at=@At("HEAD"),method = "readCustomDataFromNbt")
	public void readData(NbtCompound compound, CallbackInfo ci) {
		this.dataTracker.set(EVOLUTION_NUMBER,compound.getInt("Evol"));
	}

	@Inject(at=@At("HEAD"),method = "onDeath")
	public void dead(DamageSource damageSource, CallbackInfo ci) {
		loop(this.dataTracker.get(EVOLUTION_NUMBER));
	}

	public void loop(float depth) {
		if (depth!=0) {
			float currentDepth = depth-1;
			for (int i=0;i<10;i++) {
				if (this.getWorld() instanceof ServerWorld serverWorld) {
					IronGolemEntity golemEntity = EntityType.IRON_GOLEM.create(serverWorld);
					if (golemEntity != null) {
						golemEntity.refreshPositionAndAngles(this.getBlockPos(),0,0);
						((EvolutionAccess) golemEntity).setEvolution(((EvolutionAccess)(IronGolemEntity)(Object)this).evolution()-1);
						serverWorld.spawnEntityAndPassengers(golemEntity);
					}
				}
				loop(currentDepth);
			}
		}
	}

	@Inject(at=@At("HEAD"),method = "writeCustomDataToNbt")
	public void writeData(NbtCompound compound, CallbackInfo ci) {
		compound.putInt("Evol",this.dataTracker.get(EVOLUTION_NUMBER));
	}
}