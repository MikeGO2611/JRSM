package menu.archeologist;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;
import menu.MenuGenerator;

/**
 * Menú con las opciones para gestionar los arqueólogos.
 */
public class ArcheologistMenu implements IOperation {

    
    private List<IOperation> entryCollection = new ArrayList<>();

    /**
     * Inicializa el menú con sus entradas.
     */
    public ArcheologistMenu(){
        entryCollection.add(new ListArcheologists());
        entryCollection.add(new HireArcheologists());
        entryCollection.add(new LicenseArcheologist());
    }

    /**
     * @return el nombre de la operación.
     * @see commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Arqueólogos";
    }

    /**
     * Muestra un submenú con las opciones para los arqueólogos.
     * @see HireArcheologists
     * @see LicenseArcheologist
     * @see ListArcheologists
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
