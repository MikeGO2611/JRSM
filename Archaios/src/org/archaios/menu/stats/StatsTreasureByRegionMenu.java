package org.archaios.menu.stats;

import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.MenuGenerator;
import org.archaios.treasures.Treasure;

/**
 * Mostrar los tesoros por región.
 * @author Miguel González
 */
public class StatsTreasureByRegionMenu implements IOperation{

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Por región";
    }

    /**
     * Muestra las estadísticas de los tesoros por región.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        for(TerrainStatEntry terrainStatEntry : Stats.getTerrainStatEntryCollection()){
            System.out.println(MenuGenerator.generateTitle(terrainStatEntry.getRegion().getName(), '-', 20));

            List<Treasure> treasureCollection = terrainStatEntry.getTreasures();

            StatsTreasureUtils.printTreasures(treasureCollection);
        }
    }

}