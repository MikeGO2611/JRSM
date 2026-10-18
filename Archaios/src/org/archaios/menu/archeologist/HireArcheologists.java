package org.archaios.menu.archeologist;

import java.util.List;
import java.util.Random;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.main.Archaios;
import org.archaios.people.Archeologist;
import org.archaios.skills.Skill;
import org.archaios.skills.SkillService;

public class HireArcheologists implements IOperation{

    @Override
    public String getName() {
        return "Contratar";
    }

    @Override
    public void operation() {
        System.out.println("Puntos disponibles: " + Stats.getPoints());
        System.out.println("Cada arqueólogo cuesta 50 puntos.");
        if(Stats.getPoints() >= 50){
            Archeologist randomArcheologist = new Archeologist(null);
            Random random = new Random();
            int randomNum = random.nextInt(10);
            if(randomNum < 7){
                System.out.println("Arqueólogo de tipo común.");
            } else if (randomNum < 9){
                List<Skill> randomSkills = SkillService.selectThreeSkills(randomArcheologist, Archaios.skillRepository.getAllElements());
                randomArcheologist.addSkill(randomSkills.get(0));
                System.out.println("Arqueólogo de tipo infrecuente.");
                System.out.println("Con las siguientes habilidades:");
                for (Skill skill : randomArcheologist.getSkillList()) {
                    System.out.println("- " + skill.getName());
                }
                
            } else {
                List<Skill> randomSkills = SkillService.selectThreeSkills(randomArcheologist, Archaios.skillRepository.getAllElements());
                randomArcheologist.addSkill(randomSkills.get(0));
                randomArcheologist.addSkill(randomSkills.get(1));
                System.out.println("Arqueólogo de tipo raro.");
                System.out.println("Con las siguientes habilidades:");
                for (Skill skill : randomArcheologist.getSkillList()) {
                    System.out.println("- " + skill.getName());
                }
            }

            System.out.println("Nombre del nuevo arqueólogo: ");
            String archeologistName = UserInput.getUserString();
            randomArcheologist.setName(archeologistName);
            Stats.addArcheologist(randomArcheologist);

            System.out.println("Arqueólogo " + randomArcheologist.getName() + " añadido.");

        } else {
            System.out.println("No tienes puntos suficientes para contratar un nuevo arqueólogo.");
        }
    }

}
