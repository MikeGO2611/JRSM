package org.archaios.menu.stats;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;

public class StatsTreasureMenu implements IOperation{

    private List<IOperation> entryCollection = new ArrayList<>();

    public StatsTreasureMenu(){
        entryCollection.add(new StatsTreasureByRegionMenu());
        entryCollection.add(new StatsTreasureByTypeMenu());
    }

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Ver tesoros";
    }

    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(getName(), ':', entryCollection, "0. Volver", "Opción no válida.");
    }

}
