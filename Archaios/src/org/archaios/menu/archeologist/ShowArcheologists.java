package org.archaios.menu.archeologist;

import org.archaios.commons.operations.IOperation;
import org.archaios.people.Archeologist;
/**
 * Menú para mostrar arqueólogos.
 * @see org.archaios.menu.archeologist.ArcheologistMenu
 * @author Miguel González Obenza
 */
public class ShowArcheologists implements IOperation{

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Ver Arqueólogos";
    }

    /**
     * Muestra una lista de todos los arqueólogos del jugador, y permite mostrar información concreta de cada uno.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        Archeologist selectedArcheologist = ArcheologistMenuUtils.selectArcheologist();

        if(selectedArcheologist != null){
            selectedArcheologist.archeologistInformation();
        }
    }

}
