package org.archaios.menu.skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.main.Archaios;
import org.archaios.menu.MenuGenerator;
import org.archaios.skills.ExpertSkill;
import org.archaios.skills.Skill;

/**
 * Menú encargado de mostrar la lista de habilidades de especialista.
 * @author Miguel González Obenza
 */
public class SpecialistSkillMenu implements IOperation {
    /** Lista con las habilidades que aplican a especialista */
    private List<Skill> specialistSkillCollection = new ArrayList<Skill>();

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Especialista";
    }

    /**
     * Muestra un listado con las habilidades de especialista, y permite al jugador ver más detalles de cada una.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        initSkillList();

        boolean userExit = false;

        while (!userExit) {
            System.out.println(MenuGenerator.generateTitle(getName(), '-', 20));
            for(int i = 0; i < specialistSkillCollection.size(); i++){
                System.out.println((i + 1) + ". " + specialistSkillCollection.get(i).getName());
            }
            System.out.println("0. Volver");

            int userInput = UserInput.getUserIntegerInRange(0, specialistSkillCollection.size(), "Opción no válida.");

            if(userInput != 0){
                Skill selectedSkill = specialistSkillCollection.get(userInput - 1);
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
            if(skill instanceof ExpertSkill){
                specialistSkillCollection.add(skill);
            }
        }

        Collections.sort(specialistSkillCollection, new Comparator<Skill>() {
            public int compare(Skill skill, Skill nextSkill){
                return skill.getName().compareTo(nextSkill.getName());
            }
        });
    }

}
