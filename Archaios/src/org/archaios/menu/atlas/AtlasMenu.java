package org.archaios.menu.atlas;

import java.util.List;

import org.archaios.commons.input.UserInput;

import org.archaios.commons.operations.IOperation;
import org.archaios.main.Archaios;
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
        List<Region> regionCollection = Archaios.regionsRepository.getAllElements();

        boolean userExit = false;

        while (!userExit) {

            System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));

            for(int i = 0; i < regionCollection.size(); i++){
                Region region = regionCollection.get(i);
                System.out.println((i + 1) + ". " + region.getName());
            }
            System.out.println("0. Volver");

            int userInput = UserInput.getUserIntegerInRange(0, regionCollection.size(), "Opción no válida");

            if(userInput != 0){

                Region selectedRegion = regionCollection.get(userInput - 1);

                regionTreasuresMenu(selectedRegion);
            } else {
                userExit = true;
            }

        }
        
    }

    private void regionTreasuresMenu(Region selectedRegion){
        boolean userExit = false;

        while (!userExit) {
            System.out.println(MenuGenerator.generateTitle(selectedRegion.getName(), '-', 20));

            for (int i = 0; i < selectedRegion.getAllTreasures().size(); i++) {
                Treasure treasure = selectedRegion.getAllTreasures().get(i);
                System.out.println((i + 1) + ". " + treasure.getName() + " - " + treasure.getType().toString() + " " + treasure.getRarity().toString());
            }

            System.out.println("0. Volver");

            int userInput = UserInput.getUserIntegerInRange(0, selectedRegion.getAllTreasures().size(), "Opción no válida.");

            if(userInput != 0){
                Treasure selectedTreasure = selectedRegion.getAllTreasures().get(userInput - 1);
                System.out.println(MenuGenerator.generateTitle(selectedTreasure.getName(), ':', 20));
                System.out.println(selectedTreasure.getDescription());

                System.out.println("\n¿Consultar otro tesoro?");
                System.out.println("1. Sí");
                System.out.println("0. No");

                userInput = UserInput.getUserIntegerInRange(0, 2, "Opción no válida.");

                if(userInput == 0){
                    userExit = true;
                }

            } else {
                userExit = true;
            }
        }
    }

}
