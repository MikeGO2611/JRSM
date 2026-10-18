package org.archaios.menu.stats;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.MenuGenerator;

/**
 * Mostrar las estadísticas de cada región.
 * @author Miguel González Obenza
 */
public class StatsRegionsMenu implements IOperation{

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Ver regiones";
    }

    /**
     * Muestra las estadísticas de cada región
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        for (TerrainStatEntry terrainStatEntry : Stats.getTerrainStatEntryCollection()){
            System.out.println(MenuGenerator.generateTitle(terrainStatEntry.getRegion().getName(), '-', 20));
            System.out.println("Veces visitada: " + terrainStatEntry.getVisits());
            System.out.println( "Tesoros encontrados: " + terrainStatEntry.getTreasures().size() + "/" + terrainStatEntry.getRegion().getAllTreasures());
            System.out.println("Total de tesoros excavados: " + terrainStatEntry.getTreasureDigCount());
        }
    }

}
