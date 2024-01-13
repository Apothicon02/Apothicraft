package com.Apothic0n.Apothicraft.mixin;

import com.google.common.collect.Iterators;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Redirect(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;", ordinal = 0))
    private Iterator<Map.Entry<ResourceLocation, JsonElement>> filterRecipes(Set<Map.Entry<ResourceLocation, JsonElement>> instance) {
        return Iterators.filter(instance.iterator(), entry -> (!entry.getKey().getNamespace().equals("minecraft") && !entry.getKey().getNamespace().equals("create")));
    }
}
