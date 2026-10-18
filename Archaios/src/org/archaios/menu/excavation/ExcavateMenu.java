package org.archaios.menu.excavation;

import java.util.List;

import org.archaios.commons.input.UserInput;
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

        List<ETerrainSize> terrainSizeCollection = selectedArcheologist.getTerrainTypes();
        System.out.println(MenuGenerator.generateTitle("Terrenos", ':', 20));

        for (int i = 0; i < terrainSizeCollection.size(); i++) {
            ETerrainSize terrainSize = terrainSizeCollection.get(i);
            System.out.println((i + 1) + ". " + terrainSize.toString());
        }

        int userInput = UserInput.getUserIntegerInRange(0, terrainSizeCollection.size(), "Opción no válida.");
        ETerrainSize selectedTerrainSize = terrainSizeCollection.get(userInput - 1);


        Excavation excavation = new Excavation(selectedArcheologist, new Greece(), selectedTerrainSize);
        excavation.excavate();
    }
}
