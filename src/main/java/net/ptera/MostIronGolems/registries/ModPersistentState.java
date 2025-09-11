package net.ptera.MostIronGolems.registries;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class ModPersistentState extends PersistentState {
    public int currentEvolution = 1;
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("Evol",currentEvolution);
        return nbt;
    }
    public static ModPersistentState fromNbt(NbtCompound nbt) {
        ModPersistentState state = new ModPersistentState();
        state.currentEvolution=nbt.getInt("Evol");
        return state;
    }

    public static ModPersistentState getState(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(ModPersistentState::fromNbt, ModPersistentState::new,"iron_state");
    }
}
