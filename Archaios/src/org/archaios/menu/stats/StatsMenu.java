package org.archaios.menu.stats;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;

/**
 * Menú con las opciones para consultar las estadísticas.
 * @author Miguel González Obenza
 */
public class StatsMenu implements IOperation{

    private List<IOperation> entryCollection = new ArrayList<>();

    public StatsMenu(){
        entryCollection.add(new StatsRegionsMenu());
        entryCollection.add(new StatsTreasureMenu());
    }

    /**
     * @return el nombre de la operación.
     * @see commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Estadísticas";
    }

    /**
     * Muestra un submenú con las opciones para las estadísticas.
     * @see StatsRegionsMenu
     * @see StatsTreasureByRegionMenu
     * @see StatsTreasureByTypeMenu
     * @see StatsTreasureMenu
     * @see commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(getName(), ':', entryCollection, "0. Volver", "Opción no aceptada.");
    }

}
