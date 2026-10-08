package menu.stats;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;
import menu.Menu;
import menu.MenuGenerator;

public class StatsTreasureMenu implements IOperation{

    private List<IOperation> entryCollection = new ArrayList<>();

    public StatsTreasureMenu(){
        entryCollection.add(new StatsTreasureByRegionMenu());
        entryCollection.add(new StatsTreasureByTypeMenu());
    }

    @Override
    public String getName() {
        return "Ver tesoros";
    }

    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(getName(), ':', entryCollection, "0. Volver", "Opción no válida.");
    }

}
