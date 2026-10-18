package org.archaios.menu.skill;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;

/**
 * Menú con las opciones para consultar las habilidades.
 * @author Miguel González Obenza
 */
public class SkillMenu implements IOperation {

    /** Las opciones que se pueden escoger de este menú. */
    private List<IOperation> entryCollection = new ArrayList<>();

    /**
     * Constructor que inicializa la lista de opciones con las opciones posibles.
     */
    public SkillMenu(){
        entryCollection.add(new TreasureSkillMenu());
        entryCollection.add(new ExcavationSkillMenu());
        entryCollection.add(new TerrainSkillMenu());
        entryCollection.add(new SpecialistSkillMenu());
    }

    /**
     * @return el nombre de la operación
     * @see commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Habilidades";
    }

    /**
     * Muestra un submenú con las opciones para las habilidades.
     * @see ExcavationSkillMenu
     * @see SpecialistSkillMenu
     * @see TerrainSkillMenu
     * @see TreasureSkillMenu
     * @see commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(
            "Habilidades", 
            ':', entryCollection, 
            "0. Volver", 
            "Opción no aceptada.");
    }

}
