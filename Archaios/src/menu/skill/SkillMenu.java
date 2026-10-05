package menu.skill;

import java.util.ArrayList;
import java.util.List;

import commons.operations.IOperation;
import menu.MenuGenerator;

public class SkillMenu implements IOperation {

    private List<IOperation> entryCollection = new ArrayList<>();

    public SkillMenu(){
        entryCollection.add(new TreasureSkillMenu());
        entryCollection.add(new ExcavationSkillMenu());
        entryCollection.add(new TerrainSkillMenu());
        entryCollection.add(new SpecialistSkillMenu());
    }

    @Override
    public String getName() {
        return "Estadísticas";
    }

    @Override
    public void operation() {
        MenuGenerator.askUserMenuOption(
            "Habilidades", 
            ':', entryCollection, 
            "0. Volver", 
            "Opción no aceptada.");
    }

}
