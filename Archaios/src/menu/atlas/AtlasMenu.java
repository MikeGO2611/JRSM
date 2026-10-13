package menu.atlas;

import java.util.List;

import commons.input.UserInput;
import commons.operations.IOperation;
import menu.MenuGenerator;
import regions.Atlas;
import regions.Region;
import treasures.Treasure;

/**
 * Menú con las opciones para las regiones.
 * @author Miguel González Obenza
 */
public class AtlasMenu implements IOperation{

    @Override
    public String getName() {
        return "Atlas";
    }

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
