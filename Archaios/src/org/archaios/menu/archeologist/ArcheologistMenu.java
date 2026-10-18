package org.archaios.menu.archeologist;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;

/**
 * Menú con las opciones para gestionar los arqueólogos.
 * @author Miguel González Obenza
 */
public class ArcheologistMenu implements IOperation {
    /** Lista con las operaciones del menú */
    private List<IOperation> entryCollection = new ArrayList<>();

    /**
     * Inicializa el menú con sus entradas.
     */
    public ArcheologistMenu(){
        entryCollection.add(new ShowArcheologists());
        entryCollection.add(new HireArcheologists());
        entryCollection.add(new LicenseArcheologist());
    }

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Arqueólogos";
    }

    /**
     * Muestra un submenú con las opciones para los arqueólogos.
     * @see HireArcheologists
     * @see LicenseArcheologist
     * @see ShowArcheologists
     * @see commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(
            getName(), 
            ':', 
            entryCollection, 
            "0. Volver", 
            "Operación no válida.");
    }

}
