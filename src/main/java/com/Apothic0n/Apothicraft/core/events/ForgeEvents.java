package com.Apothic0n.Apothicraft.core.events;

import com.Apothic0n.Apothicraft.Apothicraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static com.Apothic0n.Apothicraft.core.ApothicraftMath.getOffsetDouble;

@Mod.EventBusSubscriber(modid = Apothicraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    static void lootTableLoadEvent(LootTableLoadEvent event) {
        if (!event.getName().getNamespace().equals("apothicraft")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    static void playerInteractEvent(PlayerInteractEvent event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        InteractionHand hand = player.getUsedItemHand();
        BlockPos pos = event.getPos();
        if (!level.getBlockState(pos).isSolid()) {
            List<Entity> entities = level.getEntities((Entity) null, new AABB(pos), EntitySelector.NO_SPECTATORS);
            for (Entity entity : entities) {
                if (entity.getType().equals(EntityType.CHICKEN)) {
                    player.swing(hand);
                    if (Math.random() * 33 <= 1) {
                        ItemStack itemStack = new ItemStack(Items.FEATHER);
                        ItemEntity itemEntity = new ItemEntity(level,
                                getOffsetDouble(pos.getX(), player.getEyePosition().x),
                                getOffsetDouble(pos.getY(), player.getEyePosition().y),
                                getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                                itemStack);
                        level.addFreshEntity(itemEntity);
                    }
                    level.playSound(player, pos, SoundEvents.WOOL_STEP, SoundSource.PLAYERS);
                    level.addParticle(ParticleTypes.SNOWFLAKE, false, entity.position().x, entity.position().y + 0.4, entity.position().z, Math.random() - 0.5, -0.5D, Math.random() - 0.5);
                }
            }
        } else if (player.getItemInHand(hand).is(Items.STICK) && (level.getBlockState(pos).is(Tags.Blocks.STONE) || level.getBlockState(pos).is(Tags.Blocks.COBBLESTONE) || level.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD))) {
            player.swing(hand);
            if (Math.random() * 33 <= 1) {
                ItemStack itemStack = new ItemStack(Items.FLINT);
                ItemEntity itemEntity = new ItemEntity(level,
                        getOffsetDouble(pos.getX(), player.getEyePosition().x),
                        getOffsetDouble(pos.getY(), player.getEyePosition().y),
                        getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                        itemStack);
                level.addFreshEntity(itemEntity);
            }
            level.playSound(player, pos, SoundEvents.GRINDSTONE_USE, SoundSource.PLAYERS);
            level.addParticle(ParticleTypes.SWEEP_ATTACK, false,
                    getOffsetDouble(pos.getX(), player.getEyePosition().x),
                    getOffsetDouble(pos.getY(), player.getEyePosition().y),
                    getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                    0.0D, -0.25D, 0.0D);
        } else if (player.getItemInHand(hand).is(Items.STONE_AXE) && level.getBlockState(pos).is(BlockTags.LOGS) && !level.getBlockState(pos).getBlock().getName().toString().contains("stripped")) {
            ItemStack itemStack = new ItemStack(Items.STICK);
            ItemEntity itemEntity = new ItemEntity(level,
                    getOffsetDouble(pos.getX(), player.getEyePosition().x),
                    getOffsetDouble(pos.getY(), player.getEyePosition().y),
                    getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                    itemStack);
            level.addFreshEntity(itemEntity);
            level.addParticle(ParticleTypes.SWEEP_ATTACK, false,
                    getOffsetDouble(pos.getX(), player.getEyePosition().x),
                    getOffsetDouble(pos.getY(), player.getEyePosition().y),
                    getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                    0.0D, -0.25D, 0.0D);
        }
    }
}
