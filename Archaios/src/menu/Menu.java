package menu;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;
import menu.archeologist.ArcheologistMenu;
import menu.atlas.AtlasMenu;
import menu.excavation.ExcavateMenu;
import menu.license.LicenseMenu;
import menu.skill.SkillMenu;
import menu.stats.StatsMenu;
import menu.treasure.TreasureCatalogMenu;

public class Menu {

    private List<IOperation> entryCollection = new ArrayList<>();


    public Menu(){
        entryCollection.add(new ArcheologistMenu());
        entryCollection.add(new SkillMenu());
        entryCollection.add(new LicenseMenu());
        entryCollection.add(new AtlasMenu());
        entryCollection.add(new TreasureCatalogMenu());
        entryCollection.add(new ExcavateMenu());
        entryCollection.add(new StatsMenu());
    }

    public void start(){
        MenuGenerator.askUserMenuOption(
                "Archaios", 
                '=',
                entryCollection, 
                "0. Salir", 
                "Opción no aceptada.");
    }
}
