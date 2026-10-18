package org.archaios.menu.stats;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.MenuGenerator;

public class StatsRegionsMenu implements IOperation{

    @Override
    public String getName() {
        return "Ver regiones";
    }

    @Override
    public void operation() {
        for (TerrainStatEntry terrainStatEntry : Stats.getTerrainStatEntryCollection()){
            System.out.println(MenuGenerator.generateTitle(terrainStatEntry.getRegion().getName(), '-', 20));
            System.out.println("Veces visitada: " + terrainStatEntry.getVisits());
            System.out.println("Total de tesoros excavados: " + terrainStatEntry.getTreasureDigCount());
        }
    }

}
