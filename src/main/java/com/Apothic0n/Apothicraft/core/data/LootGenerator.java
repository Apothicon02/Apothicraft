package com.Apothic0n.Apothicraft.core.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class LootGenerator extends LootTableProvider {
    public LootGenerator(PackOutput output) {
        super(output, BlockLootTables.allBuiltIn(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
