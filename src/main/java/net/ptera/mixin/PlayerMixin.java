package net.ptera.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.ptera.MostIronGolems.util.PlayerAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerAccess {
    private static final TrackedData<Integer> IRON_GOLEMS_BUILT = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(at = @At("TAIL"), method = "initDataTracker")
    private void init(CallbackInfo info) {
        this.dataTracker.startTracking(IRON_GOLEMS_BUILT,0);
    }

    @Override
    public int golemsBuilt() {
        return this.dataTracker.get(IRON_GOLEMS_BUILT);
    }

    @Override
    public void setGolemsBuilt(int i) {
        this.dataTracker.set(IRON_GOLEMS_BUILT,i);
    }
}
