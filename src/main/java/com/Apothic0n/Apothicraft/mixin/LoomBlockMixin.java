package com.Apothic0n.Apothicraft.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LoomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.Apothic0n.Apothicraft.core.ApothicraftMath.getOffsetDouble;

@Mixin(LoomBlock.class)
public class LoomBlockMixin {
    
    /**
     * @author Apothicon
     * @reason Allows the crafting of string when interacting with the loom while holding a hay block. 
     */
    @Overwrite
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult block) {
        boolean wasSuitableItemInHand = false;
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.is(Items.HAY_BLOCK)) {
            wasSuitableItemInHand = true;
            itemInHand.setCount(itemInHand.getCount()-1);
            ItemStack itemStack = new ItemStack(Items.STRING);
            ItemEntity itemEntity = new ItemEntity(level,
                    getOffsetDouble(pos.getX(), player.getEyePosition().x),
                    getOffsetDouble(pos.getY(), player.getEyePosition().y),
                    getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                    itemStack);
            level.addFreshEntity(itemEntity);
            level.playSound(player, pos, SoundEvents.VILLAGER_WORK_SHEPHERD, SoundSource.BLOCKS);
        } else if (itemInHand.is(Items.ROTTEN_FLESH)) {
            wasSuitableItemInHand = true;
            itemInHand.setCount(itemInHand.getCount()-1);
            ItemStack itemStack = new ItemStack(Items.LEATHER);
            ItemEntity itemEntity = new ItemEntity(level,
                    getOffsetDouble(pos.getX(), player.getEyePosition().x),
                    getOffsetDouble(pos.getY(), player.getEyePosition().y),
                    getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                    itemStack);
            level.addFreshEntity(itemEntity);
            level.playSound(player, pos, SoundEvents.VILLAGER_WORK_SHEPHERD, SoundSource.BLOCKS);
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            if (!wasSuitableItemInHand) {
                player.openMenu(blockState.getMenuProvider(level, pos));
            }
            player.awardStat(Stats.INTERACT_WITH_LOOM);
            return InteractionResult.CONSUME;
        }
    }
}