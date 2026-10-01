package menu;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;

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
        System.out.println(TitleGenerator.generateTitle("Archaios", '='));

        for(int i = 0; i < entryCollection.size(); i++){
            System.out.println(i + ". " + entryCollection.get(i).getName());
        }

        System.out.println("0. Exit");
    }
}
