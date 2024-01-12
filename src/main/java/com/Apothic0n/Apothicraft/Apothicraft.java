package com.Apothic0n.Apothicraft;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.fml.common.Mod;

@Mod(Apothicraft.MODID)
public class Apothicraft {
    public static final String MODID = "apothicraft";

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