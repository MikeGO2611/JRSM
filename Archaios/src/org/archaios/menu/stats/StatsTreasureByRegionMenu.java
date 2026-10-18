package org.archaios.menu.stats;

import java.util.List;

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

            List<Treasure> treasureCollection = terrainStatEntry.getTreasures();

            for(int i = 0; i < treasureCollection.size(); i++){
                Treasure treasure = treasureCollection.get(i);
                Treasure lastTreasure = null;

                if(i > 0 && i < treasureCollection.size() - 1){
                    lastTreasure = treasureCollection.get(i + 1);
                }

                if(lastTreasure == null){
                    System.out.println("- " + treasure.getName());
                } 
                else if (!lastTreasure.getName().equals(treasure.getName())){
                    System.out.println("- " + treasure.getName());
                }
                System.out.println("  - " + treasure.getCode());
            }
        }
    }

}