package org.archaios.menu.skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.main.Archaios;
import org.archaios.menu.MenuGenerator;
import org.archaios.skills.ExcavationSkill;
import org.archaios.skills.RevelationSkill;
import org.archaios.skills.Skill;

/**
 * Menú encargado de mostrar la lista de habilidades de excavación.
 * @author Miguel González Obenza
 */
public class ExcavationSkillMenu implements IOperation {
    /** Lista con las habilidades que aplican a excavación */
    private List<Skill> excavationSkillCollection = new ArrayList<Skill>();

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Excavación";
    }

    /**
     * Muestra un listado con las habilidades de excavación, y permite al jugador ver más detalles de cada una.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        initSkillList();

        boolean userExit = false;

        while (!userExit) {
            System.out.println(MenuGenerator.generateTitle(getName(), '-', 20));
            for(int i = 0; i < excavationSkillCollection.size(); i++){
                System.out.println((i + 1) + ". " + excavationSkillCollection.get(i).getName());
            }
            System.out.println("0. Volver");

            int userInput = UserInput.getUserIntegerInRange(0, excavationSkillCollection.size(), "Opción no válida.");

            if(userInput != 0){
                Skill selectedSkill = excavationSkillCollection.get(userInput - 1);
                System.out.println(MenuGenerator.generateTitle(selectedSkill.getName(), '-', 20));
                System.out.println(selectedSkill.getDescription());
                System.out.println("Tipo: " + selectedSkill.getType());
            } else {
                userExit = true;
            }
        }

    }

    /**
     * Inicializa una lista filtrada de habilidades que solo aplican a este menú.
     */
    private void initSkillList(){
        List<Skill> skillCollection = Archaios.skillRepository.getAllElements();

        for (Skill skill : skillCollection) {
            if(skill instanceof ExcavationSkill || skill instanceof RevelationSkill){
                excavationSkillCollection.add(skill);
            }
        }

        Collections.sort(excavationSkillCollection, new Comparator<Skill>() {
            public int compare(Skill skill, Skill nextSkill){
                return skill.getName().compareTo(nextSkill.getName());
            }
        });
    }

}
