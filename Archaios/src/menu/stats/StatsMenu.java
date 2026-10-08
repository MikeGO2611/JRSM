package menu.stats;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;
import menu.MenuGenerator;

public class StatsMenu implements IOperation{

    private List<IOperation> entryCollection = new ArrayList<>();

    public StatsMenu(){
        entryCollection.add(new StatsRegionsMenu());
        entryCollection.add(new StatsTreasureMenu());
    }

    @Override
    public String getName() {
        return "Estadísticas";
    }

    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(getName(), ':', entryCollection, "0. Volver", "Opción no aceptada.");
    }

}
