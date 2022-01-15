package com.cjburkey.smashcomp.block;

import com.cjburkey.smashcomp.CJsSmashComps;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class BlockSmashingComputerEntity extends BlockEntity {

    public static final String COMPUTER_ID_TAG = "computer_id";

    // Per-computer data
    private int computerId = -1;

    public BlockSmashingComputerEntity(BlockPos pos, BlockState state) {
        super(CJsSmashComps.SMASHING_COMP_ENT, pos, state);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        computerId = tag.getInt(COMPUTER_ID_TAG);
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        tag.putInt(COMPUTER_ID_TAG, computerId);
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
        markDirty();
    }

    public int getComputerId() {
        return computerId;
    }

}
