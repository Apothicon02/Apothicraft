package com.Apothic0n.Apothicraft.core.events;

import com.Apothic0n.Apothicraft.Apothicraft;
import com.Apothic0n.Apothicraft.core.data.LootGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Apothicraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();

        generator.addProvider(event.includeServer(), new LootGenerator(output));
    }
}
