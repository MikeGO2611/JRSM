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
            if (arch.getSkillList().contains(skill)) {
                continue;
            }

            if (!isIncompatible(arch, skill) && hasAllDependencies(arch, skill)) {
                validSkills.add(skill);
                if (!skill.getDependencies().isEmpty()) {
                    upgradeSkills.add(skill);
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

    /**
     * Comprueba si una habilidad es incompatible con alguna de las que ya posee el arqueólogo.
     * 
     * @param arch Arqueólogo a evaluar.
     * @param skill Habilidad a comprobar.
     * @return true si existe incompatibilidad, false en caso contrario.
     */
    private static boolean isIncompatible(Archeologist arch, Skill skill) {
        for (Skill learned : arch.getSkillList()) {
            if (skill.getIncompatibilities().contains(learned) || learned.getIncompatibilities().contains(skill)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si el arqueólogo cumple con todas las dependencias requeridas por la habilidad.
     * 
     * @param arch Arqueólogo a evaluar.
     * @param skill Habilidad a comprobar.
     * @return true si cumple todas las dependencias, false en caso contrario.
     */
    private static boolean hasAllDependencies(Archeologist arch, Skill skill) {
        for (Skill dependency : skill.getDependencies()) {
            if (!arch.getSkillList().contains(dependency)) {
                return false;
            }
        }
        return true;
    }
}