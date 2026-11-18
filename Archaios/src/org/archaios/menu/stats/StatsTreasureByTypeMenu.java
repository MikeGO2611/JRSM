package org.archaios.menu.stats;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.MenuGenerator;
import org.archaios.treasures.ETreasureType;
import org.archaios.treasures.Treasure;

public class StatsTreasureByTypeMenu implements IOperation{

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Por tipo";
    }

    /**
     * Muestra las estadísticas de los tesoros por tipo.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        if(Stats.getTerrainStatEntryCollection().size() > 0){
            for(TerrainStatEntry terrainStatEntry : Stats.getTerrainStatEntryCollection()){
                for(ETreasureType treasureType : ETreasureType.values()){
                    String type = treasureType.toString().substring(0, 1).toUpperCase() + treasureType.toString().substring(1);

                    List<Treasure> treasureCollection = new ArrayList<>();

                    for(Treasure treasure : terrainStatEntry.getTreasures()){
                        if(treasure.getType() == treasureType){
                            treasureCollection.add(treasure);
                        }
                    }
                    
                    if(treasureCollection.size() > 0){
                        System.out.println(MenuGenerator.generateTitle(type, '-', 20));
                    
                        StatsTreasureUtils.printTreasures(treasureCollection);
                    }
                }
            }
        } else {
            System.out.println("No hay datos para mostrar. ¡A excavar!");
        }
    }

}