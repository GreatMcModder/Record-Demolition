package net.ptera.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ptera.MostIronGolems.registries.ModPersistentState;
import net.ptera.MostIronGolems.util.EvolutionAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinMixin {
    @Shadow
    @Nullable
    private BlockPattern snowGolemPattern;
    @Shadow
    @Nullable
    private BlockPattern ironGolemPattern;
    private static final Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE = (state) -> state != null && (state.isOf(Blocks.CARVED_PUMPKIN) || state.isOf(Blocks.JACK_O_LANTERN));;
    private BlockPattern getSnowGolemPatter() {
        if (this.snowGolemPattern == null) {
            this.snowGolemPattern = BlockPatternBuilder.start().aisle(new String[]{"^", "#", "#"}).where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }

        return this.snowGolemPattern;
    }
    private BlockPattern getIronGolemPatter() {
        if (this.ironGolemPattern == null) {
            this.ironGolemPattern = BlockPatternBuilder.start().aisle(new String[]{"~^~", "###", "~#~"}).where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', (pos) -> pos.getBlockState().isAir()).build();
        }

        return this.ironGolemPattern;
    }

    @Inject(at = @At("HEAD"),method = "trySpawnEntity",cancellable = true)
    private void trySpawnEntity(World world, BlockPos pos, CallbackInfo ci) {
        ci.cancel();
        BlockPattern.Result result = this.getSnowGolemPatter().searchAround(world, pos);
        if (result != null) {
            SnowGolemEntity snowGolemEntity = (SnowGolemEntity) EntityType.SNOW_GOLEM.create(world);
            if (snowGolemEntity != null) {
                spawEntity(world, result, snowGolemEntity, result.translate(0, 2, 0).getBlockPos());
            }
        } else {
            BlockPattern.Result result2 = this.getIronGolemPatter().searchAround(world, pos);
            if (result2 != null) {
                if (world instanceof ServerWorld world1) {
                    ModPersistentState state = ModPersistentState.getState(world1);
                    IronGolemEntity ironGolemEntity = (IronGolemEntity) EntityType.IRON_GOLEM.create(world);
                    EvolutionAccess ironGolemEvolution = (EvolutionAccess) ironGolemEntity;
                    if (ironGolemEntity != null) {
                        ironGolemEvolution.setEvolution(state.currentEvolution);
                        ironGolemEntity.setPlayerCreated(true);
                        spawEntity(world, result2, ironGolemEntity, result2.translate(1, 2, 0).getBlockPos());
                    }
                    state.currentEvolution+=1;
                    state.markDirty();
                }
            }
        }
    }
    private static void spawEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
        System.out.println("Spawned Entity");
        breakPatternBlock(world, patternResult);
        entity.refreshPositionAndAngles((double)pos.getX() + (double)0.5F, (double)pos.getY() + 0.05, (double)pos.getZ() + (double)0.5F, 0.0F, 0.0F);
        world.spawnEntity(entity);

        for(ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand((double)5.0F))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
        }

        updatePatternBlock(world, patternResult);
    }
    private static void breakPatternBlock(World world, BlockPattern.Result patternResult) {
        for(int i = 0; i < patternResult.getWidth(); ++i) {
            for(int j = 0; j < patternResult.getHeight(); ++j) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
                world.syncWorldEvent(2001, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
            }
        }

    }

    private static void updatePatternBlock(World world, BlockPattern.Result patternResult) {
        for(int i = 0; i < patternResult.getWidth(); ++i) {
            for(int j = 0; j < patternResult.getHeight(); ++j) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.updateNeighbors(cachedBlockPosition.getBlockPos(), Blocks.AIR);
            }
        }

    }
}
