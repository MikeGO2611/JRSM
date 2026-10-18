package org.archaios.menu.excavation;

import org.archaios.commons.operations.IOperation;
import org.archaios.excavation.ETerrainSize;
import org.archaios.excavation.Excavation;
import org.archaios.menu.MenuGenerator;
import org.archaios.menu.archeologist.ArcheologistMenuUtils;
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
        Archeologist selectedArcheologist = ArcheologistMenuUtils.selectArcheologist();
        if (selectedArcheologist == null) {
            return;
        }

        // Elegir region
        // Elegir tamaño

        ETerrainSize terrain = ETerrainSize.SMALL;
        Excavation excavation = new Excavation(selectedArcheologist, new Greece(), terrain);
        excavation.excavate();
    }
}
