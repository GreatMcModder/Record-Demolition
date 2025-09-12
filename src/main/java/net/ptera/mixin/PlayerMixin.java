package net.ptera.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ptera.MostIronGolems.registries.ModItems;
import net.ptera.MostIronGolems.util.PlayerAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerMixin extends PlayerEntity implements PlayerAccess {

    public PlayerMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(at = @At("TAIL"), method = "swingHand")
    private void init(Hand hand, CallbackInfo info) {
        if (this.getStackInHand(hand).isOf(ModItems.IRON_WAND)) {
            ItemStack stack = this.getStackInHand(hand);
            NbtCompound nbtCompound = stack.getOrCreateNbt();
            if (nbtCompound.getDouble("Mode") == 1) {
                nbtCompound.putDouble("Mode", 0);
            } else {
                nbtCompound.putDouble("Mode", nbtCompound.getDouble("Mode") + 1);
            }
            stack.setNbt(nbtCompound);
        }
    }
}
