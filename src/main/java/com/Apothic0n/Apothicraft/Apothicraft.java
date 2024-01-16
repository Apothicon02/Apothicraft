package com.Apothic0n.Apothicraft;

import com.Apothic0n.Apothicraft.core.events.ForgeEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Set;

@Mod(Apothicraft.MODID)
public class Apothicraft {
    public static final String MODID = "apothicraft";
    public static ArrayList<String> woodBlocks = generateSet();

    private static ArrayList<String> generateSet() {
        ArrayList<String> tempList = new ArrayList<>();
        for (String string : Set.of("oak", "dark_oak", "spruce", "birch", "acacia", "jungle", "mangrove", "cherry")) {
            tempList.add("minecraft:blocks/stripped_"+string+"_log");
            tempList.add("minecraft:blocks/"+string+"_log");
            tempList.add("minecraft:blocks/"+string+"_wood");
            tempList.add("minecraft:blocks/"+string+"_planks");
            tempList.add("minecraft:blocks/"+string+"_slab");
            tempList.add("minecraft:blocks/"+string+"_stairs");
            tempList.add("minecraft:blocks/"+string+"_boat");
            tempList.add("minecraft:blocks/"+string+"_sign");
            tempList.add("minecraft:blocks/"+string+"_hanging_sign");
            tempList.add("minecraft:blocks/"+string+"_fence");
            tempList.add("minecraft:blocks/"+string+"_fence_gate");
            tempList.add("minecraft:blocks/"+string+"_door");
            tempList.add("minecraft:blocks/"+string+"_trapdoor");
            tempList.add("minecraft:blocks/"+string+"_button");
            tempList.add("minecraft:blocks/"+string+"_pressure_plate");
            tempList.add("minecraft:blocks/"+string+"_sapling");
        }
        return tempList;
    }

    public Apothicraft() {
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.FARMER).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.FARMER).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.FISHERMAN).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.FISHERMAN).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.SHEPHERD).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.SHEPHERD).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.FLETCHER).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.FLETCHER).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.LIBRARIAN).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.LIBRARIAN).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.CARTOGRAPHER).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.CARTOGRAPHER).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.CLERIC).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.CLERIC).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.ARMORER).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.ARMORER).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.WEAPONSMITH).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.WEAPONSMITH).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.TOOLSMITH).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.TOOLSMITH).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.BUTCHER).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.BUTCHER).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.LEATHERWORKER).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.LEATHERWORKER).remove(i);
        }
        for (int i = 0; i < VillagerTrades.TRADES.get(VillagerProfession.MASON).size(); i++) {
            VillagerTrades.TRADES.get(VillagerProfession.MASON).remove(i);
        }
    }
}