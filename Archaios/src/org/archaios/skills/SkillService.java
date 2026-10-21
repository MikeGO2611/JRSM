package org.archaios.skills;

import java.util.ArrayList;
import java.util.List;

import org.archaios.helpers.UtilsHelper;
import org.archaios.people.Archeologist;

/**
 * Servicio encargado de la lógica de selección y filtrado de habilidades
 * disponibles para los arqueólogos según dependencias e incompatibilidades.
 * 
 * @author Javier Búa Martínez
 */
public class SkillService {

    /**
     * Selecciona hasta tres habilidades válidas para ofrecer al arqueólogo al subir de nivel.
     * Prioriza una habilidad que sea mejora directa de otra ya aprendida y completa el resto al azar.
     * 
     * @param arch Arqueólogo que va a aprender la habilidad.
     * @param allSkills Catálogo completo de habilidades del juego.
     * @return Lista con un máximo de tres habilidades elegibles.
     */
    public static List<Skill> selectThreeSkills(Archeologist arch, List<Skill> allSkills) {
        List<Skill> validSkills = new ArrayList<>();
        List<Skill> upgradeSkills = new ArrayList<>();
        List<Skill> options = new ArrayList<>();

        for (Skill skill : allSkills) {
            boolean alreadyOwned = arch.getSkillList().contains(skill);
            
            if (!alreadyOwned) {
                boolean isCompatible = true;
                for (Skill learned : arch.getSkillList()) {
                    if (skill.getIncompatibilities().contains(learned) || learned.getIncompatibilities().contains(skill)) {
                        isCompatible = false;
                        break;
                    }
                }

                if (isCompatible) {
                    boolean hasAllDependencies = true;
                    for (Skill dependency : skill.getDependencies()) {
                        if (!arch.getSkillList().contains(dependency)) {
                            hasAllDependencies = false;
                            break;
                        }
                    }

                    if (hasAllDependencies) {
                        validSkills.add(skill);
                        if (!skill.getDependencies().isEmpty()) {
                            upgradeSkills.add(skill);
                        }
                    }
                }
            }
        }

        if (!upgradeSkills.isEmpty()) {
            List<Integer> randomIdx = UtilsHelper.genRandomIdxs(1, upgradeSkills.size());
            if (!randomIdx.isEmpty()) {
                Skill upgradeChoice = upgradeSkills.get(randomIdx.get(0));
                options.add(upgradeChoice);
                validSkills.remove(upgradeChoice); 
            }
        }
        int needed = 3 - options.size();
        if (needed > 0 && !validSkills.isEmpty()) {
            int countToSelect = Math.min(needed, validSkills.size());
            List<Integer> randomIdxs = UtilsHelper.genRandomIdxs(countToSelect, validSkills.size());
            
            for (int idx : randomIdxs) {
                options.add(validSkills.get(idx));
            }
        }

        return options;
    }
}