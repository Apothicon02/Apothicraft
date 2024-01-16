package com.Apothic0n.Apothicraft.core.data;

import com.Apothic0n.Apothicraft.Apothicraft;
import com.google.common.collect.Sets;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    private static final Set<ResourceLocation> LOOT_TABLES = Sets.newHashSet();
    private static Set<Block> BLOCKS = Sets.newHashSet();

    public BlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    protected static LootTable.Builder createAxeOnlyTable(ItemLike itemLike) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(itemLike)));
    }

    protected void selfWhenAxe(Block block, ResourceLocation id) {
        LootTable.Builder lootBuilder = createAxeOnlyTable(block);
        this.add(block, lootBuilder);
    }

    @Override
    public void generate() {
        for (String name : Apothicraft.woodBlocks) {
            ResourceLocation lootTableID = new ResourceLocation("blocks/"+name.substring(17));
            ResourceLocation blockID = new ResourceLocation(name.substring(17));
            Block block = ForgeRegistries.BLOCKS.getValue(blockID);
            if (block != null && block != Blocks.AIR) {
                if (name.contains("sapling")) {
                    dropSelf(block);
                } else {
                    selfWhenAxe(block, lootTableID);
                }
                LOOT_TABLES.add(lootTableID);
                BLOCKS.add(block);
            }
        }
    }

    public static Set<ResourceLocation> allBuiltIn() {
        return Collections.unmodifiableSet(LOOT_TABLES);
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return BLOCKS;
    }
}
