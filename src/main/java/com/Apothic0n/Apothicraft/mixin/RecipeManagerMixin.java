package com.Apothic0n.Apothicraft.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {

    @Shadow private boolean hasErrors;

    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private ICondition.IContext context;

    @Shadow
    public static Recipe<?> fromJson(ResourceLocation p_44046_, JsonObject p_44047_, ICondition.IContext context) {
        return null;
    }

    @Shadow private Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipes;

    @Shadow private Map<ResourceLocation, Recipe<?>> byName;

    /**
     * @author Apothicon
     * @reason Disable any recipes not added by Apothicraft
     */
    @Overwrite
    protected void apply(Map<ResourceLocation, JsonElement> p_44037_, ResourceManager p_44038_, ProfilerFiller p_44039_) {
        this.hasErrors = false;
        Map<RecipeType<?>, ImmutableMap.Builder<ResourceLocation, Recipe<?>>> map = Maps.newHashMap();
        ImmutableMap.Builder<ResourceLocation, Recipe<?>> builder = ImmutableMap.builder();

        for(Map.Entry<ResourceLocation, JsonElement> entry : p_44037_.entrySet()) {
            ResourceLocation resourcelocation = entry.getKey();
            if (resourcelocation.getNamespace().equals("apothicraft")) {
                if (resourcelocation.getPath().startsWith("_"))
                    continue; //Forge: filter anything beginning with "_" as it's used for metadata.

                try {
                    if (entry.getValue().isJsonObject() && !net.minecraftforge.common.crafting.CraftingHelper.processConditions(entry.getValue().getAsJsonObject(), "conditions", this.context)) {
                        LOGGER.debug("Skipping loading recipe {} as it's conditions were not met", resourcelocation);
                        continue;
                    }
                    Recipe<?> recipe = fromJson(resourcelocation, GsonHelper.convertToJsonObject(entry.getValue(), "top element"), this.context);
                    if (recipe == null) {
                        LOGGER.info("Skipping loading recipe {} as it's serializer returned null", resourcelocation);
                        continue;
                    }
                    map.computeIfAbsent(recipe.getType(), (p_44075_) -> {
                        return ImmutableMap.builder();
                    }).put(resourcelocation, recipe);
                    builder.put(resourcelocation, recipe);
                } catch (IllegalArgumentException | JsonParseException jsonparseexception) {
                    LOGGER.error("Parsing error loading recipe {}", resourcelocation, jsonparseexception);
                }

            }
        }

        this.recipes = map.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, (p_44033_) -> {
            return p_44033_.getValue().build();
        }));
        this.byName = builder.build();
        LOGGER.info("Loaded {} recipes", (int)map.size());
    }
}
