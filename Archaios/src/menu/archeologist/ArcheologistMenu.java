package menu.archeologist;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;
import menu.MenuGenerator;

public class ArcheologistMenu implements IOperation {

    
    private List<IOperation> entryCollection = new ArrayList<>();

    public ArcheologistMenu(){
        entryCollection.add(new ListArcheologists());
        entryCollection.add(new HireArcheologists());
        entryCollection.add(new LicenseArcheologist());
    }

    @Override
    public String getName() {
        return "Arqueólogos";
    }

    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(
            getName(), 
            ':', 
            entryCollection, 
            "0. Volver", 
            "Operación no válida.");
    }

}
