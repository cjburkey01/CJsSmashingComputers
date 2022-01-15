package com.cjburkey.smashcomp;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import org.jetbrains.annotations.NotNull;

public class SmashCompsWorldData extends PersistentState {

    public static final String STORE_NAME = "cjs-smash-comp";
    public static final String NEXT_ID_NAME = "next-id";
    private int nextId = 0;

    // Get the next computer's ID and increments the internal counter
    public int getNextId() {
        markDirty();
        return nextId++;
    }

    // Only peek at the next computer's ID (without incrementing)
    public int peekNextId() {
        return nextId;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // Store the next ID
        nbt.putInt(NEXT_ID_NAME, nextId);

        return nbt;
    }

    public static SmashCompsWorldData readNbt(NbtCompound nbt) {
        // Create an empty storage object
        var newStore = new SmashCompsWorldData();

        // Read the next ID
        if (nbt.contains(NEXT_ID_NAME)) {
            newStore.nextId = nbt.getInt(NEXT_ID_NAME);
        }

        // Return the loaded storage instance
        return newStore;
    }

    public static @NotNull SmashCompsWorldData getFromWorld(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(SmashCompsWorldData::readNbt, SmashCompsWorldData::new, STORE_NAME);
    }

}
