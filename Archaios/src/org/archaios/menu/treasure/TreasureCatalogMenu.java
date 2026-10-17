package org.archaios.menu.treasure;

import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;
import org.archaios.regions.Atlas;
import org.archaios.regions.Region;
import org.archaios.treasures.Treasure;

public class TreasureCatalogMenu implements IOperation {

    @Override
    public String getName() {
        return "Catálogo de tesoros";
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

        List<Treasure> regionTreasureCollection = selectedRegion.getAllTreasures();

        for (int i = 0; i < regionTreasureCollection.size(); i++) {
            Treasure treasure = regionTreasureCollection.get(i);
            System.out.println(i + ". " + treasure.getName());
        }
    }

}
