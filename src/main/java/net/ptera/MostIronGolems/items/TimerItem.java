package net.ptera.MostIronGolems.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TimerItem extends Item {
    public TimerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbtComponent = stack.getOrCreateNbt();
        if (user.isSneaking()) {
            nbtComponent.putBoolean("On", !nbtComponent.getBoolean("On"));
        } else {
            nbtComponent.putInt("Time", 1200);
            nbtComponent.putBoolean("On", false);
        }
        stack.setNbt(nbtComponent);
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        NbtCompound nbtComponent = stack.getOrCreateNbt();
        if (entity instanceof PlayerEntity player) {
            player.sendMessage(Text.of(Integer.toString((int) Math.floor((double) nbtComponent.getInt("Time") /20))),true);
        }
        if (nbtComponent.getBoolean("On")) {
            nbtComponent.putInt("Time", nbtComponent.getInt("Time") - 1);
        }
        stack.setNbt(nbtComponent);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
