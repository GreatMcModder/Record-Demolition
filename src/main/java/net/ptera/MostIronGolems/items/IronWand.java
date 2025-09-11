package net.ptera.MostIronGolems.items;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.ptera.MostIronGolems.entity.IronBuilderEntity;
import net.ptera.MostIronGolems.registries.ModEntities;
import net.ptera.MostIronGolems.registries.ModPersistentState;

public class IronWand extends Item {
    public IronWand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbtComponent = stack.getOrCreateNbt();
        if (user.isSneaking()) {
            if (nbtComponent.getDouble("Mode") == 1) {
                nbtComponent.putDouble("Mode", 0);
            } else {
                nbtComponent.putDouble("Mode", nbtComponent.getDouble("Mode") + 1);
            }
            stack.setNbt(nbtComponent);
        } else {
            if (nbtComponent.getDouble("Mode")==0) {
                if (world instanceof ServerWorld serverWorld) {
                    IronBuilderEntity builder = ModEntities.IRON_BUILDER.create(serverWorld);
                    if (builder!=null) {
                        builder.refreshPositionAndAngles(user.getBlockPos(),0,0);
                        builder.initialize(serverWorld,world.getLocalDifficulty(user.getBlockPos()), SpawnReason.EVENT,null,null);
                        serverWorld.spawnEntityAndPassengers(builder);
                    }
                }
            } else {
                if (world instanceof ServerWorld serverWorld) {
                    ModPersistentState state = ModPersistentState.getState(serverWorld);
                    user.sendMessage(Text.of("10^" + (state.currentEvolution * (state.currentEvolution - 1))));
                }
            }
        }
        return super.use(world, user, hand);
    }
}
