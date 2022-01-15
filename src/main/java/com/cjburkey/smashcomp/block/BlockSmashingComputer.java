package com.cjburkey.smashcomp.block;

import com.cjburkey.smashcomp.CJsSmashComps;
import com.cjburkey.smashcomp.SmashCompsWorldData;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockSmashingComputer extends Block implements BlockEntityProvider {

    public BlockSmashingComputer() {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Only run on the server side
        if (!world.isClient) {
            // Get access to the block entity for this computer.
            var blockEntity = (BlockSmashingComputerEntity) world.getBlockEntity(pos);
            if (blockEntity != null) {  // Error check :/
                // Send
                player.sendMessage(new TranslatableText("debug.cjs-smash-comp.debug_print", blockEntity.getComputerId()), false);
            } else {
                CJsSmashComps.LOGGER.warn("Computer at {} has no block entity.", pos);
            }
        }

        // Let the game know this action was performed (on the client and the server).
        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        // On run on server side
        if (!world.isClient
                && world instanceof ServerWorld serverWorld
                && world.getBlockEntity(pos) instanceof BlockSmashingComputerEntity blockEntity) {
            blockEntity.setComputerId(SmashCompsWorldData.getFromWorld(serverWorld).getNextId());
            CJsSmashComps.LOGGER.info("Created new computer with ID {}", blockEntity.getComputerId());
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockSmashingComputerEntity(pos, state);
    }

}
