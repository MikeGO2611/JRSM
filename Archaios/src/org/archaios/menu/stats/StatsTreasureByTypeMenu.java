package org.archaios.menu.stats;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.MenuGenerator;
import org.archaios.treasures.ETreasureType;
import org.archaios.treasures.Treasure;

public class StatsTreasureByTypeMenu implements IOperation{

    @Override
    public String getName() {
        return "Por tipo";
    }

    @Override
    public void operation() {
        for(TerrainStatEntry terrainStatEntry : Stats.getTerrainStatEntryCollection()){
            for(ETreasureType treasureType : ETreasureType.values()){
                String type = treasureType.toString().substring(0, 1).toUpperCase() + treasureType.toString().substring(1);
                System.out.println(MenuGenerator.generateTitle(type, '-', 20));

                for(Treasure treasure : terrainStatEntry.getTreasures()){
                    if(treasure.getType() == treasureType){
                        System.out.println("- " + treasure.getName());
                        System.out.println("- " + treasure.getCode());
                    }
                }
            }
        }
    }

}