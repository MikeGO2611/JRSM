package org.archaios.menu.stats;

import java.util.List;

import org.archaios.treasures.Treasure;

/** Utilidades para imprimir listados de tesoros en los menús de estadísticas. */
public class StatsTreasureUtils {
    
    /** 
     * Imprime los tesoros agrupándolos por nombre. 
     * @param treasureCollection Lista de tesoros a mostrar. 
     */
    public static void printTreasures(List<Treasure> treasureCollection) {
        for (int i = 0; i < treasureCollection.size(); i++) {
            Treasure treasure = treasureCollection.get(i);
            Treasure lastTreasure = null;

            if (i > 0 && i < treasureCollection.size()) {
                lastTreasure = treasureCollection.get(i - 1);
            }

            if (lastTreasure == null) {
                System.out.println("- " + treasure.getName());
            } else if (!lastTreasure.getName().equals(treasure.getName())) {
                System.out.println("- " + treasure.getName());
            }
            System.out.println("  - " + treasure.getCode());
        }
    }
}
