package org.archaios.menu.atlas;

import java.util.List;

import org.archaios.commons.input.UserInput;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;
import org.archaios.regions.Atlas;
import org.archaios.regions.Region;
import org.archaios.treasures.Treasure;

/**
 * Menú con las opciones para las regiones.
 * @author Miguel González Obenza
 */
public class AtlasMenu implements IOperation{

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Atlas";
    }

    /**    (non-Javadoc)
     * Pide al usuario que seleccione una región, y muestra todos los tesoros posibles en ella.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        List<Region> regionCollection = Atlas.getRegionCollection();
        System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));

        for(int i = 0; i < regionCollection.size(); i++){
            Region region = regionCollection.get(i);
            System.out.println((i + 1) + ". " + region.getName());
        }
        System.out.println("0. Volver");

        int userInput = UserInput.getUserIntegerInRange(0, regionCollection.size(), "Opción no válida");

        Region selectedRegion = regionCollection.get(userInput - 1);
        System.out.println(MenuGenerator.generateTitle(selectedRegion.getName(), '-', 20));

        for (Treasure treasure : selectedRegion.getAllTreasures()) {
            System.out.println("- " + treasure.getName() + " - " + treasure.getType().toString() + " " + treasure.getRarity().toString());
        }
    }

}
