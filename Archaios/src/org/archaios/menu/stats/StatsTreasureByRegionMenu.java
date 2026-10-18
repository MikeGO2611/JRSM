package org.archaios.menu.stats;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.MenuGenerator;
import org.archaios.treasures.Treasure;

public class StatsTreasureByRegionMenu implements IOperation{

    @Override
    public String getName() {
        return "Por región";
    }

    @Override
    public void operation() {
        for(TerrainStatEntry terrainStatEntry : Stats.getTerrainStatEntryCollection()){
            System.out.println(MenuGenerator.generateTitle(terrainStatEntry.getRegion().getName(), '-', 20));
            for(Treasure treasure : terrainStatEntry.getTreasures()){
                System.out.println("- " + treasure.getName());
                System.out.println("- " + treasure.getCode());
            }
        }
    }

}