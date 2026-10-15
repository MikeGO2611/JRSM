package org.archaios.menu.excavation;

import javax.swing.plaf.synth.Region;

import org.archaios.commons.operations.IOperation;
import org.archaios.excavation.ETerrainShape;
import org.archaios.excavation.ETerrainSize;
import org.archaios.excavation.Excavation;
import org.archaios.excavation.Terrain;
import org.archaios.menu.MenuGenerator;
import org.archaios.menu.archeologist.ListArcheologists;
import org.archaios.people.Archeologist;
import org.archaios.regions.Countries.Greece;

/**
 * Menú con las opciones de excavación.
 * 
 * @author Miguel González Obenza
 */
public class ExcavateMenu implements IOperation {

    @Override
    public String getName() {
        return "Excavación";
    }

    @Override
    public void operation() {
        System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));
        Archeologist selectedArcheologist = ListArcheologists.selectArcheologist();
        // Elegir region
        // Elegir tamaño
        
        Excavation excavation = new Excavation();
        excavation.start(null, selectedArcheologist, ETerrainSize.SMALL);

    }

}
