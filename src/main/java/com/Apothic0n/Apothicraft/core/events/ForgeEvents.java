package com.Apothic0n.Apothicraft.core.events;

import com.Apothic0n.Apothicraft.Apothicraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

import static com.Apothic0n.Apothicraft.core.ApothicraftMath.getOffsetDouble;

@Mod.EventBusSubscriber(modid = Apothicraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    public static Set<String> lootTableWhitelist = Set.of(
            "gameplay/fishing", "gameplay/fishing/fish", "gameplay/fishing/junk", "gameplay/fishing/treasure",

            "entities/allay", "entities/armor_stand", "entities/axolotl", "entities/bat", "entities/bee", "entities/blaze", "entities/camel", "entities/cat", "entities/cave_spider", "entities/chicken", "entities/cod", "entities/cow",
            "entities/creeper", "entities/dolphin", "entities/donkey", "entities/drowned", "entities/elder_guardian", "entities/ender_dragon", "entities/enderman", "entities/endermite", "entities/evoker", "entities/fox", "entities/frog",
            "entities/ghast", "entities/giant", "entities/glow_squid", "entities/goat", "entities/guardian", "entities/hoglin", "entities/horse", "entities/husk", "entities/illusioner", "entities/iron_golem", "entities/llama",
            "entities/magma_cube", "entities/mooshrom", "entities/mule", "entities/ocelot", "entities/panda", "entities/parrot", "entities/phantom", "entities/pig", "entities/piglin", "entities/piglin_brute", "entities/pillager",
            "entities/player", "entities/polar_bear", "entities/pufferfish", "entities/rabbit", "entities/ravager", "entities/salmon", "entities/sheep", "entities/shulker", "entities/silverfish", "entities/skeleton", "entities/skeleton_horse",
            "entities/slime", "entities/sniffer", "entities/snow_golem", "entities/spider", "entities/squid", "entities/stray", "entities/strider", "entities/tadpole", "entities/trader_llama", "entities/tropical_fish", "entities/turtle",
            "entities/vex", "entities/villager", "entities/vindicator", "entities/wandering_trader", "entities/warden", "entities/witch", "entities/wither", "entities/wither_skeleton", "entities/wolf", "entities/zoglin", "entities/zombie",
            "entities/zombie_horse", "entities/zombie_villager", "entities/zombified_piglin",

            "entities/sheep/black", "entities/sheep/blue", "entities/sheep/brown", "entities/sheep/cyan", "entities/sheep/gray", "entities/sheep/green", "entities/sheep/light_blue", "entities/sheep/light_gray", "entities/sheep/lime",
            "entities/sheep/magenta", "entities/sheep/orange", "entities/sheep/pink", "entities/sheep/purple", "entities/sheep/red", "entities/sheep/white", "entities/sheep/yellow",

            "blocks/campfire", "blocks/soul_campfire", "blocks/torch", "blocks/soul_torch",
            "blocks/dirt", "blocks/coarse_dirt", "blocks/dirt_path", "blocks/grass_block", "blocks/podzol", "blocks/rooted_dirt",
            "blocks/packed_mud", "blocks/mud_bricks", "blocks/mud_brick_wall", "blocks/mud_brick_stairs", "blocks/mud_brick_slab",
            "blocks/mangrove_propagule", "blocks/flowering_azalea_leaves", "blocks/azalea_leaves", "blocks/azalea", "blocks/flowering_azalea",
            "blocks/moss_block", "blocks/moss_carpet", "blocks/small_dripleaf", "blocks/big_dripleaf", "blocks/big_dripleaf_stem",
            "blocks/fern", "blocks/large_fern", "blocks/grass", "blocks/tall_grass", "blocks/dandelion", "blocks/poppy", "blocks/blue_orchid", "blocks/allium", "blocks/azure_bluet", "blocks/red_tulip", "blocks/orange_tulip", "blocks/white_tulip",
            "blocks/pink_tulip", "blocks/oxeye_daisy", "blocks/cornflower", "blocks/lily_of_the_valley", "blocks/wither_rose", "blocks/torchflower", "blocks/sunflower", "blocks/lilac", "blocks/rose_bush", "blocks/peony", "blocks/pitcher_plant",
            "blocks/pitcher_crop", "blocks/torchflower_crop", "blocks/wheat", "blocks/carrots", "blocks/potatoes", "blocks/beetroots", "blocks/melon", "blocks/melon_stem", "blocks/pumpkin", "blocks/carved_pumpkin", "blocks/pumpkin_stem",
            "blocks/jack_o_lantern", "blocks/bamboo", "blocks/bamboo_sapling", "blocks/sugar_cane", "blocks/cocoa", "blocks/sweet_berry_bush", "blocks/cactus", "blocks/red_mushroom", "blocks/brown_mushroom", "blocks/kelp", "blocks/sea_pickle",
            "blocks/nether_wart", "blocks/chorus_flower", "blocks/chorus_plant", "blocks/cave_vines", "blocks/cave_vines_plant", "blocks/warped_fungus", "blocks/crimson_fungus", "blocks/dead_bush",
            "blocks/hay_block"
    );
    @SubscribeEvent
    static void lootTableLoadEvent(LootTableLoadEvent event) {
        if (!(lootTableWhitelist.contains(event.getName().getPath()) || Apothicraft.woodBlocks.contains(event.getName().toString())) && (event.getName().getNamespace().equals("minecraft") || event.getName().getNamespace().equals("create"))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    static void playerJoined(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.PLAYER) && !event.getLevel().isClientSide) {
            ServerPlayer player = (ServerPlayer) entity;
            player.awardRecipes(event.getLevel().getRecipeManager().getRecipes());
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
                    level.playSound(player, pos, SoundEvents.WOOL_STEP, SoundSource.NEUTRAL);
                    level.addParticle(ParticleTypes.SNOWFLAKE, false, entity.position().x, entity.position().y + 0.4, entity.position().z, Math.random() - 0.5, -0.5D, Math.random() - 0.5);
                }
            }
        } else {
            ItemStack itemInHand = player.getItemInHand(hand);
            BlockState blockState = level.getBlockState(pos);
            if (itemInHand.is(Items.STICK)) {
                if (blockState.is(Tags.Blocks.STONE) || blockState.is(Tags.Blocks.COBBLESTONE) || blockState.is(BlockTags.BASE_STONE_OVERWORLD)) { //gather flint with stick on stone
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
                    level.playSound(player, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                    level.addParticle(ParticleTypes.SWEEP_ATTACK, false,
                            getOffsetDouble(pos.getX(), player.getEyePosition().x),
                            getOffsetDouble(pos.getY(), player.getEyePosition().y),
                            getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                            0.0D, -0.25D, 0.0D);
                } else if (blockState.is(Blocks.CAMPFIRE) || blockState.is(Blocks.FIRE)) { //convert stick to torch on fire
                    player.swing(hand);
                    ItemStack itemStack = new ItemStack(Items.TORCH);
                    itemStack.setCount(itemInHand.getCount());
                    player.setItemInHand(hand, itemStack);
                    level.playSound(player, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
                    level.addParticle(ParticleTypes.FLAME, false,
                            getOffsetDouble(pos.getX(), player.getEyePosition().x),
                            getOffsetDouble(pos.getY(), player.getEyePosition().y),
                            getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                            0.0D, 0.45D, 0.0D);
                } else if (blockState.is(Blocks.SOUL_CAMPFIRE) || blockState.is(Blocks.SOUL_FIRE)) { //convert stick to soul torch on soul fire
                    player.swing(hand);
                    ItemStack itemStack = new ItemStack(Items.SOUL_TORCH);
                    itemStack.setCount(itemInHand.getCount());
                    player.setItemInHand(hand, itemStack);
                    level.playSound(player, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
                    level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, false,
                            getOffsetDouble(pos.getX(), player.getEyePosition().x),
                            getOffsetDouble(pos.getY(), player.getEyePosition().y),
                            getOffsetDouble(pos.getZ(), player.getEyePosition().z),
                            0.0D, 0.45D, 0.0D);
                }
            }
        }
    }
}
